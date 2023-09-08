package hiiragi283.material.api.block;

import hiiragi283.material.HiiragiCreativeTabs;
import hiiragi283.material.api.item.MaterialItemBlock;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiEntry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.tile.MaterialTileEntity;
import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Comparator;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class MaterialBlock extends HiiragiBlockContainer.Holdable<MaterialTileEntity> {

    public final HiiragiShape shape;

    public MaterialBlock(HiiragiShape shape) {
        super(Material.IRON, shape.name(), MaterialTileEntity.class);
        this.shape = shape;
        setCreativeTab(HiiragiCreativeTabs.MATERIAL_BLOCK);
        setHarvestLevel("pickaxe", 1);
        this.itemBlock = new MaterialItemBlock(this); //must be last!
    }

    protected static final Consumer<HiiragiEntry.BLOCK> MODEL_CONSUMER = block -> HiiragiUtil.setModelSame(block.getObject());

    protected static final BiConsumer<HiiragiEntry.BLOCK, HiiragiMaterial> RECIPE_BICONSUMER = (block, material) -> {
    };

    public static MaterialBlock create(HiiragiShape shape) {
        return create(shape, MODEL_CONSUMER, RECIPE_BICONSUMER);
    }

    public static MaterialBlock create(
            HiiragiShape shape,
            Consumer<HiiragiEntry.BLOCK> model
    ) {
        return create(shape, model, RECIPE_BICONSUMER);
    }

    public static MaterialBlock create(
            HiiragiShape shape,
            BiConsumer<HiiragiEntry.BLOCK, HiiragiMaterial> recipe
    ) {
        return create(shape, MODEL_CONSUMER, recipe);
    }

    public static MaterialBlock create(
            HiiragiShape shape,
            Consumer<HiiragiEntry.BLOCK> model,
            BiConsumer<HiiragiEntry.BLOCK, HiiragiMaterial> recipe
    ) {

        return new MaterialBlock(shape) {

            @Override
            void getRecipe(HiiragiEntry.BLOCK block, HiiragiMaterial material) {
                recipe.accept(block, material);
            }

            @Override
            void getModel(HiiragiEntry.BLOCK block) {
                model.accept(block);
            }

        };

    }

    //    HiiragiBlock    //

    @Override
    @ParametersAreNonnullByDefault
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        OptionalUtil.getTile(world, pos, tileClazz)
                .flatMap(MaterialTileEntity::getMaterial)
                .ifPresent(material -> drops.add(asItemStack(material)));
    }

    @NotNull
    @Override
    @ParametersAreNonnullByDefault
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return OptionalUtil.getTile(world, pos, tileClazz)
                .flatMap(MaterialTileEntity::getMaterial)
                .map(this::asItemStack)
                .orElse(ItemStack.EMPTY);
    }

    @Override
    public int quantityDropped(@NotNull Random random) {
        return 0;
    }

    @NotNull
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void getSubBlocks(@NotNull CreativeTabs itemIn, @NotNull NonNullList<ItemStack> items) {
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

    abstract void getRecipe(HiiragiEntry.BLOCK block, HiiragiMaterial material);

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockColor(BlockColors blockColors) {
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> OptionalUtil.getTile(world, pos, tileClazz)
                .flatMap(MaterialTileEntity::getMaterial)
                .map(material -> material.color)
                .orElse(-1), this);
    }

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

    abstract void getModel(HiiragiEntry.BLOCK block);

}