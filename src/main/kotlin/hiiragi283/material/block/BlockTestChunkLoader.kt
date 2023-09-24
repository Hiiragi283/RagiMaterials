package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.chunk.HiiragiChunkLoadCallback
import hiiragi283.material.chunk.IBlockChunkLoader
import hiiragi283.material.util.DimensionalBlockPos
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object BlockTestChunkLoader : HiiragiBlock(Material.WOOD, "chunk_loader"), IBlockChunkLoader {

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        super.breakBlock(world, pos, state)
        HiiragiChunkLoadCallback.inactivateBlockTicket(world, DimensionalBlockPos(world, pos))
    }

    override fun onBlockAdded(world: World, pos: BlockPos, state: IBlockState) {
        super.onBlockAdded(world, pos, state)
        HiiragiChunkLoadCallback.activateBlockTicket(world, DimensionalBlockPos(world, pos))
    }

    //    IBlockChunkLoader    //

    override fun canLoad(world: World, pos: BlockPos): Boolean = true

}