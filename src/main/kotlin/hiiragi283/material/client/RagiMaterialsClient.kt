package hiiragi283.material.client

import hiiragi283.material.api.item.HiiragiBlockItem
import hiiragi283.material.api.material.MaterialPart
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.RagiRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.block.Block
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.Item

object RagiMaterialsClient : ClientModInitializer {

    @Environment(EnvType.CLIENT)
    override fun onInitializeClient() {

        ColorProviderRegistry.BLOCK.register(
            BlockColorProvider { state, world, pos, tintIndex ->
                val block = state.block
                if (block is MaterialPart<*>) return@BlockColorProvider block.getColor(state, world, pos, tintIndex)
                -1
            }, *RagiRegistry.materialBlockSet.filterIsInstance<Block>().toTypedArray()
        )

        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is HiiragiBlockItem) {
                        val block = item.block
                        if (block is MaterialPart<*>) return@ItemColorProvider block.getColor(stack, tintIndex)
                    }
                }
                -1
            }, *RagiRegistry.materialBlockSet.filterIsInstance<Block>().toTypedArray()
        )

        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is MaterialPart<*>) return@ItemColorProvider item.getColor(stack, tintIndex)
                }
                -1
            }, *RagiRegistry.materialItemSet.filterIsInstance<Item>().toTypedArray()
        )

        RagiMaterials.LOGGER.info("Color Provider registered!")

    }
}