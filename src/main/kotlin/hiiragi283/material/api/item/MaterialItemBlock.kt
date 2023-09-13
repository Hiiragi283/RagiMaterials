package hiiragi283.material.api.item

import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.api.block.MaterialBlock
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MaterialItemBlock(block: MaterialBlock) : HiiragiItemBlock(block, 32767) {

    val shape = block.shape

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        shape.getTranslatedName(HiiragiRegistry.getMaterial(stack.metadata))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistry.getMaterials()
            .filter { it.isValidIndex() && it.isSolid() && shape.isValid(it) }
            .map { getItemStack(it) }
            .sortedBy { it.metadata }
            .forEach { subItems.add(it) }
    }

}