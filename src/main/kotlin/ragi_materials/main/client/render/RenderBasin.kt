package ragi_materials.main.client.render

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11
import ragi_materials.core.util.ColorUtil
import ragi_materials.main.tile.TileBasin

/**
 * Thanks to defeatedcrow!
 * Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/machine/client/IBCTESR.java#L117
 */

@SideOnly(Side.CLIENT)
object RenderBasin : TileEntitySpecialRenderer<TileBasin>() {

    private val MC: Minecraft = Minecraft.getMinecraft()

    override fun render(te: TileBasin, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        if (te.hasWorld()) {
            val stack = te.tankBasin.fluid
            if (stack !== null) {

                val fluid = stack.fluid
                val textureMapBlocks = MC.textureMapBlocks
                val spr = textureMapBlocks.getTextureExtry(fluid.still.toString()) ?: textureMapBlocks.missingSprite
                this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
                val uMin = spr.minU.toDouble()
                val uMax = spr.maxU.toDouble()
                val vMin = spr.minV.toDouble()
                val vMax = spr.maxV.toDouble()

                GlStateManager.disableLighting()
                GlStateManager.pushMatrix()
                GL11.glEnable(GL11.GL_BLEND)
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
                ColorUtil.setGLColor(fluid.color)
                GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat() + 0.9f, z.toFloat() + 0.5f)

                val tessellator = Tessellator.getInstance()
                val vertexBuffer = tessellator.buffer
                vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX)
                vertexBuffer.pos(0.5, 0.0, -0.5).tex(uMin, vMax).endVertex()
                vertexBuffer.pos(-0.5, 0.0, -0.5).tex(uMin, vMin).endVertex()
                vertexBuffer.pos(-0.5, 0.0, 0.5).tex(uMax, vMin).endVertex()
                vertexBuffer.pos(0.5, 0.0, 0.5).tex(uMax, vMax).endVertex()
                tessellator.draw()

                GL11.glDisable(GL11.GL_BLEND)
                GlStateManager.popMatrix()
                GlStateManager.enableLighting()
            }
        }
    }
}