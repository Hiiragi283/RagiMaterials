package hiiragi283.material.block

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.block.HiiragiBlockContainer
import hiiragi283.material.init.HiiragiCreativeTabs
import hiiragi283.material.tile.TileEntityMachineExtender
import hiiragi283.material.util.SimpleColorProvider
import net.minecraft.block.BlockDirectional
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
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
object BlockMachineExtender : HiiragiBlockContainer<TileEntityMachineExtender>(
    Material.IRON,
    "machine_extender",
    ::TileEntityMachineExtender
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
    ): IBlockState = defaultState.withProperty(BlockDirectional.FACING, facing.opposite)

    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(BlockDirectional.FACING, EnumFacing.byIndex(meta))

    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState =
        state.withRotation(mirrorIn.toRotation(state.getValue(BlockDirectional.FACING)))

    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState =
        state.withProperty(BlockDirectional.FACING, rot.rotate(state.getValue(BlockDirectional.FACING)))

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        tooltip.add(I18n.format("tips.ragi_materials.block.machine_extender"))
    }

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    HiiragiEntry    //

    @SideOnly(Side.CLIENT)
    override fun getBlockColor(): IBlockColor = SimpleColorProvider(RagiMaterials.COLOR)

    @SideOnly(Side.CLIENT)
    override fun getItemColor(): IItemColor = SimpleColorProvider(RagiMaterials.COLOR)

}