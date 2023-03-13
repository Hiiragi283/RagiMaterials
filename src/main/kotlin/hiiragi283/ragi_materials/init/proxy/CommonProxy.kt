package hiiragi283.ragi_materials.init.proxy

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.client.gui.ContainerIndustrialLabo
import hiiragi283.ragi_materials.client.gui.GuiIndustrialLabo
import hiiragi283.ragi_materials.tile.*
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraftforge.fml.common.registry.GameRegistry

open class CommonProxy : IGuiHandler {

    //Pre-Initializationで読み込むメソッド
    open fun loadPreInit() {}

    //Initializationで読み込むメソッド
    open fun loadInit() {
        registerTile()
    }

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit() {}

    //TileEntityを登録するメソッド
    open fun registerTile() {
        GameRegistry.registerTileEntity(TileLaboTable::class.java, ResourceLocation(Reference.MOD_ID, "te_laboratory_table")) //100
        GameRegistry.registerTileEntity(TileFullBottleStation::class.java, ResourceLocation(Reference.MOD_ID, "te_fullbottle_station")) //101
        GameRegistry.registerTileEntity(TileForgeFurnace::class.java, ResourceLocation(Reference.MOD_ID, "te_forge_furnace")) //102
        GameRegistry.registerTileEntity(TileBlazingForge::class.java, ResourceLocation(Reference.MOD_ID, "te_blazing_forge")) //103
        GameRegistry.registerTileEntity(TileIndustrialLabo::class.java, ResourceLocation(Reference.MOD_ID, "te_industrial_labo")) //104
    }

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