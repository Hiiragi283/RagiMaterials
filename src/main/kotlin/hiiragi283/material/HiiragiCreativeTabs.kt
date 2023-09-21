package hiiragi283.material

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object HiiragiCreativeTabs {

    val COMMON by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.common") {
            override fun createIcon(): ItemStack = ItemStack(HiiragiItems.BOOK_RESPAWN)
        }
    }

    val MACHINE by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.machine") {
            override fun createIcon(): ItemStack = HiiragiBlocks.MACHINE_SMELTER.getItemStack()
        }
    }

    val MATERIAL_BLOCK by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.material_block") {
            override fun createIcon(): ItemStack = ItemStack(Blocks.IRON_BLOCK)
        }
    }

    val MATERIAL_ITEM by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.material_item") {
            override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
        }
    }

}