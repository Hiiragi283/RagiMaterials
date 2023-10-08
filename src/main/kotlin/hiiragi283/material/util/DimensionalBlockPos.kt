package hiiragi283.material.util

import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

data class DimensionalBlockPos(val dimId: Int, val pos: BlockPos) {

    constructor(dimId: Int, x: Int, y: Int, z: Int) : this(dimId, BlockPos(x, y, z))

    constructor(world: World, pos: BlockPos) : this(world.provider.dimension, pos)

    constructor(dimId: Int, x: Double, y: Double, z: Double) : this(dimId, BlockPos(x, y, z))

    val x: Int = pos.x
    val y: Int = pos.y
    val z: Int = pos.z

    fun isSameDimId(world: World): Boolean = world.provider.dimension == dimId

}