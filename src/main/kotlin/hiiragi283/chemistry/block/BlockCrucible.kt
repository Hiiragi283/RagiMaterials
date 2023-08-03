package hiiragi283.chemistry.block

import hiiragi283.api.block.HiiragiBlockContainer
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.chemistry.tile.TileEntityCrucible
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object BlockCrucible : HiiragiBlockContainer.Holdable<TileEntityCrucible>(
    Material.IRON,
    "crucible",
    TileEntityCrucible::class.java
) {

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, "crucible", 0)

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 2)
        soundType = SoundType.METAL
    }

    //    General    //

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)", "net.minecraft.util.math.AxisAlignedBB")
    )
    override fun getBoundingBox(blockState: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB =
        AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75)

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("BlockFaceShape.UNDEFINED", "net.minecraft.block.state.BlockFaceShape")
    )
    override fun getBlockFaceShape(
        world: IBlockAccess,
        state: IBlockState,
        pos: BlockPos,
        face: EnumFacing
    ): BlockFaceShape = BlockFaceShape.UNDEFINED

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}