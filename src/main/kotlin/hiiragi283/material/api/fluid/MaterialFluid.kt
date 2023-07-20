package hiiragi283.material.api.fluid

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView


class MaterialFluid(val material: HiiragiMaterial = HiiragiMaterial.EMPTY) : Fluid(), HiiragiEntry.FLUID {

    override fun getIdentifier(): Identifier = hiiragiId(material.name)

    private val bucket = MaterialBucketItem.of(this, material)

    override fun register(): Fluid {
        bucket.register()
        return super.register()
    }

    override fun getBucketItem(): Item = bucket

    override fun canBeReplacedWith(
        state: FluidState,
        world: BlockView,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction
    ): Boolean = true

    override fun getVelocity(world: BlockView, pos: BlockPos, state: FluidState): Vec3d = Vec3d.ZERO

    override fun getTickRate(world: WorldView): Int = 0

    override fun getBlastResistance(): Float = 0.0f

    override fun getHeight(state: FluidState, world: BlockView, pos: BlockPos): Float = 0.0f

    override fun getHeight(state: FluidState): Float = 0.0f

    override fun toBlockState(state: FluidState): BlockState = Blocks.AIR.defaultState

    override fun isStill(state: FluidState): Boolean = false

    override fun getLevel(state: FluidState): Int = 0

    override fun getShape(state: FluidState, world: BlockView, pos: BlockPos): VoxelShape = VoxelShapes.empty()

}