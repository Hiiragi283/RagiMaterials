package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.tile.TileQuartzAntenna
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockQuartzAntenna : BlockContainerBase("quartz_antenna", Material.CIRCUITS, 3) {

    init {
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("NULL_AABB", "net.minecraft.block.Block.NULL_AABB"))
    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB? = NULL_AABB

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.TRANSLUCENT

    //    Tile Entity    //

    override fun createNewTileEntity(world: World, meta: Int): TileEntity = TileQuartzAntenna()

}