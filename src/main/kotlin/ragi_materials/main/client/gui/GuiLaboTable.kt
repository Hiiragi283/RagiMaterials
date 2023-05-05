package ragi_materials.main.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.client.gui.GuiBase
import ragi_materials.main.container.ContainerLaboTable
import ragi_materials.main.tile.TileLaboBase

class GuiLaboTable(player: EntityPlayer, tile: TileLaboBase) : GuiBase<TileLaboBase>(ContainerLaboTable(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/laboratory.png")
}