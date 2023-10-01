package hiiragi283.material.api.fluid

import hiiragi283.material.api.item.HiiragiItemBlock
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.IFluidBlock

class MaterialFluidBlockItem(block: MaterialFluidBlock) : HiiragiItemBlock(block) {

    override fun getItemStackDisplayName(stack: ItemStack): String {
        val iFluidBlock: IFluidBlock = (block as? IFluidBlock) ?: return super.getItemStackDisplayName(stack)
        return I18n.format(iFluidBlock.fluid.unlocalizedName)
    }

}