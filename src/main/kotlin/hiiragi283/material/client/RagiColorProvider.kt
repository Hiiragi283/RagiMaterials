package hiiragi283.material.client

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.item.HiiragiBlockItem
import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.part.IHiiragiPart
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
        .filterIsInstance<MaterialPartBlock>()
        .toTypedArray()

    private val items = Registry.ITEM.entrySet
        .map { it.value }
        .filterIsInstance<MaterialPartItem>()
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
                if (block is IHiiragiPart.BLOCK) return@BlockColorProvider block.getColor(state, world, pos, tintIndex)
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
                        if (block is IHiiragiPart.BLOCK) return@ItemColorProvider block.getColor(stack, tintIndex)
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
                    if (item is IHiiragiPart.ITEM) return@ItemColorProvider item.getColor(stack, tintIndex)
                }
                -1
            }, *items
        )
    }

}