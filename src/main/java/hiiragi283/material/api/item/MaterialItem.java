package hiiragi283.material.api.item;

import hiiragi283.material.HiiragiCreativeTabs;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiEntry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class MaterialItem extends HiiragiItem {

    private final HiiragiShape shape;

    public MaterialItem(HiiragiShape shape) {
        super(shape.name(), 32767);
        this.shape = shape;
        setCreativeTab(HiiragiCreativeTabs.MATERIAL_ITEM);
    }

    @NotNull
    public HiiragiShape getShape() {
        return shape;
    }

    protected static final Consumer<HiiragiEntry.ITEM> MODEL_CONSUMER = item -> HiiragiUtil.setModelSame(item.getObject());

    public static MaterialItem create(HiiragiShape shape) {
        return create(shape, MODEL_CONSUMER, HiiragiUtil.getEmptyBiConsumer());
    }

    public static MaterialItem create(HiiragiShape shape, Consumer<HiiragiEntry.ITEM> model) {
        return create(shape, model, HiiragiUtil.getEmptyBiConsumer());
    }

    public static MaterialItem create(HiiragiShape shape, BiConsumer<HiiragiEntry.ITEM, HiiragiMaterial> recipe) {
        return create(shape, MODEL_CONSUMER, recipe);
    }

    public static MaterialItem create(
            HiiragiShape shape,
            Consumer<HiiragiEntry.ITEM> model,
            BiConsumer<HiiragiEntry.ITEM, HiiragiMaterial> recipe
    ) {

        return new MaterialItem(shape) {

            @Override
            protected void getRecipe(HiiragiEntry.ITEM item, HiiragiMaterial material) {
                recipe.accept(item, material);
            }

            @Override
            protected void getModel(HiiragiEntry.ITEM item) {
                model.accept(item);
            }
        };

    }

    //    Client    //

    @NotNull
    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(@NotNull ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .map(shape::getTranslatedName)
                .orElse(super.getItemStackDisplayName(stack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (!isInCreativeTab(tab)) return;
        HiiragiMaterial.REGISTRY.getValues().stream()
                .filter(material -> material.isIndexValid() && material.isSolid() && shape.isValid(material))
                .map(this::asItemStack)
                .sorted(Comparator.comparing(ItemStack::getMetadata))
                .filter(stack -> stack.getMetadata() > 0)
                .forEach(items::add);
    }

    //    HiiragiEntry    //

    @Override
    public void registerOreDict() {
        HiiragiMaterial.REGISTRY.getValues().stream()
                .filter(material -> material.isSolid() && shape.isValid(material))
                .forEach(material -> shape.getOreDicts(material).forEach(oreDict -> OreDictionary.registerOre(oreDict, asItemStack(material))));
    }

    @Override
    public void registerRecipe() {
        HiiragiMaterial.REGISTRY.getValues().stream()
                .filter(material -> material.isSolid() && shape.isValid(material))
                .forEach(material -> getRecipe(this, material));
    }

    protected abstract void getRecipe(HiiragiEntry.ITEM item, HiiragiMaterial material);

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemColor(ItemColors itemColors) {
        itemColors.registerItemColorHandler((stack, tintIndex) -> HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .map(material -> material.color)
                .orElse(-1), this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        getModel(this);
    }

    protected abstract void getModel(HiiragiEntry.ITEM item);

}