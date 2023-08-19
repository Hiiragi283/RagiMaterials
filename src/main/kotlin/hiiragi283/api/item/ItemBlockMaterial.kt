package hiiragi283.api.item

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.block.BlockMaterial
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemBlockMaterial(block: BlockMaterial) : HiiragiItemBlock(block, 32767),
    HiiragiEntry.ITEM {

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