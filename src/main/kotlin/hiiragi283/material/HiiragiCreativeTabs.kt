package hiiragi283.material

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object HiiragiCreativeTabs {

    private fun createCreativeTab(id: String, stack: () -> ItemStack): CreativeTabs =
        object : CreativeTabs("${RMReference.MOD_ID}.$id") {
            override fun createIcon(): ItemStack = stack()
        }

    val COMMON by lazy { createCreativeTab("common") { HiiragiItems.BOOK_RESPAWN.getItemStack() } }

    val MACHINE by lazy { createCreativeTab("machine") { HiiragiBlocks.MACHINE_SMELTER.getItemStack() } }

    val MATERIAL_BLOCK by lazy { createCreativeTab("material_block") { ItemStack(Blocks.IRON_BLOCK) } }

    val MATERIAL_ITEM by lazy { createCreativeTab("material_item") { ItemStack(Items.IRON_INGOT) } }

}