package hiiragi283.ragi_materials.client.render

import hiiragi283.ragi_materials.RagiRegistry
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

/**
Thanks to SkyTheory!
Source: https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/renderer/RenderTileEntityItemStack.java
 */

object RagiTEISR : TileEntityItemStackRenderer() {

    override fun renderByItem(stack: ItemStack, partialTicks: Float) {
        val item = stack.item
        var block = Blocks.AIR
        if (item is ItemBlock) block = item.block
        if (block == RagiRegistry.BlockIndustrialLabo) {
            RenderIndustrialLabo.render(0.0, 0.0, 0.0)
        }
    }
}