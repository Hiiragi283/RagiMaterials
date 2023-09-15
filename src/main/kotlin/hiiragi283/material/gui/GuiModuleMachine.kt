package hiiragi283.material.gui

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.container.ContainerModuleMachine
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation


class GuiModuleMachine(tile: TileEntityModuleMachine, player: EntityPlayer) :
    HiiragiGuiContainer<TileEntityModuleMachine>(ContainerModuleMachine(tile, player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/module_machine.png")

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        //Input Tanks
        if (isPointInRegion(getSlotPositionX(1), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(0), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(2), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(1), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(3), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(2), mouseX, mouseY)
        }
        //Output Tanks
        if (isPointInRegion(getSlotPositionX(5), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(3), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(6), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(4), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(7), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(5), mouseX, mouseY)
        }
        renderHoveredToolTip(mouseX, mouseY)
    }

    private fun drawFluidTooltip(tank: HiiragiFluidTank, mouseX: Int, mouseY: Int) {
        drawFluidTooltip(tank.fluid, mouseX, mouseY)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)
        //Input Slots
        fun drawItemSlotOverlay(index: Int) {
            if (container.tile.machineProperty.itemSlots <= index) return
            drawTexturedModalRect(
                getOriginX() + getSlotPositionX(index % 3 + 1) - 1,
                getOriginY() + getSlotPositionY(index / 3) - 1,
                xSize + 18 * (index % 3),
                18 * (index / 3 + 1) - 1,
                18,
                18
            )
        }
        drawItemSlotOverlay(0)
        drawItemSlotOverlay(1)
        drawItemSlotOverlay(2)
        drawItemSlotOverlay(3)
        drawItemSlotOverlay(4)
        drawItemSlotOverlay(5)
        //Input Tanks
        fun drawFluidSlotOverlay(index: Int) {
            if (container.tile.machineProperty.fluidSlots <= index) return
            drawTexturedModalRect(
                getOriginX() + getSlotPositionX(index % 3 + 1) - 1,
                getOriginY() + getSlotPositionY(index / 3 + 2) - 1,
                xSize + 18 * (index % 3),
                18 * (index / 3 + 3) - 1,
                18,
                18
            )
        }
        drawFluidSlotOverlay(0)
        drawFluidSlotOverlay(1)
        drawFluidSlotOverlay(2)
        drawFluid(container.tile.getTank(0), getSlotPositionX(1), getSlotPositionY(2))
        drawFluid(container.tile.getTank(1), getSlotPositionX(2), getSlotPositionY(2))
        drawFluid(container.tile.getTank(2), getSlotPositionX(3), getSlotPositionY(2))
        //Output Tanks
        drawFluid(container.tile.getTank(3), getSlotPositionX(5), getSlotPositionY(2))
        drawFluid(container.tile.getTank(4), getSlotPositionX(6), getSlotPositionY(2))
        drawFluid(container.tile.getTank(5), getSlotPositionX(7), getSlotPositionY(2))
        //Progress Arrow
        drawTexturedModalRect(
            getOriginX() + getSlotPositionX(4) - 1,
            getOriginY() + getSlotPositionY(1) - 1,
            xSize,
            0,
            (18 * container.tile.getProgress()).toInt(),
            17
        )
    }

}