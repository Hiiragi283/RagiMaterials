package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.client.gui.ContainerIndustrialLabo
import hiiragi283.ragi_materials.client.gui.GuiIndustrialLabo
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

class RagiGuiHandler : IGuiHandler {

    //    IGuiHandler    //

    //サーバー側にはContainerを返す
    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var result: Container? = null
        if (ID == 0) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    is TileIndustrialLabo -> result = ContainerIndustrialLabo(tile, player)
                    else -> {}
                }
            }
        }
        return result
    }

    //クライアント側にはGUIを返す
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var result: GuiContainer? = null
        val tile = world.getTileEntity(BlockPos(x, y, z))
        if (tile !== null) {
            when (tile) {
                is TileIndustrialLabo -> result = GuiIndustrialLabo(tile, player)
                else -> {}
            }
        }
        return result
    }
}