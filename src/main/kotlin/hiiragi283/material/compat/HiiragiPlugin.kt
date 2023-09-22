package hiiragi283.material.compat


import hiiragi283.material.RagiMaterials
import hiiragi283.material.config.RMConfig
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

object HiiragiPlugin : IHiiragiPlugin {

    private fun enableIntegration(name: String, config: Boolean): Boolean =
        RMConfig.INTEGRATION.forceIntegration || (Loader.isModLoaded(name) && config)

    private val botania = { enableIntegration("botania", RMConfig.INTEGRATION.botania) }
    private val embers = { enableIntegration("embers", RMConfig.INTEGRATION.embers) }
    private val enderIO = { enableIntegration("enderio", RMConfig.INTEGRATION.enderIO) }
    private val ic2Ex = { enableIntegration("ic2", RMConfig.INTEGRATION.ic2Ex) }
    private val mekanism = { enableIntegration("mekanism", RMConfig.INTEGRATION.mekanism) }
    private val projectRed = { enableIntegration("projectred-core", RMConfig.INTEGRATION.projectRed) }
    private val railCraft = { enableIntegration("railcraft", RMConfig.INTEGRATION.railCraft) }
    private val tCon = { enableIntegration("tconstruct", RMConfig.INTEGRATION.tCon) }
    private val thaum = { enableIntegration("thaumcraft", RMConfig.INTEGRATION.thaum) }
    private val thermal = { enableIntegration("thermalfoundation", RMConfig.INTEGRATION.thermal) }
    private val top = { enableIntegration("theoneprobe", true) }

    private val list: MutableList<IHiiragiPlugin> = mutableListOf(HiiragiVanillaPlugin)

    override fun onConstruct(event: FMLConstructionEvent) {
        if (botania()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Botania")
            list.add(HiiragiBotaniaPlugin)
        }
        if (embers()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Embers")
            list.add(HiiragiEmbersPlugin)
        }
        if (enderIO()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Ender IO")
            list.add(HiiragiEnderIOPlugin)
        }
        if (ic2Ex()) {
            RagiMaterials.LOGGER.info("Integration Enabled: IC2ex")
            list.add(HiiragiIC2exPlugin)
        }
        if (mekanism()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Mekanism")
            list.add(HiiragiMekanismPlugin)
        }
        if (projectRed()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Project Red")
            list.add(HiiragiProjectRedPlugin)
        }
        if (railCraft()) {
            RagiMaterials.LOGGER.info("Integration Enabled: RailCraft")
            list.add(HiiragiRailCraftPlugin)
        }
        if (tCon()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Tinker's Construct")
            list.add(HiiragiTConPlugin)
        }
        if (thaum()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Thaumcraft")
            list.add(HiiragiThaumPlugin)
        }
        if (thermal()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Thermal Series")
            list.add(HiiragiThermalPlugin)
        }
        if (top()) {
            RagiMaterials.LOGGER.info("Integration Enabled: Thermal Series")
            list.add(HiiragiTOPPlugin)
        }
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        list.forEach { it.onPreInit(event) }
    }

    override fun registerMaterial() {
        list.forEach { it.registerMaterial() }
    }

    override fun onInit(event: FMLInitializationEvent) {
        list.forEach { it.onInit(event) }
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        list.forEach { it.onPostInit(event) }
    }

}