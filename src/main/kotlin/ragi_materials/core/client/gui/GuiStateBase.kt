package ragi_materials.core.client.gui

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.ResourceLocation
import ragi_materials.core.container.ContainerStateBase

abstract class GuiStateBase(val container: ContainerStateBase) : GuiContainer(container) {

    abstract val background: ResourceLocation

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        fontRenderer.drawString(container.inventory.displayName.unformattedText, 8, 6, 0x404040)
        fontRenderer.drawString(container.player.inventory.displayName.unformattedText, 8, ySize - 96 + 2, 0x404040)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        mc.textureManager.bindTexture(background)
        val i = (width - xSize) / 2
        val j = (height - ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, xSize, ySize)
    }
}