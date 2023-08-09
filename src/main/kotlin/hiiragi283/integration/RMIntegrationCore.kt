package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.RagiMaterials
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.registerOreDict
import hiiragi283.material.util.shareOredict
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.*
import zone.rong.mixinbooter.ILateMixinLoader

class RMIntegrationCore : AbstractIntegration(), ILateMixinLoader {

    companion object {
        val INSTANCE: RMIntegrationCore = RMIntegrationCore()
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

    override fun getMixinConfigs(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()
        if (enderIO) list.add("mixins.ragi_materials.ender_io.json")
        return list
    }

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
        if (botania) BotaniaIntegration.onPreInit(event)
        if (embers) EmbersIntegration.onPreInit(event)
        if (enderIO) EnderIOIntegration.onPreInit(event)
        if (ic2Ex) IC2exIntegration.onPreInit(event)
        if (mekanism) MekanismIntegration.onPreInit(event)
        if (projectRed) ProjectRedIntegration.onPreInit(event)
        if (railCraft) RailCraftIntegration.onPreInit(event)
        if (tCon) TConIntegration.onPreInit(event)
        if (thaum) ThaumIntegration.onPreInit(event)
        if (thermal) ThermalIntegration.onPreInit(event)
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.REDSTONE)
        registry.add(MaterialIntegration.LAPIS)
        registry.add(MaterialIntegration.GLOWSTONE)
        registry.add(MaterialIntegration.ENDER_PEARL)

        if (botania) BotaniaIntegration.registerMaterial(registry)
        if (embers) EmbersIntegration.registerMaterial(registry)
        if (enderIO) EnderIOIntegration.registerMaterial(registry)
        if (ic2Ex) IC2exIntegration.registerMaterial(registry)
        if (mekanism) MekanismIntegration.registerMaterial(registry)
        if (projectRed) ProjectRedIntegration.registerMaterial(registry)
        if (railCraft) RailCraftIntegration.registerMaterial(registry)
        if (tCon) TConIntegration.registerMaterial(registry)
        if (thaum) ThaumIntegration.registerMaterial(registry)
        if (thermal) ThermalIntegration.registerMaterial(registry)
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

        if (botania) BotaniaIntegration.onInit(event)
        if (embers) EmbersIntegration.onInit(event)
        if (enderIO) EnderIOIntegration.onInit(event)
        if (ic2Ex) IC2exIntegration.onInit(event)
        if (mekanism) MekanismIntegration.onInit(event)
        if (projectRed) ProjectRedIntegration.onInit(event)
        if (railCraft) RailCraftIntegration.onInit(event)
        if (tCon) TConIntegration.onInit(event)
        if (thaum) ThaumIntegration.onInit(event)
        if (thermal) ThermalIntegration.onInit(event)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        if (botania) BotaniaIntegration.onPostInit(event)
        if (embers) EmbersIntegration.onPostInit(event)
        if (enderIO) EnderIOIntegration.onPostInit(event)
        if (ic2Ex) IC2exIntegration.onPostInit(event)
        if (mekanism) MekanismIntegration.onPostInit(event)
        if (projectRed) ProjectRedIntegration.onPostInit(event)
        if (railCraft) RailCraftIntegration.onPostInit(event)
        if (tCon) TConIntegration.onPostInit(event)
        if (thaum) ThaumIntegration.onPostInit(event)
        if (thermal) ThermalIntegration.onPostInit(event)
    }

    override fun onComplete(event: FMLLoadCompleteEvent) {

    }

}