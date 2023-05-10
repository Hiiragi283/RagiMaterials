package ragi_materials.core.proxy

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import ragi_materials.core.event.ClientRegistryEvent
import ragi_materials.main.client.render.RenderBasin
import ragi_materials.main.client.render.RenderLaboratoryTable
import ragi_materials.main.tile.TileBasin
import ragi_materials.main.tile.TileLaboTable
import ragi_materials.metallurgy.client.render.RenderBlastFurnace
import ragi_materials.metallurgy.client.render.RenderBlazingForge
import ragi_materials.metallurgy.tile.TileBlastFurnaceInterface
import ragi_materials.metallurgy.tile.TileBlazingForge

class ClientProxy : CommonProxy() {

    override fun onConstruct(event: FMLConstructionEvent) {
        super.onConstruct(event)
        MinecraftForge.EVENT_BUS.register(ClientRegistryEvent)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        super.onPreInit(event)
        //TESRの登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileBasin::class.java, RenderBasin)
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlastFurnaceInterface::class.java, RenderBlastFurnace)
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlazingForge::class.java, RenderBlazingForge)
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable)
        //TEISRの登録
        //RagiRegistry.ItemBlockIndustrialLabo.tileEntityItemStackRenderer = RagiTEISR
    }
}