package hiiragi283.material.api.multiblock

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.function.BiPredicate

sealed class MultiblockComponent(val displayStacks: () -> Collection<ItemStack>) : BiPredicate<World, BlockPos> {

    fun getDisplayStack(): Collection<ItemStack> = displayStacks()

    //    Tile    //

    class Tile(
        displayStacks: () -> Collection<ItemStack>,
        val predicate: (World, BlockPos) -> Boolean
    ) : MultiblockComponent(displayStacks) {

        override fun test(t: World, u: BlockPos): Boolean = predicate(t, u)

    }

    //    BlockState    //

    class State(
        displayStacks: () -> Collection<ItemStack>,
        val predicate: (IBlockState) -> Boolean
    ) : MultiblockComponent(displayStacks) {

        override fun test(t: World, u: BlockPos): Boolean = predicate(t.getBlockState(u))

    }

    class Block(val block: net.minecraft.block.Block) : MultiblockComponent({ listOf(ItemStack(block)) }) {

        override fun test(t: World, u: BlockPos): Boolean = t.getBlockState(u).block == block

    }

}