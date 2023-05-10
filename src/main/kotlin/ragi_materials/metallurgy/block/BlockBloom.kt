package ragi_materials.metallurgy.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import ragi_materials.core.block.BlockContainerHoldable
import ragi_materials.core.material.IMaterialBlock
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.tile.TileBase
import ragi_materials.metallurgy.tile.TileBloom
import java.util.*

object BlockBloom : BlockContainerHoldable<TileBloom>("bloom", Material.ROCK, TileBloom::class.java, -1), IMaterialBlock {

    override val itemBlock = null

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(blockState: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB = AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)

    @Deprecated("Deprecated in Java", ReplaceWith("BlockFaceShape.UNDEFINED", "net.minecraft.block.state.BlockFaceShape"))
    override fun getBlockFaceShape(world: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing) = BlockFaceShape.UNDEFINED

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        val tile = world.getTileEntity(pos)
        val part = if (tile !== null && tile is TileBase) tile.material.getPart(PartRegistry.INGOT) else ItemStack.EMPTY
        if (!part.isEmpty) drops.add(part)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun quantityDropped(random: Random) = 0

    //    IMaterialBlock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): RagiMaterial {
        val tile = world.getTileEntity(pos)
        return if (tile !== null && tile is TileBase) tile.material else super.getMaterialBlock(world, pos, state)
    }
}