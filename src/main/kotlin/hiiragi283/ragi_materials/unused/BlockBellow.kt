package hiiragi283.ragi_materials.unused

import hiiragi283.ragi_materials.base.BlockHorizontalBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiUtil.toInt
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockBellow : BlockHorizontalBase("block_bellow", Material.CLOTH, 1) {

    companion object {
        val ACTIVE: PropertyBool = PropertyBool.create("active")
    }

    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false)
        setHardness(3.5F)
        setHarvestLevel("axe", 0)
        setResistance(3.5F)
        soundType = SoundType.CLOTH
    }

    //    General    //

    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(world: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return when (face) {
            EnumFacing.UP -> when (state.getValue(ACTIVE)) {
                true -> BlockFaceShape.UNDEFINED
                false -> BlockFaceShape.SOLID
            }

            EnumFacing.DOWN -> BlockFaceShape.SOLID
            else -> BlockFaceShape.UNDEFINED
        }
    }

    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(state: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return when (state.getValue(ACTIVE)) {
            true -> AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0)
            else -> AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, ACTIVE, FACING)

    override fun getMetaFromState(state: IBlockState): Int = ((!state.getValue(ACTIVE)).toInt()) * 4 + (state.getValue(FACING).index - 2)

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(ACTIVE, meta / 4 == 0).withProperty(FACING, EnumFacing.getFront((meta % 4) + 2))", "hiiragi283.ragi_materials.unused.BlockBellow.Companion.ACTIVE", "net.minecraft.block.BlockHorizontal.FACING", "net.minecraft.util.EnumFacing"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(ACTIVE, meta / 4 == 0).withProperty(FACING, EnumFacing.getFront((meta % 4) + 2))

    //    Event    //

    @Deprecated("Deprecated in Java")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block, fromPos: BlockPos) {
        if (!world.isRemote && world.isBlockPowered(pos)) blowBellow(world, pos, state)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = blowBellow(world, pos, state)

    private fun blowBellow(world: World, pos: BlockPos, state: IBlockState): Boolean {
        //サーバー側，かつACTIVEでない場合
        /*if (!world.isRemote && !state.getValue(ACTIVE)) {
            //ふいごが向いている方向，その先の座標とstateを取得
            val facingBellow = state.getValue(FACING)
            val posFurnace = pos.offset(facingBellow)
            val stateTo = world.getBlockState(posFurnace)
            //stateTo.blockがBlockForgeFurnace，かつ燃料が満タンの場合
            return if (stateTo.block is BlockForgeFurnaceOld && stateTo.getValue(BlockForgeFurnaceOld.FUEL) == 3) {
                val facingFurnace = stateTo.getValue(FACING) //Forge Furnaceの方向
                val litForgeFurnace = RagiInit.BlockLitForgeFurnace.defaultState
                world.setBlockState(posFurnace, litForgeFurnace.withProperty(FACING, facingFurnace), 2) //火力UP
                world.scheduleUpdate(posFurnace, RagiInit.BlockLitForgeFurnace, 100) //Forge Furnaceのtick更新を100 tick後に設定
                world.setBlockState(pos, state.withProperty(ACTIVE, true), 2) //アニメーション
                world.scheduleUpdate(pos, this, 40) //Bellowのtick更新を20 tick後に設定
                world.playSound(null, posFurnace, RagiUtil.getSound("minecraft:entity.blaze.shoot"), SoundCategory.BLOCKS, 1.0f, 0.5f) //SEを再生
                RagiLogger.infoDebug("Forge Furnace was boosted!")
                true
            } else false
        } else */return false
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!world.isRemote) world.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false), 2)
    }
}