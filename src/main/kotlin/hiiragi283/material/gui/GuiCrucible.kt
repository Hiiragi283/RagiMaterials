package hiiragi283.material.gui

import hiiragi283.api.gui.HiiragiGui
import hiiragi283.material.container.ContainerCrucible
import hiiragi283.material.tile.TileEntityCrucible
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiCrucible(player: EntityPlayer, tile: TileEntityCrucible) : HiiragiGui<TileEntityCrucible>(ContainerCrucible(player, tile)) {

    init {
        ySize = 133
    }

    override val background: ResourceLocation = hiiragiLocation("textures/gui/slot1.png")

}