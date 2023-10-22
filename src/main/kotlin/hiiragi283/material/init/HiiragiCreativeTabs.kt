package hiiragi283.material.init

import hiiragi283.material.RMReference
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.util.itemStack
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object HiiragiCreativeTabs {

    @JvmStatic
    private fun createCreativeTab(id: String, stack: () -> ItemStack): CreativeTabs =
        object : CreativeTabs("${RMReference.MOD_ID}.$id") {
            override fun createIcon(): ItemStack = stack()
        }

    val COMMON by lazy { createCreativeTab("common") { Items.WRITABLE_BOOK.itemStack() } }

    val MACHINE by lazy {
        createCreativeTab("machine") {
            HiiragiRegistries.BLOCK_MACHINE.getValue(MachineType.SMELTER)!!.itemStack()
        }
    }

    val MATERIAL_BLOCK by lazy { createCreativeTab("material_block") { Blocks.IRON_BLOCK.itemStack() } }

    val MATERIAL_ITEM by lazy { createCreativeTab("material_item") { Items.IRON_INGOT.itemStack() } }

}