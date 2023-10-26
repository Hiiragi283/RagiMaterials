package hiiragi283.material.api.transfer

import net.minecraft.block.Block
import net.minecraft.block.BlockDirectional
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import java.util.function.Supplier

interface TransferPipe {

    val capabilities: Supplier<Collection<Capability<*>>>

    fun getTerminalPos(world: World, pos: BlockPos, state: IBlockState): BlockPos {
        val posTo: BlockPos = pos.offset(state.getValue(BlockDirectional.FACING))
        val stateTo: IBlockState = world.getBlockState(posTo)
        val blockTo: Block = stateTo.block
        return if (blockTo is TransferPipe && blockTo.capabilities.get().containsAll(capabilities.get())) {
            blockTo.getTerminalPos(world, posTo, stateTo)
        } else posTo
    }

}