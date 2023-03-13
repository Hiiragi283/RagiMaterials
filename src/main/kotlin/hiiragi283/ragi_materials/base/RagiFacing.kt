package hiiragi283.ragi_materials.base

import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing

object RagiFacing {

    //0 -> NORTH, 1 -> EAST, 2 -> SOUTH, 3 -> WEST
    val HORIZONTAL: PropertyDirection = PropertyDirection.create("facing",
            listOf(EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST)
    )

    fun getMeta(state: IBlockState): Int {
        return when (state.getValue(HORIZONTAL)) {
            EnumFacing.EAST -> 1
            EnumFacing.SOUTH -> 2
            EnumFacing.WEST -> 3
            else -> 0
        }
    }

    fun getState(meta: Int): EnumFacing = HORIZONTAL.allowedValues.toList()[meta]

}