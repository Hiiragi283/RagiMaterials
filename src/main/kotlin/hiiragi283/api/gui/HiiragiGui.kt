package hiiragi283.api.gui

import hiiragi283.api.container.HiiragiContainer
import hiiragi283.api.tileentity.HiiragiTileEntity
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.drawFluid
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack


abstract class HiiragiGui<T : HiiragiTileEntity>(val container: HiiragiContainer<T>) : GuiContainer(container) {

    abstract val background: ResourceLocation

    fun getOriginX() = (width - xSize) / 2
    fun getOriginY() = (height - ySize) / 2

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        fontRenderer.drawString(container.tile.displayName!!.unformattedText, 8, 6, 0x404040)
        fontRenderer.drawString(container.player.inventory.displayName.unformattedText, 8, ySize - 96 + 2, 0x404040)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        mc.textureManager.bindTexture(background)
        this.drawTexturedModalRect(getOriginX(), getOriginY(), 0, 0, xSize, ySize)
    }

    /**
     * Thanks to defeatedcrow!
     * Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/gui/GuiIBCv3.java
     */

    //液体をGUi上に描画するメソッド
    fun renderFluid(fluid: Fluid, x: Int, y: Int) {
        //液体のTextureAtlasSpriteを取得する
        val textureMapBlocks: TextureMap = mc.textureMapBlocks
        val spr: TextureAtlasSprite =
            textureMapBlocks.getTextureExtry(fluid.still.toString()) ?: textureMapBlocks.missingSprite
        mc.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
        //TextureAtlasSpriteに液体の色を乗せる
        HiiragiColor.setGLColor(fluid.color)
        //TextureAtlasSpriteを描画する
        drawFluid(mc, x.toDouble(), y.toDouble(), spr)
        //着色をリセットする
        HiiragiColor.setGLColor(0xFFFFFF)
    }

    fun renderFluid(stack: FluidStack, x: Int, y: Int) {
        renderFluid(stack.fluid, x, y)
    }

}