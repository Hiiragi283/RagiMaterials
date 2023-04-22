package ragi_materials.main.client.gui

import ragi_materials.core.RagiMaterials
import ragi_materials.main.container.ContainerFullBottle
import ragi_materials.main.tile.TileFullBottleStation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.client.gui.GuiBase

class GuiFullBottle(player: EntityPlayer, tile: TileFullBottleStation) : GuiBase<TileFullBottleStation>(ContainerFullBottle(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/fullbottle_station.png")

}