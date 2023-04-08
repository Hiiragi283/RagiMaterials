package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.inventory.container.ContainerLaboTableNew
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiLaboTableNew(player: EntityPlayer, tile: TileLaboTable) : GuiItemHandlerBase<TileLaboTable>(ContainerLaboTableNew(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation("minecraft:textures/gui/container/hopper.png")

}