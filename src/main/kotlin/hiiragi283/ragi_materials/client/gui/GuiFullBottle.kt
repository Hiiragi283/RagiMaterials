package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.container.ContainerFullBottle
import hiiragi283.ragi_materials.tile.TileFullBottleStation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiFullBottle(player: EntityPlayer, tile: TileFullBottleStation) : GuiBase<TileFullBottleStation>(ContainerFullBottle(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/fullbottle_station.png")

}