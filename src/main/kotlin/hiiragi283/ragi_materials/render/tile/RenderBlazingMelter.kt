package hiiragi283.ragi_materials.render.tile

import hiiragi283.ragi_materials.block.BlockBlazingMelter
import hiiragi283.ragi_materials.tile.TileBlazingMelter
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation

class RenderBlazingMelter: TileEntitySpecialRenderer<TileBlazingMelter>() {

    private val location = ResourceLocation("minecraft", "textures/entity/blaze.png")

    val model = ModelBlazingMelter()

    override fun render(te: TileBlazingMelter, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {

            val state = te.world.getBlockState(te.pos)
            if (state.block is BlockBlazingMelter) {
                val rotate = when (state.getValue(BlockBlazingMelter.FACING)) {
                    EnumFacing.EAST -> -90.0f
                    EnumFacing.SOUTH -> 0.0f
                    EnumFacing.WEST -> 90.0f
                    else -> 180.0f
                }

                this.bindTexture(location)
                GlStateManager.pushMatrix()
                GlStateManager.enableRescaleNormal()
                GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
                GlStateManager.scale(1.0f, -1.0f, -1.0f)
                GlStateManager.rotate(rotate, 0.0f, 1.0f, 0.0f)
                model.render()
                GlStateManager.disableRescaleNormal()
                GlStateManager.popMatrix()
            }
        }
    }
}