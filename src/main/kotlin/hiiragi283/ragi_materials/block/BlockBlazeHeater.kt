package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockHorizontalBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockBlazeHeater : BlockHorizontalBase("blaze_heater", Material.ROCK, 2) {

    companion object {
        val HELL: PropertyBool = PropertyBool.create("hellrise")
    }

    init {
        blockHardness = 3.5F
        blockResistance = 3.5F
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(HELL, false)
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        when (state.getValue(HELL)) {
            true -> drops.add(ItemStack(this, 1, 1))
            false -> drops.add(ItemStack(this, 1, 0))
        }
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer  = BlockStateContainer(this, FACING, HELL)

    override fun getMetaFromState(state: IBlockState): Int {
        return when (state.getValue(FACING)) {
            EnumFacing.NORTH -> if (!state.getValue(HELL)) 0 else 4
            EnumFacing.SOUTH -> if (!state.getValue(HELL)) 1 else 5
            EnumFacing.WEST -> if (!state.getValue(HELL)) 2 else 6
            EnumFacing.EAST -> if (!state.getValue(HELL)) 3 else 7
            else -> 0
        }
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        val stack = placer.getHeldItem(hand)
        var isHellrise = false
        if (stack.metadata == 1) isHellrise = true
        if (isHellrise) {
            world.playSound(null, pos, RagiUtil.getSound("minecraft:entity.endermen.stare"), SoundCategory.BLOCKS, 1.0f, 2.0f)
        } else {
            world.playSound(null, pos, RagiUtil.getSound("minecraft:entity.blaze.ambient"), SoundCategory.BLOCKS, 1.0f, 1.0f)
        }
        return this.defaultState.withProperty(FACING, placer.horizontalFacing.opposite).withProperty(HELL, isHellrise)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(FACING, EnumFacing.getFront((meta / 4) + 2)).withProperty(HELL, meta % 4 == 1)", "net.minecraft.block.BlockHorizontal.FACING", "net.minecraft.util.EnumFacing", "hiiragi283.ragi_materials.block.BlockBlazeHeater.Companion.HELL"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(FACING, EnumFacing.getFront((meta / 4) + 2)).withProperty(HELL, meta % 4 == 1)

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = FFHelper.getResult(world, pos, state, player, player.getHeldItem(hand)) //レシピ実行

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}