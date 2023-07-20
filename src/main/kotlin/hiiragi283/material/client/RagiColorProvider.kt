package hiiragi283.material.client

import hiiragi283.material.api.HiiragiBlockItem
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.item.MaterialItem
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.util.registry.Registry

@Environment(EnvType.CLIENT)
object RagiColorProvider {

    private val blocks = Registry.BLOCK.entrySet
        .map { it.value }
        .filterIsInstance<MaterialBlock>()
        .toTypedArray()

    private val items = Registry.ITEM.entrySet
        .map { it.value }
        .filterIsInstance<MaterialItem>()
        .toTypedArray()

    fun load() {
        registerBlock()
        registerBlockItem()
        registerItem()
    }

    private fun registerBlock() {

        ColorProviderRegistry.BLOCK.register(
            BlockColorProvider { state, world, pos, tintIndex ->
                val block = state.block
                if (block is BlockColorProvider) return@BlockColorProvider block.getColor(state, world, pos, tintIndex)
                -1
            }, *blocks
        )

    }

    private fun registerBlockItem() {
        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is HiiragiBlockItem) {
                        val block = item.block
                        if (block is ItemColorProvider) return@ItemColorProvider block.getColor(stack, tintIndex)
                    }
                }
                -1
            }, *blocks
        )
    }

    private fun registerItem() {
        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is ItemColorProvider) return@ItemColorProvider item.getColor(stack, tintIndex)
                }
                -1
            }, *items
        )
    }

}