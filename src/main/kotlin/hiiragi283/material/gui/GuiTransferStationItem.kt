package hiiragi283.material.gui

import hiiragi283.material.api.gui.HiiragiGuiContainer
import hiiragi283.material.container.ContainerTransferStationItem
import hiiragi283.material.tile.TileTransferStationItem
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiTransferStationItem(tile: TileTransferStationItem, player: EntityPlayer) :
    HiiragiGuiContainer.TileEntity<TileTransferStationItem>(ContainerTransferStationItem(tile, player)) {

    override val backGround: ResourceLocation = hiiragiLocation("textures/gui/slot1.png")

    init {
        ySize = 133
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

}