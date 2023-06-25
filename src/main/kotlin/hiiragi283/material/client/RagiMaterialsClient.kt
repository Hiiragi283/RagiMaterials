package hiiragi283.material.client

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.item.HiiragiBlockItem
import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.RagiRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider

object RagiMaterialsClient : ClientModInitializer {

    @Environment(EnvType.CLIENT)
    override fun onInitializeClient() {

        ColorProviderRegistry.BLOCK.register(
            BlockColorProvider { state, world, pos, tintIndex ->
                val block = state.block
                if (block is MaterialPartBlock) return@BlockColorProvider block.materialH.color
                -1
            }, *RagiRegistry.materialBlockSet.toTypedArray()
        )

        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is HiiragiBlockItem) {
                        val block = item.block
                        if (block is MaterialPartBlock) return@ItemColorProvider block.materialH.color
                    }
                }
                -1
            }, *RagiRegistry.materialBlockSet.toTypedArray()
        )

        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is MaterialPartItem) return@ItemColorProvider item.material.color
                }
                -1
            }, *RagiRegistry.materialItemSet.toTypedArray()
        )

        RagiMaterials.LOGGER.info("Color Provider registered!")

    }
}