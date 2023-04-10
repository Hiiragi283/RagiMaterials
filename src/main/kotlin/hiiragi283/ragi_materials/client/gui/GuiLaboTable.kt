package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.container.ContainerLaboTable
import hiiragi283.ragi_materials.tile.TileLaboBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiLaboTable(player: EntityPlayer, tile: TileLaboBase) : GuiBase<TileLaboBase>(ContainerLaboTable(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(Reference.MOD_ID, "textures/gui/laboratory.png")

}