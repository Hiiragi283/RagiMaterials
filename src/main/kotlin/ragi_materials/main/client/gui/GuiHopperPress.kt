package ragi_materials.main.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.client.gui.GuiBase
import ragi_materials.main.container.ContainerHopperPress
import ragi_materials.main.tile.TileHopperPress

class GuiHopperPress(player: EntityPlayer, tile: TileHopperPress) : GuiBase<TileHopperPress>(ContainerHopperPress(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/fullbottle_station.png")

}