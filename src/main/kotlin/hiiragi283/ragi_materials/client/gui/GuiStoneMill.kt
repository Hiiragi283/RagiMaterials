package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.client.container.ContainerStoneMill
import hiiragi283.ragi_materials.tile.TileStoneMill
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiStoneMill(player: EntityPlayer, tile: TileStoneMill) : GuiBase<TileStoneMill>(ContainerStoneMill(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(Reference.MOD_ID, "textures/gui/stone_mill.png")

}