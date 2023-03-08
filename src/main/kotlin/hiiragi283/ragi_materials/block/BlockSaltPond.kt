package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.IStringSerializable
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidUtil
import java.util.*

class BlockSaltPond : BlockBase("salt_pond", Material.WOOD, 2) {

    //private変数の宣言
    companion object {
        val NORTH: PropertyBool = PropertyBool.create("north")
        val EAST: PropertyBool = PropertyBool.create("east")
        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val TYPE: PropertyEnum<EnumSalt> = PropertyEnum.create("type", EnumSalt::class.java)
    }

    init {
        blockHardness = 2.0F
        blockResistance = 3.0F
        defaultState = blockState.baseState.withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(TYPE, EnumSalt.EMPTY)
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("axe", 0)
        soundType = SoundType.WOOD
    }

    //    General    //

    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(world: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return when (face) {
            //下 -> SOLID
            EnumFacing.DOWN -> BlockFaceShape.SOLID
            //それ以外 -> UNDEFINED
            else -> BlockFaceShape.UNDEFINED
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(state: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, NORTH, EAST, SOUTH, WEST, TYPE)

    private fun canConnectTo(world: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        val posTo = pos.offset(facing)
        val stateTo = world.getBlockState(posTo)
        val shape = stateTo.getBlockFaceShape(world, posTo, facing)
        return stateTo.block != Blocks.AIR && (stateTo.block == this || shape == BlockFaceShape.SOLID || shape == BlockFaceShape.UNDEFINED)
    }

    @Deprecated("Deprecated in Java")
    override fun getActualState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState {
        val connectN = canConnectTo(world, pos, EnumFacing.NORTH)
        val connectE = canConnectTo(world, pos, EnumFacing.EAST)
        val connectS = canConnectTo(world, pos, EnumFacing.SOUTH)
        val connectW = canConnectTo(world, pos, EnumFacing.WEST)
        return state.withProperty(NORTH, connectN).withProperty(EAST, connectE).withProperty(SOUTH, connectS).withProperty(WEST, connectW)
    }

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(TYPE).indexInt

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        return when (meta % 4) {
            1 -> this.defaultState.withProperty(TYPE, EnumSalt.WATER)
            2 -> this.defaultState.withProperty(TYPE, EnumSalt.SALTWATER)
            3 -> this.defaultState.withProperty(TYPE, EnumSalt.BRINE)
            else -> this.defaultState.withProperty(TYPE, EnumSalt.EMPTY)
        }
    }

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        //サーバー側，かつ塩田ブロックが空の場合
        return if (!world.isRemote && state.getValue(TYPE) == EnumSalt.EMPTY) {
            val stack = player.getHeldItem(hand)
            val fluidStack = FluidUtil.getFluidContained(stack)
            //バケツ系は挙動が直らないので除外
            if (fluidStack !== null && !stack.item.registryName!!.toString().contains("bucket")) {
                FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing)
                world.setBlockState(pos, world.getBlockState(pos).withProperty(TYPE, getType(fluidStack.fluid.name)), 2) //stateの更新
                world.scheduleUpdate(pos, this, 200) //tick更新を200 tick後に設定
                world.playSound(null, pos, RagiUtil.getSound("minecraft:item.bucket.empty"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
                true
            } else false
        } else false
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        //サーバー側の場合
        if (!world.isRemote) {
            //完成品の場合分け
            val stack: ItemStack = when (state.getValue(TYPE)) {
                EnumSalt.WATER -> ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.SALT.index)
                EnumSalt.SALTWATER -> ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.MAGNESIUM_CHLORIDE.index)
                EnumSalt.BRINE -> ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.LITHIUM_CHLORIDE.index)
                else -> ItemStack.EMPTY
            }
            if (stack.item != Items.AIR) {
                RagiUtil.dropItem(world, pos, stack)
                world.setBlockState(pos, state.withProperty(TYPE, EnumSalt.EMPTY), 2) //stateの更新
                world.playSound(null, pos, RagiUtil.getSound("minecraft:block.sand.break"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
            }
        }
    }

    //    Class    //

    enum class EnumSalt(val indexInt: Int, val fluid: String) : IStringSerializable {
        EMPTY(0, "empty"), WATER(1, "water"), SALTWATER(2, "saltwater"), BRINE(3, "brine");

        override fun getName(): String {
            return this.fluid
        }

    }

    private fun getType(fluid: String): EnumSalt {
        return when (fluid) {
            "water" -> EnumSalt.WATER
            "saltwater" -> EnumSalt.SALTWATER
            "brine" -> EnumSalt.BRINE
            else -> EnumSalt.EMPTY
        }
    }
}