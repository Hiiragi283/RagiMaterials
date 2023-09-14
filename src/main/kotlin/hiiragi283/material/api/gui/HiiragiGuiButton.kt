package hiiragi283.material.api.gui

import net.minecraft.client.gui.GuiButton

/**
 * Thanks to SkyTheory!
 * [: Source](https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/gui/AbstractButton.java)
 */

open class HiiragiGuiButton(
    gui: HiiragiGuiContainer<*>,
    buttonId: Int,
    x: Int,
    y: Int,
    widthIn: Int,
    heightIn: Int,
) : GuiButton(buttonId, gui.getGuiLeft() + x, gui.getGuiTop() + y, widthIn, heightIn, "")