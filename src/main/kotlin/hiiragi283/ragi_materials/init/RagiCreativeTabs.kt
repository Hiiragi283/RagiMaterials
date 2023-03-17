package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack

class RagiCreativeTabs(name: String, val stack: ItemStack) : CreativeTabs("${Reference.MOD_ID}.$name") {

    init {
        RagiLogger.infoDebug("The creative tab $name is registered!")
    }

    override fun getTabIconItem(): ItemStack = stack

}