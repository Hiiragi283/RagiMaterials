package hiiragi283.core

import hiiragi283.api.container.HiiragiContainer
import hiiragi283.api.gui.HiiragiGui
import hiiragi283.chemistry.container.ContainerCrucible
import hiiragi283.chemistry.tile.TileEntityCrucible
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

object RMGuiHandler : IGuiHandler {

    const val TILE_ID = 0

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var container: HiiragiContainer<*>? = null
        if (ID == TILE_ID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    is TileEntityCrucible -> container = ContainerCrucible(player, tile)
                    else -> {}
                }
            }
        }
        return container
    }

    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var gui: HiiragiGui<*>? = null
        if (ID == TILE_ID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    else -> {}
                }
            }
        }
        return gui
    }

}