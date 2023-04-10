package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.tile.TileLaboTable
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockLaboTable : BlockContainerBase<TileLaboTable>("laboratory_table", Material.IRON, TileLaboTable::class.java, 3) {

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

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileLaboTable) RagiUtil.dropInventoryItems(world, pos, tile.inventory)
        super.breakBlock(world, pos, state)
    }

    @Deprecated("Deprecated in Java")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block, fromPos: BlockPos) {
        if (world.isBlockPowered(pos)) {
            val tile = world.getTileEntity(pos)
            if (tile !== null && tile is TileLaboTable) tile.chemicalReaction(world, pos)
        }
    }
}