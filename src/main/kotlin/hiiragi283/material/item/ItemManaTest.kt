package hiiragi283.material.item

import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import vazkii.botania.api.mana.IManaItem

object ItemManaTest : IManaItem {

    override fun getMana(p0: ItemStack?): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxMana(p0: ItemStack?): Int {
        TODO("Not yet implemented")
    }

    override fun addMana(p0: ItemStack?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun canReceiveManaFromPool(p0: ItemStack?, p1: TileEntity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun canReceiveManaFromItem(p0: ItemStack?, p1: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun canExportManaToPool(p0: ItemStack?, p1: TileEntity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun canExportManaToItem(p0: ItemStack?, p1: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isNoExport(p0: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }
}