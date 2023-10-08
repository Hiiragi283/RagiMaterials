package hiiragi283.material.api.multiblock

import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import java.util.function.BiPredicate

sealed class MultiblockComponent(val displayState: IBlockState) : BiPredicate<World, BlockPos> {

    fun getPickBlock(): ItemStack {
        val minecraft: Minecraft = Minecraft.getMinecraft()
        val rayTraceResult: RayTraceResult = minecraft.objectMouseOver
        return displayState.block.getPickBlock(
            displayState,
            rayTraceResult,
            minecraft.world,
            rayTraceResult.blockPos,
            minecraft.player
        )
    }

    //    EMPTY    //

    object EMPTY : MultiblockComponent(Blocks.AIR.defaultState) {

        override fun test(t: World, u: BlockPos): Boolean = true

    }

    //    Tile    //

    class Tile(
        displayState: IBlockState,
        val predicate: (World, BlockPos) -> Boolean
    ) : MultiblockComponent(displayState) {

        override fun test(t: World, u: BlockPos): Boolean = predicate(t, u)

    }

    //    BlockState    //

    class State(
        displayState: IBlockState,
        val predicate: (IBlockState) -> Boolean
    ) : MultiblockComponent(displayState) {

        override fun test(t: World, u: BlockPos): Boolean = predicate(t.getBlockState(u))

    }

    //    Block    //

    class Block(val block: net.minecraft.block.Block) : MultiblockComponent(block.defaultState) {

        override fun test(t: World, u: BlockPos): Boolean = t.getBlockState(u).block == block

    }

}