package hiiragi283.material.api.gui

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.HiiragiColor.setGLColor
import hiiragi283.material.util.drawSquareTexture
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.IFluidTank

abstract class HiiragiGuiContainer<T : HiiragiContainer>(open val container: T) : GuiContainer(container) {

    abstract val backGround: ResourceLocation

    fun getOriginX(): Int = (width - xSize) / 2

    fun getOriginY(): Int = (height - ySize) / 2

    fun getSlotPosX(index: Int): Int = 8 + 18 * index

    fun getSlotPosY(index: Int): Int = 18 * (index + 1)

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        fontRenderer.drawString(getContainerTitle(), 8, 6, HiiragiColor.DARK_GRAY.rgb)
        fontRenderer.drawString(
            container.inventoryPlayer.displayName.getUnformattedText(),
            8,
            ySize - 96 + 2,
            HiiragiColor.DARK_GRAY.rgb
        )
    }

    abstract fun getContainerTitle(): String

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        mc.textureManager.bindTexture(backGround)
        this.drawTexturedModalRect(getOriginX(), getOriginY(), 0, 0, xSize, ySize)
    }

    fun drawFluidTooltip(tank: HiiragiFluidTank, mouseX: Int, mouseY: Int) {
        drawFluidTooltip(tank.fluid, mouseX, mouseY)
    }

    fun drawFluidTooltip(fluidStack: FluidStack?, mouseX: Int, mouseY: Int) {
        fluidStack?.let { stack: FluidStack ->
            val list: MutableList<String> = mutableListOf()
            list.add(I18n.format(stack.fluid.unlocalizedName))
            list.add(stack.amount.toString() + " mB")
            drawHoveringText(list, mouseX, mouseY)
        }
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

    abstract class TileEntity<T : HiiragiTileEntity>(
        override val container: HiiragiContainer.TileEntity<T>
    ) : HiiragiGuiContainer<HiiragiContainer.TileEntity<T>>(container) {

        override fun getContainerTitle(): String = container.tile.displayName?.unformattedText ?: ""

    }

}