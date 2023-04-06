package hiiragi283.ragi_materials.client.render

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.client.model.tile.ModelEnergyCrystal
import hiiragi283.ragi_materials.tile.TileQuartzAntenna
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object RenderQuartzAntenna : TileEntitySpecialRenderer<TileQuartzAntenna>() {

    private val location = ResourceLocation(Reference.MOD_ID, "textures/tiles/energy_crystal.png")

    val model = ModelEnergyCrystal

    override fun render(te: TileQuartzAntenna, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {
            this.bindTexture(location)
            GlStateManager.pushMatrix()
            GlStateManager.enableRescaleNormal()
            GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat() + 0.5f, z.toFloat() + 0.5f)
            GlStateManager.scale(1.0f, 1.0f, -1.0f)
            ModelEnergyCrystal.render()
            GlStateManager.disableRescaleNormal()
            GlStateManager.popMatrix()
        }
    }
}