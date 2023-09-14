package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlockContainer
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.tile.TileEntityMachineExtender
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object BlockMachineExtender : HiiragiBlockContainer<TileEntityMachineExtender>(
    Material.IRON,
    "machine_extender",
    { TileEntityMachineExtender() }
) {

    override var itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, 0)

    init {
        defaultState = defaultState.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH)
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, BlockHorizontal.FACING)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(BlockHorizontal.FACING).horizontalIndex

    override fun getStateForPlacement(
        world: World,
        pos: BlockPos,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        meta: Int,
        placer: EntityLivingBase,
        hand: EnumHand
    ): IBlockState = defaultState.withProperty(BlockHorizontal.FACING, placer.horizontalFacing)


    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "defaultState.withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta))",
            "net.minecraft.block.BlockHorizontal",
            "net.minecraft.util.EnumFacing"
        )
    )
    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta))

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "state.withRotation(mirrorIn.toRotation(state.getValue(BlockHorizontal.FACING)))",
            "net.minecraft.block.BlockHorizontal"
        )
    )
    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState =
        state.withRotation(mirrorIn.toRotation(state.getValue(BlockHorizontal.FACING)))

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "state.withProperty(BlockHorizontal.FACING, rot.rotate(state.getValue(BlockHorizontal.FACING)))",
            "net.minecraft.block.BlockHorizontal",
            "net.minecraft.block.BlockHorizontal"
        )
    )
    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState =
        state.withProperty(BlockHorizontal.FACING, rot.rotate(state.getValue(BlockHorizontal.FACING)))

}