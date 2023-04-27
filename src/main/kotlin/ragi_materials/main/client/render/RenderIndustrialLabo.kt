package ragi_materials.main.client.render

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.main.block.BlockIndustrialLabo
import ragi_materials.main.client.model.tile.ModelIndustrialLabo
import ragi_materials.main.tile.TileIndustrialLabo

@SideOnly(Side.CLIENT)
object RenderIndustrialLabo : TileEntitySpecialRenderer<TileIndustrialLabo>() {

    private val location = ResourceLocation(RagiMaterials.MOD_ID, "textures/tiles/industrial_labo.png")

    private var rotate = 180.0f

    override fun render(te: TileIndustrialLabo, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {
            val state = te.world.getBlockState(te.pos)
            if (state.block is BlockIndustrialLabo) {
                rotate = when (state.getValue(RagiProperty.HORIZONTAL)) {
                    EnumFacing.EAST -> -90.0f
                    EnumFacing.SOUTH -> 0.0f
                    EnumFacing.WEST -> 90.0f
                    else -> 180.0f
                }
                render(x, y, z)
            }
        }
    }

    fun render(x: Double, y: Double, z: Double) {
        this.bindTexture(location)
        GlStateManager.pushMatrix()
        GlStateManager.enableRescaleNormal()
        GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
        GlStateManager.scale(1.0f, -1.0f, -1.0f)
        GlStateManager.rotate(rotate, 0.0f, 1.0f, 0.0f)
        ModelIndustrialLabo.render()
        GlStateManager.disableRescaleNormal()
        GlStateManager.popMatrix()
    }
}