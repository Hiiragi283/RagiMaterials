package hiiragi283.material.api.gui

import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.HiiragiColor.setGLColor
import hiiragi283.material.util.drawSquareTexture
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.IFluidTank

abstract class HiiragiGuiContainer<T : HiiragiTileEntity>(val container: HiiragiContainer<T>) : GuiContainer(container) {

    abstract val backGround: ResourceLocation

    fun getOriginX(): Int = (width - xSize) / 2

    fun getOriginY(): Int = (height - ySize) / 2

    fun getSlotPositionX(index: Int): Int = 8 + 18 * index

    fun getSlotPositionY(index: Int): Int = 18 * (index + 1)

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        fontRenderer.drawString(container.tile.displayName?.unformattedText ?: "", 8, 6, HiiragiColor.DARK_GRAY.rgb)
        fontRenderer.drawString(
            container.inventoryPlayer.displayName.getUnformattedText(),
            8,
            ySize - 96 + 2,
            HiiragiColor.DARK_GRAY.rgb
        )
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        mc.textureManager.bindTexture(backGround)
        this.drawTexturedModalRect(getOriginX(), getOriginY(), 0, 0, xSize, ySize)
    }

    fun drawFluid(tank: IFluidTank, x: Int, y: Int) {
        drawFluid(tank.getFluid(), x, y)
    }

    fun drawFluid(fluidStack: FluidStack?, x: Int, y: Int) {
        if (fluidStack == null) return
        drawFluid(fluidStack.fluid, x, y)
    }

    fun drawFluid(fluid: Fluid, x: Int, y: Int) {
        setGLColor(fluid.color)
        drawSquareTexture(mc, getOriginX() + x, getOriginY() + y, fluid.still)
        setGLColor(HiiragiColor.WHITE)
    }

}