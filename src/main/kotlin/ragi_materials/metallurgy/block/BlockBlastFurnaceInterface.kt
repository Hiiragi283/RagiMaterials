package ragi_materials.metallurgy.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.block.BlockContainerBase
import ragi_materials.metallurgy.tile.TileBlastFurnaceInterface

class BlockBlastFurnaceInterface : BlockContainerBase<TileBlastFurnaceInterface>("blast_furnace_interface", Material.ROCK, TileBlastFurnaceInterface::class.java, -1) {

    override val itemBlock = null

    init {
        blockHardness = 0.0f
        blockResistance = 0.0f
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("NULL_AABB", "net.minecraft.block.Block.NULL_AABB"))
    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB? = NULL_AABB

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState) = false

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    @SideOnly(Side.CLIENT)
    @Deprecated("Deprecated in Java", ReplaceWith("EnumBlockRenderType.INVISIBLE", "net.minecraft.util.EnumBlockRenderType"))
    override fun getRenderType(state: IBlockState) = EnumBlockRenderType.INVISIBLE

}