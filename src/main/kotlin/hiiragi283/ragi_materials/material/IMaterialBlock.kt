package hiiragi283.ragi_materials.material

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IMaterialBlock {

    fun getMaterialBlock(world: World, pos: BlockPos, state: IBlockState): MaterialBuilder
}