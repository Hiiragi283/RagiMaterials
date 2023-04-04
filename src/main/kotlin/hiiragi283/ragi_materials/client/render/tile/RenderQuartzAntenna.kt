package hiiragi283.ragi_materials.client.render.tile

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.tile.TileQuartzAntenna
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class RenderQuartzAntenna : TileEntitySpecialRenderer<TileQuartzAntenna>() {

    private val location = ResourceLocation(Reference.MOD_ID, "textures/tiles/quartz_antenna.png")

    val model = ModelQuartzAntenna()

    override fun render(te: TileQuartzAntenna, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {
            this.bindTexture(location)
            GlStateManager.pushMatrix()
            GlStateManager.enableRescaleNormal()
            GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
            GlStateManager.scale(1.0f, 1.0f, -1.0f)
            //model.render(te.getRotateX(), te.getRotateY())
            GlStateManager.disableRescaleNormal()
            GlStateManager.popMatrix()
        }
    }
}