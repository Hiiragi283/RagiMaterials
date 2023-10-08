package hiiragi283.material.api.transfer

import net.minecraft.block.Block
import net.minecraft.block.BlockDirectional
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability

interface TransferPipe {

    val capabilities: () -> Collection<Capability<*>>

    fun getTerminalPos(world: World, pos: BlockPos, state: IBlockState): BlockPos {
        val posTo: BlockPos = pos.offset(state.getValue(BlockDirectional.FACING))
        val stateTo: IBlockState = world.getBlockState(posTo)
        val blockTo: Block = stateTo.block
        return if (blockTo is TransferPipe && blockTo.capabilities().containsAll(capabilities())) {
            blockTo.getTerminalPos(world, posTo, stateTo)
        } else posTo
    }

}