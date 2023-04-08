package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.client.render.RagiTEISR
import hiiragi283.ragi_materials.client.render.RenderBlazingForge
import hiiragi283.ragi_materials.client.render.RenderIndustrialLabo
import hiiragi283.ragi_materials.client.render.RenderLaboratoryTable
import hiiragi283.ragi_materials.event.ClientRegistryEvent
import hiiragi283.ragi_materials.tile.TileBlazingForge
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class ClientProxy : CommonProxy() {

    override fun onConstruct(event: FMLConstructionEvent) {
        super.onConstruct(event)
        MinecraftForge.EVENT_BUS.register(ClientRegistryEvent)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        super.onPreInit(event)
        //TESRの登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlazingForge::class.java, RenderBlazingForge)
        ClientRegistry.bindTileEntitySpecialRenderer(TileIndustrialLabo::class.java, RenderIndustrialLabo)
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable)
        //TEISRの登録
        RagiRegistry.ItemBlockIndustrialLabo.tileEntityItemStackRenderer = RagiTEISR
    }
}