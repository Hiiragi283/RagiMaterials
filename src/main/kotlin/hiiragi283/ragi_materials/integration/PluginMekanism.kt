package hiiragi283.ragi_materials.integration

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.block.BlockTransferBase
import hiiragi283.ragi_materials.client.color.RagiColor
import hiiragi283.ragi_materials.proxy.IProxy
import hiiragi283.ragi_materials.tile.TileTransferGas
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

object PluginMekanism : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //Block
        RagiRegistry.BlockTransferGas = BlockTransferBase("gas", TileTransferGas::class.java, RagiColor.GREEN)
    }

    override fun onInit(event: FMLInitializationEvent) {}

    override fun onPostInit(event: FMLPostInitializationEvent) {}

}