package hiiragi283.material.gui

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.container.ContainerModuleMachine
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack


class GuiModuleMachine(tile: TileEntityModuleMachine, player: EntityPlayer) :
    HiiragiGuiContainer<TileEntityModuleMachine>(ContainerModuleMachine(tile, player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/module_machine.png")

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        //Input Tanks
        if (isPointInRegion(getSlotPositionX(1), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput0, mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(2), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput1, mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(3), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput2, mouseX, mouseY)
        }
        //Output Tanks
        if (isPointInRegion(getSlotPositionX(5), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankOutput0, mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(6), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankOutput1, mouseX, mouseY)
        }
        if (isPointInRegion(getSlotPositionX(7), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankOutput2, mouseX, mouseY)
        }
        renderHoveredToolTip(mouseX, mouseY)
    }

    private fun drawFluidTooltip(tank: HiiragiFluidTank, mouseX: Int, mouseY: Int) {
        tank.fluid?.let { fluidStack: FluidStack ->
            val list: MutableList<String> = mutableListOf()
            list.add(I18n.format(fluidStack.fluid.unlocalizedName))
            list.add(fluidStack.amount.toString() + " mB")
            drawHoveringText(list, mouseX, mouseY)
        }
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)
        //Input Tanks
        drawFluid(container.tile.tankInput0, getSlotPositionX(1), getSlotPositionY(2))
        drawFluid(container.tile.tankInput1, getSlotPositionX(2), getSlotPositionY(2))
        drawFluid(container.tile.tankInput2, getSlotPositionX(3), getSlotPositionY(2))
        //Output Tanks
        drawFluid(container.tile.tankOutput0, getSlotPositionX(5), getSlotPositionY(2))
        drawFluid(container.tile.tankOutput1, getSlotPositionX(6), getSlotPositionY(2))
        drawFluid(container.tile.tankOutput2, getSlotPositionX(7), getSlotPositionY(2))
        //Progress Arrow
        drawTexturedModalRect(
            getOriginX() + getSlotPositionX(4) - 1,
            getOriginY() + getSlotPositionY(1) - 1,
            xSize,
            0,
            (18 * container.tile.getProgress()).toInt(),
            18
        )
    }

}