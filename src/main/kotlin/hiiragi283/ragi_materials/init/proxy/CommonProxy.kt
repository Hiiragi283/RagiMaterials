package hiiragi283.ragi_materials.init.proxy

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.client.gui.ContainerIndustrialLabo
import hiiragi283.ragi_materials.client.gui.GuiIndustrialLabo
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.tile.*
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.tileentity.TileEntity
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
        registerTiles()
    }

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit() {}

    //TileEntityを登録するメソッド
    open fun registerTiles() {
        registerTile(TileLaboTable::class.java, RagiBlock.BlockLaboratoryTable) //100
        registerTile(TileFullBottleStation::class.java, RagiBlock.BlockFullBottleStation) //101
        registerTile(TileForgeFurnace::class.java, RagiBlock.BlockForgeFurnace) //102
        registerTile(TileBlazingForge::class.java, RagiBlock.BlockBlazingForge) //103
        registerTile(TileIndustrialLabo::class.java, RagiBlock.BlockIndustrialLabo) //104
        registerTile(TileStoneMill::class.java, RagiBlock.BlockStoneMill) //105
    }

    private fun <T: TileEntity> registerTile(tile: Class<T>, block: BlockContainerBase) {
        GameRegistry.registerTileEntity(tile, ResourceLocation(Reference.MOD_ID, "te_${block.registryName!!.resourcePath}"))
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