package hiiragi283.api.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.audio.SoundHandler
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.ResourceLocation

/**
 * @see <a href = https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/gui/AbstractButton.java>Reference </a>
 */

open class HiiragiButton(
    gui: HiiragiGui<*>,
    id: Int,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    val location: ResourceLocation,
    val texU: Int = 0,
    val texV: Int = 0
) : GuiButton(
    id, gui.getGuiLeft() + x, gui.getGuiTop() + y, width, height, ""
) {

    init {
        enabled = true
    }

    fun enable(): HiiragiButton = also { setEnabled(true) }

    fun disable(): HiiragiButton = also { setEnabled(false) }

    fun setEnabled(boolean: Boolean) = also { enabled = boolean }

    fun show(): HiiragiButton = also { setVisible(true) }

    fun hide(): HiiragiButton = also { setVisible(false) }

    fun setVisible(visible: Boolean): HiiragiButton = also { this.visible = visible }

    override fun playPressSound(soundHandlerIn: SoundHandler) {
        if (visible && enabled) super.playPressSound(soundHandlerIn)
    }

    override fun drawButton(mc: Minecraft, mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (!visible) return
        hovered = mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height
        mc.textureManager.bindTexture(location)
        GlStateManager.disableDepth()
        this.drawTexturedModalRect(x, y, texU, texV, width, height)
        GlStateManager.enableDepth()
    }

}