package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/gui/GuiIBCv3.java
*/

@SideOnly(Side.CLIENT)
class GuiIndustrialLabo(val tile: TileIndustrialLabo, val player: EntityPlayer) : GuiContainer(ContainerIndustrialLabo(tile, player)) {

    val texture = ResourceLocation(Reference.MOD_ID, "textures/gui/container/labo_table.png")

    //GUIに文字列を表示させるメソッド
    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        tile.displayName?.unformattedText?.let { this.fontRenderer.drawString(it, this.xSize / 2 - this.fontRenderer.getStringWidth(it) / 2, 6, 0x404040) }
        this.fontRenderer.drawString(this.player.inventory.displayName.unformattedText, 8, this.ySize - 96 + 2, 0x404040)
    }

    //GUIの背景を描画するメソッド
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        mc.textureManager.bindTexture(texture)
        val i = (width - xSize) / 2
        val j = (height - ySize) / 2
        this.drawTexturedModalRect(i, j, 0, 0, xSize, ySize)
    }

    //ホバー時に描画するメソッド
    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }
}