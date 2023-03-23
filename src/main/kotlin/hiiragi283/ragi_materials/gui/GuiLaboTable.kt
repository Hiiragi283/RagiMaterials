package hiiragi283.ragi_materials.gui

import hiiragi283.ragi_materials.base.TileLockableBase
import hiiragi283.ragi_materials.container.ContainerLaboTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiLaboTable(player: EntityPlayer, tile: TileLockableBase) : RagiGuiHandler<TileLockableBase>(ContainerLaboTable(player, tile)) {

    init {
        ySize = 133
    }

     override val background = ResourceLocation("minecraft:textures/gui/container/hopper.png")

}