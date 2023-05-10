package ragi_materials.main.block

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.block.BlockContainerBase
import ragi_materials.main.tile.TileLaboTable
import java.util.*

object BlockLaboTable : BlockContainerBase<TileLaboTable>("laboratory_table", Material.IRON, TileLaboTable::class.java, 3) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 2)
        soundType = SoundType.METAL
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Event    //

    @Deprecated("Deprecated in Java", ReplaceWith("if (world.isBlockPowered(pos) || world.isBlockPowered(pos.up())) world.scheduleUpdate(pos, this, 4)"))
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block, fromPos: BlockPos) {
        if (world.isBlockPowered(pos) || world.isBlockPowered(pos.up())) world.scheduleUpdate(pos, this, 4)
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!world.isRemote) {
            val tile = world.getTileEntity(pos)
            if (tile !== null && tile is TileLaboTable) tile.chemicalReaction()
        }
    }
}