package ragi_materials.metallurgy.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.client.gui.GuiBase
import ragi_materials.main.tile.TileFireboxPrimitive
import ragi_materials.metallurgy.container.ContainerFirebox

class GuiFirebox(player: EntityPlayer, tile: TileFireboxPrimitive) : GuiBase<TileFireboxPrimitive>(ContainerFirebox(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/fullbottle_station.png")

}