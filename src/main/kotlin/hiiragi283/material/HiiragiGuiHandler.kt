package hiiragi283.material

import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.block.BlockModuleInstaller
import hiiragi283.material.container.ContainerModuleInstaller
import hiiragi283.material.container.ContainerModuleMachine
import hiiragi283.material.gui.GuiModuleInstaller
import hiiragi283.material.gui.GuiModuleMachine
import hiiragi283.material.util.getTile
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

object HiiragiGuiHandler : IGuiHandler {

    const val TILE_ENTITY: Int = 0

    const val BLOCK: Int = 1

    override fun getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        val pos = BlockPos(x, y, z)
        return when (id) {
            TILE_ENTITY -> {
                getTile<TileEntity>(world, pos)?.let { tile: TileEntity ->
                    when (tile) {
                        is TileEntityModuleMachine -> ContainerModuleMachine(tile, player)
                        else -> null
                    }
                }
            }

            BLOCK -> {
                when (world.getBlockState(pos).block) {
                    is BlockModuleInstaller -> ContainerModuleInstaller(player)
                    else -> null
                }
            }

            else -> null
        }
    }

    override fun getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        val pos = BlockPos(x, y, z)
        return when (id) {
            TILE_ENTITY -> {
                getTile<TileEntity>(world, pos)?.let { tile: TileEntity ->
                    when (tile) {
                        is TileEntityModuleMachine -> GuiModuleMachine(tile, player)
                        else -> null
                    }
                }
            }

            BLOCK -> {
                when (world.getBlockState(pos).block) {
                    is BlockModuleInstaller -> GuiModuleInstaller(player)
                    else -> null
                }
            }

            else -> null
        }
    }

}