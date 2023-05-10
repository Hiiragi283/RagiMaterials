package hiiragi283.material.material

import hiiragi283.material.util.RagiColor
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

interface IMaterialBlock : IMaterialItem {

    fun getColor(world: IBlockAccess, pos: BlockPos, state: IBlockState, tintIndex: Int) =
        if (tintIndex == 0) getMaterialBlock(world, pos, state).color else RagiColor.WHITE

    fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState) = RagiMaterial.EMPTY

}