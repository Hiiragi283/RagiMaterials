package hiiragi283.ragi_materials.integration.waila

import mcp.mobius.waila.api.IWailaPlugin
import mcp.mobius.waila.api.IWailaRegistrar
import mcp.mobius.waila.api.WailaPlugin

/*
  Thanks to defeatedcrow!
  Source: https://github.com/Hiiragi283/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/plugin/waila/DCWailaPlugin.java
*/

@WailaPlugin
class WailaCore : IWailaPlugin {

    override fun register(registrar: IWailaRegistrar?) {
        registrar?.let {}
    }

}