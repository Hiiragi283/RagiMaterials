package hiiragi283.api.block

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

interface IHeatSource {

    fun getHeat(world: IBlockAccess, pos: BlockPos, state: IBlockState = world.getBlockState(pos)): Int

}