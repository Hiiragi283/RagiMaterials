package hiiragi283.ragi_materials.client.render.tile

import hiiragi283.ragi_materials.block.BlockBlazingForge
import hiiragi283.ragi_materials.tile.TileBlazingForge
import hiiragi283.ragi_materials.util.RagiFacing
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class RenderBlazingForge : TileEntitySpecialRenderer<TileBlazingForge>() {

    private val location = ResourceLocation("minecraft", "textures/entity/blaze.png")

    val model = ModelBlazingForge()

    override fun render(te: TileBlazingForge, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {

            val state = te.world.getBlockState(te.pos)
            if (state.block is BlockBlazingForge) {
                val rotate = when (state.getValue(RagiFacing.HORIZONTAL)) {
                    EnumFacing.EAST -> -90.0f
                    EnumFacing.SOUTH -> 0.0f
                    EnumFacing.WEST -> 90.0f
                    else -> 180.0f
                }

                this.bindTexture(location)
                GlStateManager.pushMatrix()
                GlStateManager.enableRescaleNormal()
                GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
                GlStateManager.scale(1.2f, -1.2f, -1.2f)
                GlStateManager.rotate(rotate, 0.0f, 1.0f, 0.0f)
                model.render()
                GlStateManager.disableRescaleNormal()
                GlStateManager.popMatrix()
            }
        }
    }
}