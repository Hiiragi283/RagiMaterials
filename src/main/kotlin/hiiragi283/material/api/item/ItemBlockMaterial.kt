package hiiragi283.material.api.item

import hiiragi283.material.RMReference
import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.BlockMaterial
import hiiragi283.material.api.material.MaterialRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemBlockMaterial(block: BlockMaterial) : HiiragiItemBlock(block, RMReference.MOD_ID, block.shape.name, 32767),
    HiiragiEntry.ITEM {

    val shape = block.shape

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        shape.getTranslatedName(MaterialRegistry.getMaterial(stack.metadata))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .map { getItemStack(it) }
            .filter { it.metadata != 0 }
            .sortedBy { it.metadata }
            .forEach { subItems.add(it) }
    }

}