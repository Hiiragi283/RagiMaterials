package hiiragi283.material.api.fluid

import hiiragi283.material.api.HiiragiEntry
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView


abstract class HiiragiFluid : FlowableFluid(), HiiragiEntry.FLUID {

    //    FlowableFluid    //

    override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
        Block.dropStacks(state, world, pos, world.getBlockEntity(pos))
    }

    override fun canBeReplacedWith(
        fluidState: FluidState,
        blockView: BlockView,
        blockPos: BlockPos,
        fluid: Fluid,
        direction: Direction
    ): Boolean = false

    override fun getBlastResistance(): Float = 100.0f

    override fun getFlowSpeed(world: WorldView): Int = 4

    override fun getLevelDecreasePerBlock(world: WorldView): Int = 1

    override fun getTickRate(world: WorldView): Int = 5

    override fun isInfinite(): Boolean = false

    override fun matchesType(fluid: Fluid?): Boolean = fluid == still || fluid == flowing

}