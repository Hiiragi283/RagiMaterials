package hiiragi283.material.client

import hiiragi283.material.api.block.MaterialBlock
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.registry.Registry

@Environment(EnvType.CLIENT)
object RMRenderHandler {

    private val blocks = Registry.BLOCK.entrySet
        .map { it.value }
        .filterIsInstance<MaterialBlock>()
        .toTypedArray()

    fun load() {

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), *blocks)

    }

}