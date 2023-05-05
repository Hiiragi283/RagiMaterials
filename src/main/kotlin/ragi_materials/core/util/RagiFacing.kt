package ragi_materials.core.util

import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import ragi_materials.core.block.property.RagiProperty

object RagiFacing {

    fun getMeta(state: IBlockState): Int {
        return when (state.getValue(RagiProperty.HORIZONTAL)) {
            EnumFacing.EAST -> 1
            EnumFacing.SOUTH -> 2
            EnumFacing.WEST -> 3
            else -> 0
        }
    }

    fun getValue(meta: Int): EnumFacing = RagiProperty.HORIZONTAL.allowedValues.toList().let { it[meta % it.size] }

}