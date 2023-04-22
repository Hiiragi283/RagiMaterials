package ragi_materials.metallurgy.client.render

import ragi_materials.metallurgy.block.BlockBlazingForge
import ragi_materials.metallurgy.client.model.tile.ModelBlazingForge
import ragi_materials.main.tile.TileBlazingForge
import ragi_materials.core.util.RagiFacing
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object RenderBlazingForge : TileEntitySpecialRenderer<TileBlazingForge>() {

    private val location = ResourceLocation("minecraft", "textures/entity/blaze.png")

    private var rotate = 180.0f

    override fun render(te: TileBlazingForge, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {
            val state = te.world.getBlockState(te.pos)
            if (state.block is BlockBlazingForge) {
                rotate = when (state.getValue(RagiFacing.HORIZONTAL)) {
                    EnumFacing.EAST -> -90.0f
                    EnumFacing.SOUTH -> 0.0f
                    EnumFacing.WEST -> 90.0f
                    else -> 180.0f
                }
                this.bindTexture(location)
                render(x, y, z)
            }
        }
    }

    fun render(x: Double, y: Double, z: Double) {
        GlStateManager.pushMatrix()
        GlStateManager.enableRescaleNormal()
        GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
        GlStateManager.scale(1.2f, -1.2f, -1.2f)
        GlStateManager.rotate(rotate, 0.0f, 1.0f, 0.0f)
        ModelBlazingForge.render()
        GlStateManager.disableRescaleNormal()
        GlStateManager.popMatrix()
    }
}