package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.tile.TileStoneMill
import hiiragi283.ragi_materials.util.dropInventoryItems
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockStoneMill : BlockContainerBase<TileStoneMill>("stone_mill", Material.ROCK, TileStoneMill::class.java, 2) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(RagiProperty.COUNT8, 0)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullBlock(state: IBlockState) = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState) = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiProperty.COUNT8)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(RagiProperty.COUNT8)

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(COUNT, meta / 8)", "hiiragi283.ragi_materials.block.BlockStoneMill.Companion.COUNT"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(RagiProperty.COUNT8, meta / 8)

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileStoneMill) dropInventoryItems(world, pos, tile.inventory)
        super.breakBlock(world, pos, state)
    }
}