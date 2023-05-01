package ragi_materials.metallurgy.client.render

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.metallurgy.client.model.tile.ModelBlastFurnace
import ragi_materials.metallurgy.tile.TileBlastFurnaceInterface

@SideOnly(Side.CLIENT)
object RenderBlastFurnace : TileEntitySpecialRenderer<TileBlastFurnaceInterface>() {

    private val location = ResourceLocation("minecraft", "textures/blocks/coal_block.png")

    override fun render(te: TileBlastFurnaceInterface, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        if (te.hasWorld()) {

            val ore = te.inputOre.getStackInSlot(0).count
            val fuel = te.inputFuel.getStackInSlot(0).count
            val flux = te.inputFlux.getStackInSlot(0).count
            val slug = te.output.getStackInSlot(0).count
            val count = (ore + fuel + flux + slug) / 128.0f

            if (count > 0.0f) {
                this.bindTexture(location)
                GlStateManager.pushMatrix()
                GlStateManager.enableRescaleNormal()
                GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat() - 1.0f + count, z.toFloat() + 0.5f)
                GlStateManager.scale(1.0f, -1.0f, -1.0f)
                ModelBlastFurnace.render()
                GlStateManager.disableRescaleNormal()
                GlStateManager.popMatrix()
            }
        }
    }
}