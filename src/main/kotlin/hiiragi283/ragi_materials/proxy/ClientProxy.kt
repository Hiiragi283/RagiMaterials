package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.client.render.*
import hiiragi283.ragi_materials.tile.TileBlazingForge
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import hiiragi283.ragi_materials.tile.TileLaboTable
import hiiragi283.ragi_materials.tile.TileQuartzAntenna
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class ClientProxy : CommonProxy() {

    override fun loadPreInit(event: FMLPreInitializationEvent) {
        super.loadPreInit(event)
        //TESRの登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlazingForge::class.java, RenderBlazingForge)
        ClientRegistry.bindTileEntitySpecialRenderer(TileIndustrialLabo::class.java, RenderIndustrialLabo)
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable)
        ClientRegistry.bindTileEntitySpecialRenderer(TileQuartzAntenna::class.java, RenderQuartzAntenna)
        //TEISRの登録
        RagiRegistry.ItemBlockIndustrialLabo.tileEntityItemStackRenderer = RagiTEISR
    }
}