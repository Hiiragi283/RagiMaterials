package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.RagiBlockContainer
import hiiragi283.ragi_materials.base.RagiFacing
import hiiragi283.ragi_materials.tile.TileForgeFurnace
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockForgeFurnace : RagiBlockContainer("forge_furnace", Material.ROCK, 3) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(RagiFacing.HORIZONTAL, EnumFacing.NORTH)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiFacing.HORIZONTAL)

    override fun getMetaFromState(state: IBlockState): Int = RagiFacing.getMeta(state)

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState = this.defaultState.withProperty(RagiFacing.HORIZONTAL, placer.horizontalFacing.opposite)

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(RagiFacing.HORIZONTAL, RagiFacing.getState(meta))", "hiiragi283.ragi_materials.base.RagiFacing", "hiiragi283.ragi_materials.base.RagiFacing"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(RagiFacing.HORIZONTAL, RagiFacing.getState(meta))

    //    Tile Entity    //

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity = TileForgeFurnace()
}