package hiiragi283.ragi_materials.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockSoilAir : BlockBase("soil_air", Material.PLANTS, 2) {

    override val itemBlock = null

    init {
        blockHardness = 0.0f
        blockResistance = 0.0f
        soundType = SoundType.STONE
        tickRandomly = true
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("NULL_AABB", "net.minecraft.block.Block.NULL_AABB"))
    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB? = NULL_AABB

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullBlock(state: IBlockState) = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState) = false

    //    Event    //

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (pos.y < 64) {
            for (facing in EnumFacing.values()) {
                val posTo = pos.offset(facing)
                val stateTo = world.getBlockState(posTo)
                //隣接するブロックが自分自身でない場合，それを侵食する
                if (stateTo.block != this && stateTo.block == Blocks.AIR) {
                    world.setBlockState(posTo, this.defaultState, 2)
                }
            }
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}