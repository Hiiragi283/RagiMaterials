package hiiragi283.chemistry.gui

import hiiragi283.api.gui.HiiragiGui
import hiiragi283.chemistry.container.ContainerCrucible
import hiiragi283.chemistry.tile.TileEntityCrucible
import hiiragi283.core.util.hiiragiLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiCrucible(player: EntityPlayer, tile: TileEntityCrucible) : HiiragiGui<TileEntityCrucible>(ContainerCrucible(player, tile)) {

    init {
        ySize = 133
    }

    override val background: ResourceLocation = hiiragiLocation("textures/gui/slot1.png")

}