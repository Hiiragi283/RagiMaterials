package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.client.color.RagiColor
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.material.RagiMaterial
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

interface IMaterialBlock : IMaterialItem {

    fun getColor(world: IBlockAccess, pos: BlockPos, state: IBlockState, tintIndex: Int) = if (tintIndex == 0) getMaterialBlock(world, pos, state).color else RagiColor.WHITE

    fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState) = RagiMaterial.EMPTY

}