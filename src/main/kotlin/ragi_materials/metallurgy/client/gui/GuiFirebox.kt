package ragi_materials.metallurgy.client.gui

import ragi_materials.core.RagiMaterials
import ragi_materials.metallurgy.container.ContainerFirebox
import ragi_materials.main.tile.TileFireboxPrimitive
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.client.gui.GuiBase

class GuiFirebox(player: EntityPlayer, tile: TileFireboxPrimitive) : GuiBase<TileFireboxPrimitive>(ContainerFirebox(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/fullbottle_station.png")

}