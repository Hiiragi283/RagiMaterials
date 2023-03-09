package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockLitForgeFurnace : BlockHorizontal(Material.ROCK) {

    private val registryName = "lit_forge_furnace"

    init {
        blockHardness = 3.5F
        blockResistance = 3.5F
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("pickaxe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        soundType = SoundType.STONE
        unlocalizedName = registryName
    }

    //    General    //

    /*override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        drops.add(ItemStack(RagiInit.BlockForgeFurnace))
    }*/

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, FACING)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(FACING).index - 2

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState = this.defaultState.withProperty(FACING, placer.horizontalFacing.opposite)

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(FACING, EnumFacing.getFront((meta / 4) + 2))", "net.minecraft.block.BlockHorizontal.FACING", "net.minecraft.util.EnumFacing"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(FACING, EnumFacing.getFront((meta / 4) + 2))

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (!world.isRemote) FFHelper.getResult(world, pos, state, player, player.getHeldItem(hand)) //レシピ実行
        return true
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        //if (!world.isRemote) world.setBlockState(pos, RagiInit.BlockForgeFurnace.defaultState.withProperty(FACING, state.getValue(FACING)).withProperty(BlockForgeFurnace.FUEL, 3), 2) //火力DOWN
    }

    //    Redstone    //

    @Deprecated("Deprecated in Java", ReplaceWith("4"))
    override fun getComparatorInputOverride(state: IBlockState, world: World, pos: BlockPos): Int = 4 //常に4を返す

    @Deprecated("Deprecated in Java", ReplaceWith("true"))
    override fun hasComparatorInputOverride(state: IBlockState): Boolean = true
}