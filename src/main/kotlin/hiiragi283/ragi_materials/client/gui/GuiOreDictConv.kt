package hiiragi283.ragi_materials.client.gui

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.container.ContainerOreDictConv
import hiiragi283.ragi_materials.tile.TileOreDictConv
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation

class GuiOreDictConv(player: EntityPlayer, tile: TileOreDictConv) : GuiBase<TileOreDictConv>(ContainerOreDictConv(player, tile)) {

    init {
        ySize = 133
    }

    override val background = ResourceLocation(Reference.MOD_ID, "textures/gui/stone_mill.png")

}