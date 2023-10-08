package hiiragi283.material.api.multiblock

import net.minecraft.block.state.IBlockState
import net.minecraft.init.Biomes
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.WorldType
import net.minecraft.world.biome.Biome

class MultiblockWorld(private val pattern: MultiblockPattern) : IBlockAccess {

    override fun getTileEntity(pos: BlockPos): TileEntity? = null

    override fun getCombinedLight(pos: BlockPos, lightValue: Int): Int = 15 shl 20 or (lightValue shl 4)

    override fun getBlockState(pos: BlockPos): IBlockState =
        pattern.getComponent(pos)?.displayState ?: Blocks.AIR.defaultState

    override fun isAirBlock(pos: BlockPos): Boolean = pattern.hasComponent(pos)

    override fun getBiome(pos: BlockPos): Biome = Biomes.PLAINS

    override fun getStrongPower(pos: BlockPos, direction: EnumFacing): Int = 0

    override fun getWorldType(): WorldType = WorldType.FLAT

    override fun isSideSolid(pos: BlockPos, side: EnumFacing, default: Boolean): Boolean =
        getBlockState(pos).isSideSolid(this, pos, side)

}