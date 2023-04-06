package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.inventory.container.ContainerLaboTable
import hiiragi283.ragi_materials.inventory.container.ContainerOreDictConv
import hiiragi283.ragi_materials.inventory.container.ContainerStoneMill
import hiiragi283.ragi_materials.inventory.gui.GuiLaboTable
import hiiragi283.ragi_materials.inventory.gui.GuiOreDictConv
import hiiragi283.ragi_materials.inventory.gui.GuiStoneMill
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import hiiragi283.ragi_materials.tile.TileLaboTable
import hiiragi283.ragi_materials.tile.TileOreDictConv
import hiiragi283.ragi_materials.tile.TileStoneMill
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

object RagiGuiHandler : IGuiHandler {

    const val RagiID = 0

    //    IGuiHandler    //

    //サーバー側にはContainerを返す
    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var container: Container? = null
        if (ID == RagiID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    is TileIndustrialLabo -> container = ContainerLaboTable(player, tile)
                    is TileLaboTable -> container = ContainerLaboTable(player, tile)
                    is TileOreDictConv -> container = ContainerOreDictConv(player, tile)
                    is TileStoneMill -> container = ContainerStoneMill(player, tile)
                    else -> {}
                }
            }
        }
        return container
    }

    //クライアント側にはGUIを返す
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var gui: GuiContainer? = null
        if (ID == RagiID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    is TileIndustrialLabo -> gui = GuiLaboTable(player, tile)
                    is TileLaboTable -> gui = GuiLaboTable(player, tile)
                    is TileOreDictConv -> gui = GuiOreDictConv(player, tile)
                    is TileStoneMill -> gui = GuiStoneMill(player, tile)
                    else -> {}
                }
            }
        }
        return gui
    }
}