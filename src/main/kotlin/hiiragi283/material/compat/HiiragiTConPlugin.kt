package hiiragi283.material.compat

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import slimeknights.tconstruct.library.TinkerRegistry
import slimeknights.tconstruct.library.fluid.FluidMolten

typealias TConMaterial = slimeknights.tconstruct.library.materials.Material

object HiiragiTConPlugin : HiiragiPluginBase("tconstruct", "Tinker's Construct", { HiiragiConfigs.INTEGRATION.tCon }) {

    fun getTexStill(): ResourceLocation = if (enabled()) getResourceLocation("blocks/fluids/molten_metal") else ResourceLocation("blocks/concrete_white")

    fun getTexFlow(): ResourceLocation = if (enabled()) getResourceLocation("blocks/fluids/molten_metal_flow") else ResourceLocation("blocks/concrete_white")

    override fun onPreInit(event: FMLPreInitializationEvent) {
        addTConMaterial(MaterialCommons.STEEL)
    }

    private fun addTConMaterial(material: HiiragiMaterial) {
        /*val tConMaterial = TConMaterial(material.name, material.color)
        //Add TCon Material
        TinkerRegistry.addMaterial(tConMaterial)
        //Add TCon Molten Metals
        FluidMolten(material.name, material.color)
        //Link Tcon Material, Fluid, and OreDict prefix
        TinkerRegistry.integrate(tConMaterial, material.getFluid(), material.getOreDictName())*/
    }

    override fun registerMaterial() {
        MaterialCompats.ARDITE.register()
        MaterialCompats.MANYULLYN.register()
        MaterialCompats.ALUMINIUM_BRASS.register()
    }


}