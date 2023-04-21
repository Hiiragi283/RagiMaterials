package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.tile.TileOreDictConv
import hiiragi283.ragi_materials.util.dropInventoryItems
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockOreDictConv : BlockContainerBase<TileOreDictConv>("oredict_converter", Material.WOOD, TileOreDictConv::class.java, 2) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
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

    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(blockState: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0)

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileOreDictConv) dropInventoryItems(world, pos, tile.inventory)
        super.breakBlock(world, pos, state)
    }
}