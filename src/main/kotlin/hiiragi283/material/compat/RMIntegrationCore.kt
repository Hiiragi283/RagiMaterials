package hiiragi283.material.compat

import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.registerOreDict
import hiiragi283.material.util.shareOredict
import mcjty.theoneprobe.TheOneProbe
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.*
import zone.rong.mixinbooter.ILateMixinLoader

class RMIntegrationCore : hiiragi283.material.compat.AbstractIntegration(), ILateMixinLoader {

    companion object {
        val INSTANCE: hiiragi283.material.compat.RMIntegrationCore = hiiragi283.material.compat.RMIntegrationCore()
    }

    private fun enableIntegration(name: String, config: Boolean): Boolean =
        RMConfig.INTEGRATION.forceIntegration || (Loader.isModLoaded(name) && config)

    private val botania by lazy { enableIntegration("botania", RMConfig.INTEGRATION.botania) }
    private val embers by lazy { enableIntegration("embers", RMConfig.INTEGRATION.embers) }
    private val enderIO by lazy { enableIntegration("enderio", RMConfig.INTEGRATION.enderIO) }
    private val ic2Ex by lazy { enableIntegration("ic2", RMConfig.INTEGRATION.ic2Ex) }
    private val mekanism by lazy { enableIntegration("mekanism", RMConfig.INTEGRATION.mekanism) }
    private val projectRed by lazy { enableIntegration("projectred-core", RMConfig.INTEGRATION.projectRed) }
    private val railCraft by lazy { enableIntegration("railcraft", RMConfig.INTEGRATION.railCraft) }
    private val tCon by lazy { enableIntegration("tconstruct", RMConfig.INTEGRATION.tCon) }
    private val thaum by lazy { enableIntegration("thaumcraft", RMConfig.INTEGRATION.thaum) }
    private val thermal by lazy { enableIntegration("thermalfoundation", RMConfig.INTEGRATION.thermal) }

    override fun getMixinConfigs(): List<String> = listOf()

    override fun onConstruct(event: FMLConstructionEvent) {
        if (botania) RagiMaterials.LOGGER.info("Integration Enabled: Botania")
        if (embers) RagiMaterials.LOGGER.info("Integration Enabled: Embers")
        if (enderIO) RagiMaterials.LOGGER.info("Integration Enabled: Ender IO")
        if (ic2Ex) RagiMaterials.LOGGER.info("Integration Enabled: IC2ex")
        if (mekanism) RagiMaterials.LOGGER.info("Integration Enabled: Mekanism")
        if (projectRed) RagiMaterials.LOGGER.info("Integration Enabled: Project Red")
        if (railCraft) RagiMaterials.LOGGER.info("Integration Enabled: RailCraft")
        if (tCon) RagiMaterials.LOGGER.info("Integration Enabled: Tinker's Construct")
        if (thaum) RagiMaterials.LOGGER.info("Integration Enabled: Thaumcraft")
        if (thermal) RagiMaterials.LOGGER.info("Integration Enabled: Thermal Series")
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        if (botania) hiiragi283.material.compat.BotaniaIntegration.onPreInit(event)
        if (embers) hiiragi283.material.compat.EmbersIntegration.onPreInit(event)
        if (enderIO) hiiragi283.material.compat.EnderIOIntegration.onPreInit(event)
        if (ic2Ex) hiiragi283.material.compat.IC2exIntegration.onPreInit(event)
        if (mekanism) hiiragi283.material.compat.MekanismIntegration.onPreInit(event)
        if (projectRed) hiiragi283.material.compat.ProjectRedIntegration.onPreInit(event)
        if (railCraft) hiiragi283.material.compat.RailCraftIntegration.onPreInit(event)
        if (tCon) hiiragi283.material.compat.TConIntegration.onPreInit(event)
        if (thaum) hiiragi283.material.compat.ThaumIntegration.onPreInit(event)
        if (thermal) hiiragi283.material.compat.ThermalIntegration.onPreInit(event)
    }

