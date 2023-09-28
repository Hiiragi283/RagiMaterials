package hiiragi283.material

import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.registry.HiiragiRegistries
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

    val MACHINE by lazy {
        createCreativeTab("machine") {
            HiiragiRegistries.BLOCK_MACHINE.getValue(MachineType.SMELTER)!!.getItemStack()
        }
    }

    val MATERIAL_BLOCK by lazy { createCreativeTab("material_block") { ItemStack(Blocks.IRON_BLOCK) } }

    val MATERIAL_ITEM by lazy { createCreativeTab("material_item") { ItemStack(Items.IRON_INGOT) } }

}