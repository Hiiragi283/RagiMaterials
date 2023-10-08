package hiiragi283.material.gui

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.gui.HiiragiGuiButton
import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.container.ContainerModuleMachine
import hiiragi283.material.network.HiiragiMessage
import hiiragi283.material.network.HiiragiNetworkWrapper
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.client.gui.GuiButton
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation


class GuiModuleMachine(tile: TileEntityModuleMachine, player: EntityPlayer) :
    HiiragiGuiContainer.TileEntity<TileEntityModuleMachine>(ContainerModuleMachine(tile, player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/module_machine.png")

    override fun initGui() {
        super.initGui()
        /*if (MachineTrait.PRIMITIVE in container.tile.machineProperty.machineTraits) {
            addButton(Button())
        }*/
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        //Input Tanks
        if (isPointInRegion(getSlotPosX(1), getSlotPosY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(0), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPosX(2), getSlotPosY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(1), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPosX(3), getSlotPosY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(2), mouseX, mouseY)
        }
        //Output Tanks
        if (isPointInRegion(getSlotPosX(5), getSlotPosY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(3), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPosX(6), getSlotPosY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(4), mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPosX(7), getSlotPosY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.getTank(5), mouseX, mouseY)
        }
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)
        //Input Slots
        fun drawItemSlotOverlay(index: Int) {
            if (container.tile.machineProperty.itemSlots > index) return
            drawTexturedModalRect(
                getOriginX() + getSlotPosX(index % 3 + 1) - 1,
                getOriginY() + getSlotPosY(index / 3) - 1,
                xSize,
                17,
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
        //Fluid Slots
        fun drawFluidSlotOverlay(index: Int) {
            if (container.tile.machineProperty.fluidSlots > index) return
            drawTexturedModalRect(
                getOriginX() + getSlotPosX(index % 3 + 1) - 1,
                getOriginY() + getSlotPosY(index / 3 + 2) - 1,
                xSize,
                17,
                18,
                18
            )
        }
        drawFluidSlotOverlay(0)
        drawFluidSlotOverlay(1)
        drawFluidSlotOverlay(2)
        //Progress Arrow
        drawTexturedModalRect(
            getOriginX() + getSlotPosX(4) - 1,
            getOriginY() + getSlotPosY(1) - 1,
            xSize,
            0,
            (18 * container.tile.getProgress()).toInt(),
            17
        )
        //Input Tanks
        drawFluid(container.tile.getTank(0), getSlotPosX(1), getSlotPosY(2))
        drawFluid(container.tile.getTank(1), getSlotPosX(2), getSlotPosY(2))
        drawFluid(container.tile.getTank(2), getSlotPosX(3), getSlotPosY(2))
        //Output Tanks
        drawFluid(container.tile.getTank(3), getSlotPosX(5), getSlotPosY(2))
        drawFluid(container.tile.getTank(4), getSlotPosX(6), getSlotPosY(2))
        drawFluid(container.tile.getTank(5), getSlotPosX(7), getSlotPosY(2))
    }

    override fun actionPerformed(button: GuiButton) {
        if (button is Button) {
            HiiragiNetworkWrapper.sendToServer(HiiragiMessage.Primitive(container.tile.pos))
        }
    }

    inner class Button : HiiragiGuiButton(this@GuiModuleMachine, -1, getSlotPosX(4) - 1, getSlotPosY(2) - 1, 18, 18)

}