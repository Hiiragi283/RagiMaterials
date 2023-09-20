package hiiragi283.material.gui

import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.container.ContainerMachineWorkbench
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiMachineWorkbench(player: EntityPlayer) :
    HiiragiGuiContainer<ContainerMachineWorkbench>(ContainerMachineWorkbench(player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/module_installer.png")

    override fun getContainerTitle(): String = I18n.format("tile.machine_workbench.name")

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

}