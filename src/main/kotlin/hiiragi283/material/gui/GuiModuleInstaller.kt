package hiiragi283.material.gui

import hiiragi283.material.api.gui.HiiragiGuiButton
import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.container.ContainerModuleInstaller
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.installModule
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiModuleInstaller(player: EntityPlayer) :
    HiiragiGuiContainer<ContainerModuleInstaller>(ContainerModuleInstaller(player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/module_installer.png")

    override fun initGui() {
        super.initGui()
        addButton(HiiragiGuiButton(this, -1, getSlotPosX(5) - 1, getSlotPosY(1) - 1, 18, 18))
    }

    override fun getContainerTitle(): String = I18n.format("tile.module_installer.name")

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun actionPerformed(button: GuiButton) {
        if (button is HiiragiGuiButton) {
            container.itemHandler.setStackInSlot(
                3,
                installModule(
                    container.itemHandler.getStackInSlot(0),
                    container.itemHandler.getStackInSlot(1),
                    container.itemHandler.getStackInSlot(2)
                )
            )
            //TODO: Send packet to server...?
        }
    }

}