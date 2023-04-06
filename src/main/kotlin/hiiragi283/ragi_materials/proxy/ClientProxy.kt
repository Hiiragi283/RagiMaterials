package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.client.model.RagiTEISR
import hiiragi283.ragi_materials.client.render.RenderBlazingForge
import hiiragi283.ragi_materials.client.render.RenderIndustrialLabo
import hiiragi283.ragi_materials.client.render.RenderLaboratoryTable
import hiiragi283.ragi_materials.client.render.RenderQuartzAntenna
import hiiragi283.ragi_materials.tile.TileBlazingForge
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import hiiragi283.ragi_materials.tile.TileLaboTable
import hiiragi283.ragi_materials.tile.TileQuartzAntenna
import net.minecraftforge.fml.client.registry.ClientRegistry

class ClientProxy : CommonProxy() {

    override fun loadPreInit() {
        super.loadPreInit()
        //TESRの登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlazingForge::class.java, RenderBlazingForge)
        ClientRegistry.bindTileEntitySpecialRenderer(TileIndustrialLabo::class.java, RenderIndustrialLabo)
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable)
        ClientRegistry.bindTileEntitySpecialRenderer(TileQuartzAntenna::class.java, RenderQuartzAntenna)
        //ClientRegistry.bindTileEntitySpecialRenderer(TileOreDictConv::class.java, RenderOreDictConv)
        //TEISRの登録
        RagiRegistry.ITEM.ItemBlockIndustrialLabo.tileEntityItemStackRenderer = RagiTEISR
    }
}