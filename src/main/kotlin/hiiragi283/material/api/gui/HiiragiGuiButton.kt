package hiiragi283.material.api.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.ResourceLocation

/**
 * Thanks to SkyTheory!
 * [: Source](https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/gui/AbstractButton.java)
 */

open class HiiragiGuiButton(
    gui: HiiragiGuiContainer<*>,
    buttonId: Int,
    x: Int,
    y: Int,
    private val texU: Int,
    private val texV: Int,
    widthIn: Int,
    heightIn: Int,
    protected val texture: ResourceLocation
) : GuiButton(buttonId, gui.getGuiLeft() + x, gui.getGuiTop() + y, widthIn, heightIn, "") {

    override fun drawButton(mc: Minecraft, mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (visible) {
            hovered = mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height
            mc.textureManager.bindTexture(texture)
            GlStateManager.disableDepth()
            drawTexturedModalRect(x, y, texU, texV, width, height)
            GlStateManager.enableDepth()
        }
    }

}