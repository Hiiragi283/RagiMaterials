package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.RagiBlockContainer
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.tile.TileBlazingMelter
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockBlazingMelter: RagiBlockContainer("blazing_melter", Material.IRON, 1) {

    companion object {
        val FACING: PropertyDirection = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL)
    }

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, FACING)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(FACING).index - 2

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState = this.defaultState.withProperty(FACING, placer.horizontalFacing.opposite)

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(FACING, EnumFacing.getFront((meta / 4) + 2))", "hiiragi283.ragi_materials.block.BlockBlazingMelter.Companion.FACING", "net.minecraft.util.EnumFacing"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(FACING, EnumFacing.getFront((meta / 4) + 2))

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    Tile Entity    //

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity = TileBlazingMelter()
}