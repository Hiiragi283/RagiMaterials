package hiiragi283.material.base

import hiiragi283.material.util.ColorUtil
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack


abstract class GuiBase<T : TileBase>(val container: ContainerBase<T>) : GuiContainer(container) {

    abstract val background: ResourceLocation

    fun getOriginX(): Int = (width - xSize) / 2
    fun getOriginY(): Int = (height - ySize) / 2

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
        val textureMapBlocks = mc.textureMapBlocks
        val spr = textureMapBlocks.getTextureExtry(fluid.still.toString()) ?: textureMapBlocks.missingSprite
        mc.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
        //TextureAtlasSpriteに液体の色を乗せる
        ColorUtil.setGLColor(fluid.color)
        //TextureAtlasSpriteを描画する
        drawFluidTexture(x.toDouble(), y.toDouble(), spr!!)
        //着色をリセットする
        ColorUtil.setGLColor(0xFFFFFF)
    }

    fun renderFluid(stack: FluidStack, x: Int, y: Int) {
        renderFluid(stack.fluid, x, y)
    }

    private fun drawFluidTexture(x: Double, y: Double, spr: TextureAtlasSprite) {
        //TextureAtlasSpriteのx座標の左端と右端，y座標の下端と上端をDoubleに変換する
        val uMin = spr.minU.toDouble()
        val uMax = spr.maxU.toDouble()
        val vMin = spr.minV.toDouble()
        val vMax = spr.maxV.toDouble()
        //GUiは2次元なのでz座標は適当?
        val z = 100.0
        //Tessellatorに設定を書き込んでいく
        val tessellator = Tessellator.getInstance()
        val vertexBuffer = tessellator.buffer
        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX)
        vertexBuffer.pos(x, y + 16, z).tex(uMin, vMax).endVertex() //左下
        vertexBuffer.pos(x + 16, y + 16, z).tex(uMax, vMax).endVertex() //右下
        vertexBuffer.pos(x + 16, y, z).tex(uMax, vMin).endVertex() //左上
        vertexBuffer.pos(x, y, z).tex(uMin, vMin).endVertex() //右上
        //いざ描画!!
        tessellator.draw()
    }
}