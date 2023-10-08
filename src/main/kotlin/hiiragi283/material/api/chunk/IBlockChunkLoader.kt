package hiiragi283.material.api.chunk

import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IBlockChunkLoader {

    fun canLoad(world: World, pos: BlockPos): Boolean

}