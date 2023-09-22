package hiiragi283.material.gui

import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.container.ContainerMinecartTank
import hiiragi283.material.entity.EntityMinecartTank
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiMinecartTank(entity: EntityMinecartTank, player: EntityPlayer) :
    HiiragiGuiContainer<ContainerMinecartTank>(ContainerMinecartTank(entity, player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/slot1.png")

    init {
        ySize = 133
    }

    override fun getContainerTitle(): String = container.entity.displayName.unformattedText

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        //Tank
        if (isPointInRegion(80, 20, 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.entity.tank.fluid, mouseX, mouseY)
        }
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)
        drawFluid(container.entity.tank, 80, 20)
    }

}