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
class RenderLaboratoryTable: TileEntitySpecialRenderer<TileLaboTable>() {

    private val location = ResourceLocation(Reference.MOD_ID, "textures/tiles/laboratory_table.png")

    val model = ModelLaboratoryTable()

    override fun render(te: TileLaboTable, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {

        //ワールド上のみで描画
        if (te.hasWorld()) {
            this.bindTexture(location)
            GlStateManager.pushMatrix()
            GlStateManager.enableRescaleNormal()
            GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat(), z.toFloat() + 0.5f)
            GlStateManager.scale(1.0f, -1.0f, -1.0f)
            model.render()
            GlStateManager.disableRescaleNormal()
            GlStateManager.popMatrix()

            if (!te.invLabo.getStackInSlot(0).isEmpty) {
                //スロット内のItemStackを取得
                val stack = te.invLabo.getStackInSlot(0)
                GlStateManager.pushMatrix()
                GlStateManager.translate(x.toFloat() + 0.3125f, y.toFloat() + 1.0f, z.toFloat() + 0.3125f)
                GlStateManager.scale(0.8f, 0.8f, 0.8f)
                Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND) //ItemStackを描画
                GlStateManager.popMatrix()
            }
            if (!te.invLabo.getStackInSlot(1).isEmpty) {
                //スロット内のItemStackを取得
                val stack = te.invLabo.getStackInSlot(1)
                GlStateManager.pushMatrix()
                GlStateManager.translate(x.toFloat() + 0.6875f, y.toFloat() + 1.0f, z.toFloat() + 0.3125f)
                GlStateManager.scale(0.8f, 0.8f, 0.8f)
                Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND) //ItemStackを描画
                GlStateManager.popMatrix()
            }
            if (!te.invLabo.getStackInSlot(2).isEmpty) {
                //スロット内のItemStackを取得
                val stack = te.invLabo.getStackInSlot(2)
                GlStateManager.pushMatrix()
                GlStateManager.translate(x.toFloat() + 0.6875f, y.toFloat() + 1.0f, z.toFloat() + 0.6875f)
                GlStateManager.scale(0.8f, 0.8f, 0.8f)
                Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND) //ItemStackを描画
                GlStateManager.popMatrix()
            }
            if (!te.invLabo.getStackInSlot(3).isEmpty) {
                //スロット内のItemStackを取得
                val stack = te.invLabo.getStackInSlot(3)
                GlStateManager.pushMatrix()
                GlStateManager.translate(x.toFloat() + 0.3125f, y.toFloat() + 1.0f, z.toFloat() + 0.6875f)
                GlStateManager.scale(0.8f, 0.8f, 0.8f)
                Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND) //ItemStackを描画
                GlStateManager.popMatrix()
            }
            if (!te.invLabo.getStackInSlot(4).isEmpty) {
                //スロット内のItemStackを取得
                val stack = te.invLabo.getStackInSlot(4)
                GlStateManager.pushMatrix()
                GlStateManager.translate(x.toFloat() + 0.5f, y.toFloat() + 1.0f, z.toFloat() + 0.5f)
                GlStateManager.scale(0.8f, 0.8f, 0.8f)
                Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.GROUND) //ItemStackを描画
                GlStateManager.popMatrix()
            }
        }
    }
}