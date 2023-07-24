package hiiragi283.material.client

import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.common.util.getRegistryEntries
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.util.registry.Registry

@Environment(EnvType.CLIENT)
object RMColorProvider {

    private val blocks: Collection<MaterialBlock> = getRegistryEntries(Registry.BLOCK).filterIsInstance<MaterialBlock>()

    private val items = getRegistryEntries(Registry.ITEM).filterIsInstance<RMItemColorProvider>()

    fun register() {
        registerBlock()
        registerBlockItem()
        registerItem()
    }

    private fun registerBlock() {

        blocks.forEach { ColorProviderRegistry.BLOCK.register(it, it) }

    }

    private fun registerBlockItem() {

        blocks.forEach { ColorProviderRegistry.ITEM.register(it, it) }

    }

    private fun registerItem() {

        items.forEach { ColorProviderRegistry.ITEM.register(it, it) }

    }

}