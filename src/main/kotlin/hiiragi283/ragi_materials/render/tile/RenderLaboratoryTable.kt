package hiiragi283.ragi_materials.render.tile

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/RenderIBC.java
*/

@SideOnly(Side.CLIENT)
class RenderLaboratoryTable : TileEntitySpecialRenderer<TileLaboTable>() {

    private val location = ResourceLocation(Reference.MOD_ID, "textures/tiles/laboratory_table.png")

    private val listSlot = listOf(
            0.3125f to 0.3125f,
            0.6875f to 0.3125f,
            0.6875f to 0.6875f,
            0.3125f to 0.6875f,
            0.5f to 0.5f
    )

    //val model = ModelLaboratoryTable()

    override fun render(te: TileLaboTable, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        //ワールド上のみで描画
        if (te.hasWorld()) {
            /*
            this.bindTexture(location)
            GlStateManager.pushMatrix()
            GlStateManager.enableRescaleNormal()
            GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
            GlStateManager.scale(1.0f, -1.0f, -1.0f)
            model.render()
            GlStateManager.disableRescaleNormal()
            GlStateManager.popMatrix()
            */
            renderStack(te, x, y, z, 0)
            renderStack(te, x, y, z, 1)
            renderStack(te, x, y, z, 2)
            renderStack(te, x, y, z, 3)
            renderStack(te, x, y, z, 4)
        }
    }

    private fun renderStack(te: TileLaboTable, x: Double, y: Double, z: Double, slot: Int) {
        if (!te.invLabo.getStackInSlot(slot).isEmpty) {
            //スロット内のItemStackを取得
            val stack = te.invLabo.getStackInSlot(slot)
            GlStateManager.pushMatrix()
            GlStateManager.translate(x.toFloat() + listSlot[slot].first, y.toFloat() + 1.0f, z.toFloat() + listSlot[slot].second)
            GlStateManager.scale(0.8f, 0.8f, 0.8f)
            Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND) //ItemStackを描画
            GlStateManager.popMatrix()
        }
    }
}