    override fun registerMaterial() {

        HiiragiRegistry.registerMaterial(MaterialIntegration.REDSTONE)
        HiiragiRegistry.registerMaterial(MaterialIntegration.LAPIS)
        HiiragiRegistry.registerMaterial(MaterialIntegration.GLOWSTONE)
        HiiragiRegistry.registerMaterial(MaterialIntegration.ENDER_PEARL)

        if (botania) hiiragi283.material.compat.BotaniaIntegration.registerMaterial()
        if (embers) hiiragi283.material.compat.EmbersIntegration.registerMaterial()
        if (enderIO) hiiragi283.material.compat.EnderIOIntegration.registerMaterial()
        if (ic2Ex) hiiragi283.material.compat.IC2exIntegration.registerMaterial()
        if (mekanism) hiiragi283.material.compat.MekanismIntegration.registerMaterial()
        if (projectRed) hiiragi283.material.compat.ProjectRedIntegration.registerMaterial()
        if (railCraft) hiiragi283.material.compat.RailCraftIntegration.registerMaterial()
        if (tCon) hiiragi283.material.compat.TConIntegration.registerMaterial()
        if (thaum) hiiragi283.material.compat.ThaumIntegration.registerMaterial()
        if (thermal) hiiragi283.material.compat.ThermalIntegration.registerMaterial()
    }


    override fun onInit(event: FMLInitializationEvent) {

        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommon.STONE), Blocks.STONE)
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommon.NETHERRACK), Blocks.NETHERRACK)
        registerOreDict(HiiragiShapes.STONE.getOreDict(MaterialCommon.END_STONE), Blocks.END_STONE)

        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.GUNPOWDER),
            Items.GUNPOWDER,
            share = "gunpowder"
        )
        registerOreDict(HiiragiShapes.DUST.getOreDict(MaterialCommon.SUGAR), Items.SUGAR, share = "sugar")
        registerOreDict(HiiragiShapes.GEM.getOreDict(MaterialCommon.CHARCOAL), Items.COAL, 1, share = "charcoal")
        registerOreDict(HiiragiShapes.GEM.getOreDict(MaterialCommon.COAL), Items.COAL, share = "coal")
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialIntegration.ENDER_PEARL),
            Items.ENDER_PEARL,
            share = "enderpearl"
        )
        registerOreDict(HiiragiShapes.STICK.getOreDict(MaterialCommon.WOOD), Items.STICK, share = "stick")

        shareOredict("fuelCoke", "gemCoke")

        if (botania) hiiragi283.material.compat.BotaniaIntegration.onInit(event)
        if (embers) hiiragi283.material.compat.EmbersIntegration.onInit(event)
        if (enderIO) hiiragi283.material.compat.EnderIOIntegration.onInit(event)
        if (ic2Ex) hiiragi283.material.compat.IC2exIntegration.onInit(event)
        if (mekanism) hiiragi283.material.compat.MekanismIntegration.onInit(event)
        if (projectRed) hiiragi283.material.compat.ProjectRedIntegration.onInit(event)
        if (railCraft) hiiragi283.material.compat.RailCraftIntegration.onInit(event)
        if (tCon) hiiragi283.material.compat.TConIntegration.onInit(event)
        if (thaum) hiiragi283.material.compat.ThaumIntegration.onInit(event)
        if (thermal) hiiragi283.material.compat.ThermalIntegration.onInit(event)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        if (botania) hiiragi283.material.compat.BotaniaIntegration.onPostInit(event)
        if (embers) hiiragi283.material.compat.EmbersIntegration.onPostInit(event)
        if (enderIO) hiiragi283.material.compat.EnderIOIntegration.onPostInit(event)
        if (ic2Ex) hiiragi283.material.compat.IC2exIntegration.onPostInit(event)
        if (mekanism) hiiragi283.material.compat.MekanismIntegration.onPostInit(event)
        if (projectRed) hiiragi283.material.compat.ProjectRedIntegration.onPostInit(event)
        if (railCraft) hiiragi283.material.compat.RailCraftIntegration.onPostInit(event)
        if (tCon) hiiragi283.material.compat.TConIntegration.onPostInit(event)
        if (thaum) hiiragi283.material.compat.ThaumIntegration.onPostInit(event)
        if (thermal) hiiragi283.material.compat.ThermalIntegration.onPostInit(event)

        if (Loader.isModLoaded("theoneprobe")) TheOneProbe.theOneProbeImp.registerProvider(hiiragi283.material.compat.TOPIntegration)

    }

    override fun onComplete(event: FMLLoadCompleteEvent) {

    }

}