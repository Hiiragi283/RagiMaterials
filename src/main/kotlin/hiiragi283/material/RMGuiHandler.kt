package hiiragi283.material

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

object RMGuiHandler : IGuiHandler {

    const val TILE_ID = 0

    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        if (ID == TILE_ID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                return when (tile) {
                    else -> null
                }
            }
        }
        return null
    }

    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        if (ID == TILE_ID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                return when (tile) {
                    else -> null
                }
            }
        }
        return null
    }

}