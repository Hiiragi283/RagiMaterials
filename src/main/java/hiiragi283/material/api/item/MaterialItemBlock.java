package hiiragi283.material.api.item;

import hiiragi283.material.api.block.MaterialBlock;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.shape.HiiragiShape;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class MaterialItemBlock extends HiiragiItemBlock {

    public final HiiragiShape shape;

    public MaterialItemBlock(MaterialBlock block) {
        super(block, 32767);
        this.shape = block.shape;
    }

    //    Client    //

    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull String getItemStackDisplayName(@NotNull ItemStack stack) {
        return shape.getTranslatedName(HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) return;
        HiiragiMaterial.REGISTRY.getValues().stream()
                .filter(material -> material.isIndexValid() && material.isSolid() && shape.isValid(material))
                .map(this::asItemStack)
                .sorted(Comparator.comparing(ItemStack::getMetadata))
                .forEach(items::add);
    }

}