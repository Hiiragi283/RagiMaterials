package ragi_materials.core.integration

import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.proxy.IProxy
import ragi_materials.core.util.RagiColor
import ragi_materials.main.block.BlockTransferBase
import ragi_materials.main.tile.TileTransferGas

object PluginMekanism : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //Block
        if (RagiConfig.module.enableMain) {
            RagiRegistry.BlockTransferGas = BlockTransferBase("gas", TileTransferGas::class.java, RagiColor.GREEN)
        }
    }

    override fun onInit(event: FMLInitializationEvent) {}

    override fun onPostInit(event: FMLPostInitializationEvent) {}

}