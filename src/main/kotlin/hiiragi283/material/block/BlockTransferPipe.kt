package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.transfer.TransferPipe
import hiiragi283.material.init.HiiragiCreativeTabs
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
import java.util.function.Supplier

@Suppress("OVERRIDE_DEPRECATION")
class BlockTransferPipe(
    type: String,
    override val capabilities: Supplier<Collection<Capability<*>>>
) : HiiragiBlock(Material.IRON, "pipe_$type"), TransferPipe {

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

    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(BlockDirectional.FACING, EnumFacing.byIndex(meta))

    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState = state.withProperty(
        BlockDirectional.FACING,
        mirrorIn.mirror(state.getValue(BlockDirectional.FACING))
    )

    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState = state.withProperty(
        BlockDirectional.FACING,
        rot.rotate(state.getValue(BlockDirectional.FACING))
    )

}