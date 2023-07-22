package hiiragi283.material.client

import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.block.MaterialBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.util.registry.Registry

@Environment(EnvType.CLIENT)
object RMColorProvider {

    private val blocks: Collection<MaterialBlock> = Registry.BLOCK.entrySet
        .map { it.value }
        .filterIsInstance<MaterialBlock>()

    private val items = Registry.ITEM.entrySet
        .map { it.value }
        .filterIsInstance<RMItemColorProvider>()

    fun register() {
        registerBlock()
        registerBlockItem()
        registerItem()
    }

    private fun registerBlock() {

        blocks.forEach { ColorProviderRegistry.BLOCK.register(it, it) }

        /*ColorProviderRegistry.BLOCK.register(
            BlockColorProvider { state, world, pos, tintIndex ->
                val block = state.block
                if (block is BlockColorProvider) return@BlockColorProvider block.getColor(state, world, pos, tintIndex)
                -1
            }, *blocks
        )*/

    }

    private fun registerBlockItem() {

        blocks.forEach { ColorProviderRegistry.ITEM.register(it, it) }

        /*ColorProviderRegistry.ITEM.register(
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
        )*/

    }

    private fun registerItem() {

        items.forEach { ColorProviderRegistry.ITEM.register(it, it) }

        /*
        ColorProviderRegistry.ITEM.register(
            ItemColorProvider { stack, tintIndex ->
                if (!stack.isEmpty) {
                    val item = stack.item
                    if (item is ItemColorProvider) return@ItemColorProvider item.getColor(stack, tintIndex)
                }
                -1
            }, *items
        )*/

    }

}