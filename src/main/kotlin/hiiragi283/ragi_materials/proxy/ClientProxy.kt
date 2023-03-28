package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.client.render.tile.RenderBlazingForge
import hiiragi283.ragi_materials.client.render.tile.RenderIndustrialLabo
import hiiragi283.ragi_materials.client.render.tile.RenderLaboratoryTable
import hiiragi283.ragi_materials.tile.TileBlazingForge
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraftforge.fml.client.registry.ClientRegistry

class ClientProxy : CommonProxy() {

    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {}

    //Initializationで読み込むメソッド
    override fun loadInit() {
        //タイルエンティティの登録
        super.registerTiles()
        //TESRの登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlazingForge::class.java, RenderBlazingForge())
        ClientRegistry.bindTileEntitySpecialRenderer(TileIndustrialLabo::class.java, RenderIndustrialLabo())
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable())
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}