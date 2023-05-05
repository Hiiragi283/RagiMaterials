package ragi_materials.metallurgy.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.client.gui.GuiBase
import ragi_materials.metallurgy.container.ContainerBlastFurnace
import ragi_materials.metallurgy.tile.TileBlastFurnaceInterface

class GuiBlastFurnace(player: EntityPlayer, val tile: TileBlastFurnaceInterface) : GuiBase<TileBlastFurnaceInterface>(ContainerBlastFurnace(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/blast_furnace.png")

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        //カーソルが液体上にある場合
        val list: MutableList<String> = mutableListOf()
        if (isPointInRegion(8 + 5 * 18, 20, 16, 16, mouseX, mouseY)) {
            //液体が存在する場合，その情報を表示させる
            tile.tank.getFluid(0)?.let {
                list.add(it.localizedName)
                list.add("${it.amount} mb")
            }
        }
        drawHoveringText(list, mouseX, mouseY)
        renderHoveredToolTip(mouseX, mouseY)
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)
        //液体を描画する
        tile.tank.getFluid(0)?.let { renderFluid(it, getOriginX() + 8 + 5 * 18, getOriginY() + 20) }
    }
}