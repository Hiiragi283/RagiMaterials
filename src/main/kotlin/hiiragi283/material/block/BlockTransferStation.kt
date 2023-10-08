package hiiragi283.material.block

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.block.HiiragiBlockContainer
import hiiragi283.material.api.tile.HiiragiTileEntity
import net.minecraft.block.BlockDirectional
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("OVERRIDE_DEPRECATION")
class BlockTransferStation<T : HiiragiTileEntity>(type: String, supplier: () -> T) : HiiragiBlockContainer<T>(
    Material.IRON,
    "station_$type",
    supplier
) {

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
        defaultState = defaultState.withProperty(BlockDirectional.FACING, EnumFacing.NORTH)
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

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        tooltip.add(I18n.format("tips.ragi_materials.block.transfer_station"))
    }

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT_MIPPED

}