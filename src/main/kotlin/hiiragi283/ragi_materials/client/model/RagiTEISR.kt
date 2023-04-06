package hiiragi283.ragi_materials.client.model

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.client.model.tile.ModelIndustrialLabo
import hiiragi283.ragi_materials.client.render.RenderIndustrialLabo
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

/*
  Thanks to SkyTheory!
  Source: https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/renderer/RenderTileEntityItemStack.java
*/

object RagiTEISR : TileEntityItemStackRenderer() {

    override fun renderByItem(stack: ItemStack) {
        renderByItem(stack, 1.0f)
    }

    override fun renderByItem(stack: ItemStack, partialTicks: Float) {
        val item = stack.item
        var block = Blocks.AIR
        if (item is ItemBlock) {
            block = item.block
        }

        if (block == RagiRegistry.BLOCK.BlockIndustrialLabo) {
            Minecraft.getMinecraft().textureManager.bindTexture(RenderIndustrialLabo.location)
            GlStateManager.pushMatrix()
            GlStateManager.enableRescaleNormal()
            GlStateManager.translate(0.5f, 0.0f, 0.5f)
            GlStateManager.scale(1.0f, -1.0f, -1.0f)
            GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f)
            ModelIndustrialLabo.render()
            GlStateManager.disableRescaleNormal()
            GlStateManager.popMatrix()
        }
    }
}