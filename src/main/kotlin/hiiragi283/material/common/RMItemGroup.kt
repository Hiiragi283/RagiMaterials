package hiiragi283.material.common

import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.block.Blocks
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object RMItemGroup {

    val MATERIAL_BLOCk: ItemGroup = FabricItemGroupBuilder.create(hiiragiId("material_block"))
        .icon { ItemStack(Blocks.IRON_BLOCK) }
        .build()

    val MATERIAL_ITEM: ItemGroup = FabricItemGroupBuilder.create(hiiragiId("material_item"))
        .icon { ItemStack(Items.IRON_INGOT) }
        .build()

}