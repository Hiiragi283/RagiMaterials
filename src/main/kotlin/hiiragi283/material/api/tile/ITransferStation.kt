package hiiragi283.material.api.tile

import hiiragi283.material.api.block.ITransferPipe
import hiiragi283.material.util.getTile
import net.minecraft.block.Block
import net.minecraft.block.BlockDirectional
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface ITransferStation {

    fun getFacing(state: IBlockState): EnumFacing = state.getValue(BlockDirectional.FACING)

    fun getTerminalPos(world: World, pos: BlockPos): BlockPos {
        val posTo: BlockPos = pos.offset(getFacing(world.getBlockState(pos)))
        val stateTo: IBlockState = world.getBlockState(posTo)
        return when (val blockTo: Block = stateTo.block) {
            is ITransferPipe -> blockTo.getTerminalPos(world, posTo, stateTo)
            else -> posTo
        }
    }

    fun getTerminalTile(world: World, pos: BlockPos): TileEntity? = getTile(world, getTerminalPos(world, pos))

}