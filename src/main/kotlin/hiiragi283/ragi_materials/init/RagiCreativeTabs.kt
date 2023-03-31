package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class RagiCreativeTabs(name: String, val stack: ItemStack) : CreativeTabs("${Reference.MOD_ID}.$name") {

    init {
        RagiLogger.infoDebug("The creative tab $name is registered!")
    }

    companion object {
        //Creative Tabの定義
        val BLOCK = RagiCreativeTabs("blocks", ItemStack(RagiItem.ItemBlockForgeFurnace))
        val FULLBOTTLE = RagiCreativeTabs("fullbottles", ItemStack(RagiItem.ItemFullBottle))
        val MATERIAL = RagiCreativeTabs("materials", ItemStack(RagiItem.ItemIngot, 1, 26))
    }

    @SideOnly(Side.CLIENT)
    override fun createIcon(): ItemStack = stack

}