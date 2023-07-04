package hiiragi283.material.common

import hiiragi283.material.api.RMInitializer
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint

private lateinit var entries: MutableList<RMInitializer>

fun loadRMInitializer() = entries.forEach(RMInitializer::onInitialize)

object RagiMaterialsPre : PreLaunchEntrypoint {

    override fun onPreLaunch() {

        entries = FabricLoader.getInstance().getEntrypoints("ragi_materials", RMInitializer::class.java)

    }
}