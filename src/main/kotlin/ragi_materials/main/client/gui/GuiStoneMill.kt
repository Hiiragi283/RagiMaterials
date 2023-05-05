package ragi_materials.main.client.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.client.gui.GuiBase
import ragi_materials.main.container.ContainerStoneMill
import ragi_materials.main.tile.TileStoneMill

class GuiStoneMill(player: EntityPlayer, tile: TileStoneMill) : GuiBase<TileStoneMill>(ContainerStoneMill(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/stone_mill.png")

}