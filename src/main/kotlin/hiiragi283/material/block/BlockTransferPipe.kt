package hiiragi283.material.block

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.block.ITransferPipe
import net.minecraft.block.BlockDirectional
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
import net.minecraftforge.common.capabilities.Capability

class BlockTransferPipe(
    type: String,
    override val capabilities: () -> Collection<Capability<*>>
) : HiiragiBlock(Material.IRON, "pipe_$type"), ITransferPipe {

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
        defaultState = blockState.baseState.withProperty(BlockDirectional.FACING, EnumFacing.NORTH)
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, BlockDirectional.FACING)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(BlockDirectional.FACING).index

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
    ): IBlockState =
        defaultState.withProperty(BlockDirectional.FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "defaultState.withProperty(BlockDirectional.FACING, EnumFacing.byIndex(meta))",
            "net.minecraft.block.BlockDirectional",
            "net.minecraft.util.EnumFacing"
        )
    )
    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(BlockDirectional.FACING, EnumFacing.byIndex(meta))

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "state.withProperty(BlockDirectional.FACING, mirrorIn.mirror(state.getValue(BlockDirectional.FACING)))",
            "net.minecraft.block.BlockDirectional",
            "net.minecraft.block.BlockDirectional"
        )
    )
    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState = state.withProperty(
        BlockDirectional.FACING,
        mirrorIn.mirror(state.getValue(BlockDirectional.FACING))
    )

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "state.withProperty(BlockDirectional.FACING, rot.rotate(state.getValue(BlockDirectional.FACING)))",
            "net.minecraft.block.BlockDirectional",
            "net.minecraft.block.BlockDirectional"
        )
    )
    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState = state.withProperty(
        BlockDirectional.FACING,
        rot.rotate(state.getValue(BlockDirectional.FACING))
    )

}