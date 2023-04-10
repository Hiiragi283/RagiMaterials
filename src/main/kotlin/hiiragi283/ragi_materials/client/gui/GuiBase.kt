package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.container.ContainerBase
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.ResourceLocation

abstract class GuiBase<T : TileBase>(val container: ContainerBase<T>) : GuiContainer(container) {

    abstract val background: ResourceLocation

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        fontRenderer.drawString(container.tile.displayName!!.unformattedText, 8, 6, 0x404040)
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