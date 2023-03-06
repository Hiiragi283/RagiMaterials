package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.event.*
import hiiragi283.ragi_materials.render.tile.RenderLaboratoryTable
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry

class ClientProxy : CommonProxy() {

    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(ColorHandler())
        //MinecraftForge.EVENT_BUS.register(ItemTooltip())
        MinecraftForge.EVENT_BUS.register(ModelRegistry())
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        super.registerTile()
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable())
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}