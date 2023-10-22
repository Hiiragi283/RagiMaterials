package hiiragi283.material.init

import hiiragi283.material.block.BlockMachineWorkbench
import hiiragi283.material.client.gui.*
import hiiragi283.material.container.*
import hiiragi283.material.entity.EntityMinecartTank
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.tile.TileTransferStationFluid
import hiiragi283.material.tile.TileTransferStationItem
import hiiragi283.material.util.getTile
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

object HiiragiGuiHandler : IGuiHandler {

    const val TILE_ENTITY: Int = -1
    const val BLOCK: Int = -2

    override fun getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        //TileEntity -> Entity -> Blockの順に検索していく
        val pos = BlockPos(x, y, z)
        return getTile<TileEntity>(world, pos)?.let { tile: TileEntity ->
            when (tile) {
                is TileEntityModuleMachine -> ContainerModuleMachine(tile, player)
                is TileTransferStationFluid -> ContainerTransferStationFluid(tile, player)
                is TileTransferStationItem -> ContainerTransferStationItem(tile, player)
                else -> null
            }
        } ?: world.getEntityByID(id)?.let { entity: Entity ->
            when (entity) {
                is EntityMinecartTank -> ContainerMinecartTank(entity, player)
                else -> null
            }
        } ?: when (world.getBlockState(pos).block) {
            is BlockMachineWorkbench -> ContainerMachineWorkbench(player)
            else -> null
        }
    }

    override fun getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        //TileEntity -> Entity -> Blockの順に検索していく
        val pos = BlockPos(x, y, z)
        return getTile<TileEntity>(world, pos)?.let { tile: TileEntity ->
            when (tile) {
                is TileEntityModuleMachine -> GuiModuleMachine(tile, player)
                is TileTransferStationFluid -> GuiTransferStationFluid(tile, player)
                is TileTransferStationItem -> GuiTransferStationItem(tile, player)
                else -> null
            }
        } ?: world.getEntityByID(id)?.let { entity: Entity ->
            when (entity) {
                is EntityMinecartTank -> GuiMinecartTank(entity, player)
                else -> null
            }
        } ?: when (world.getBlockState(pos).block) {
            is BlockMachineWorkbench -> GuiMachineWorkbench(player)
            else -> null
        }
    }

}