package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

interface IMaterialBlock {

    fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): MaterialBuilder
}