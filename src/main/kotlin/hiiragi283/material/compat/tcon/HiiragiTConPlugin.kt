package hiiragi283.material.compat.tcon

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.compat.HiiragiPluginBase
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import slimeknights.tconstruct.library.TinkerRegistry

typealias TConMaterial = slimeknights.tconstruct.library.materials.Material

object HiiragiTConPlugin : HiiragiPluginBase("tconstruct", "Tinker's Construct", { HiiragiConfigs.INTEGRATION.tCon }) {

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //tConMaterialOf(MaterialCommons.STEEL)
    }

    private fun tConMaterialOf(material: HiiragiMaterial): TConMaterial {
        val tConMaterial = TConMaterial(material.name, material.color)
        //Add TCon Material
        TinkerRegistry.addMaterial(tConMaterial)
        //Link Tcon Material, Fluid, and OreDict prefix
        TinkerRegistry.integrate(tConMaterial, material.getFluid(), material.getOreDictName())
        return tConMaterial
    }

    override fun registerMaterial() {
        MaterialCompats.ARDITE.register()
        MaterialCompats.MANYULLYN.register()
        MaterialCompats.ALUMINIUM_BRASS.register()
    }

}