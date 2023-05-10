package ragi_materials.main.block

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import ragi_materials.core.block.BlockContainerBase
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.util.toBool
import ragi_materials.core.util.toInt
import ragi_materials.main.tile.TileHopperPress
import java.util.*

object BlockHopperPress : BlockContainerBase<TileHopperPress>("hopper_press", Material.IRON, TileHopperPress::class.java, 3) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(RagiProperty.ACTIVE, true)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.METAL
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("BlockFaceShape.UNDEFINED", "net.minecraft.block.state.BlockFaceShape"))
    override fun getBlockFaceShape(worldIn: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return BlockFaceShape.UNDEFINED
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiProperty.ACTIVE)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(RagiProperty.ACTIVE).toInt()

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(RagiProperty.ACTIVE, meta.toBool())", "ragi_materials.core.block.property.RagiProperty", "ragi_materials.core.util.toBool"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(RagiProperty.ACTIVE, meta.toBool())

    fun isActive(state: IBlockState): Boolean = state.getValue(RagiProperty.ACTIVE)

    fun setActive(state: IBlockState, active: Boolean): IBlockState = state.withProperty(RagiProperty.ACTIVE, active)

    //    Event    //

    @Deprecated("Deprecated in Java", ReplaceWith("if (world.isBlockPowered(pos) || world.isBlockPowered(pos.up())) world.scheduleUpdate(pos, this, 4)"))
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block, fromPos: BlockPos) {
        if (!world.isRemote) {
            if (world.isBlockPowered(pos) || world.isBlockPowered(pos.up())) {
                world.setBlockState(pos, setActive(state, true), 2)
            }
        }
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!world.isRemote) {
            val tile = world.getTileEntity(pos)
            if (tile !== null && tile is TileHopperPress) {
                if (isActive(state)) tile.doProcess()
            }
            world.setBlockState(pos, setActive(state, false), 2)
        }
    }
}