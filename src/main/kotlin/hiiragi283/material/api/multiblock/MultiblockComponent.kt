package hiiragi283.material.api.multiblock

import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import java.util.function.BiPredicate
import java.util.function.Predicate

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

        override fun test(world: World, pos: BlockPos): Boolean = world.isAirBlock(pos)

    }

    //    WILDCARD    //

    object WILDCARD : MultiblockComponent(Blocks.AIR.defaultState) {

        override fun test(world: World, pos: BlockPos): Boolean = true

    }


    //    Tile    //

    class Tile(
        displayState: IBlockState,
        private val predicate: BiPredicate<World, BlockPos>
    ) : MultiblockComponent(displayState) {

        override fun test(world: World, pos: BlockPos): Boolean = predicate.test(world, pos)

    }

    //    BlockState    //

    class State(
        displayState: IBlockState,
        private val predicate: Predicate<IBlockState>
    ) : MultiblockComponent(displayState) {

        override fun test(world: World, pos: BlockPos): Boolean = predicate.test(world.getBlockState(pos))

    }

    //    Block    //

    class Block(val block: net.minecraft.block.Block) : MultiblockComponent(block.defaultState) {

        override fun test(world: World, pos: BlockPos): Boolean = world.getBlockState(pos).block == block

    }

}