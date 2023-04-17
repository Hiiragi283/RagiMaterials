package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.container.ContainerFirebox
import hiiragi283.ragi_materials.tile.TileFireboxPrimitive
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiFirebox(player: EntityPlayer, tile: TileFireboxPrimitive) : GuiBase<TileFireboxPrimitive>(ContainerFirebox(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/fullbottle_station.png")

}