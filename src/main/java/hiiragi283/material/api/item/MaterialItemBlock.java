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

public class MaterialItemBlock extends HiiragiItemBlock {

    public final HiiragiShape shape;

    public MaterialItemBlock(MaterialBlock block) {
        super(block, 32767);
        this.shape = block.shape;
    }

    @NotNull
    public HiiragiShape getShape() {
        return shape;
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
        this.block.getSubBlocks(tab, items);
    }

}