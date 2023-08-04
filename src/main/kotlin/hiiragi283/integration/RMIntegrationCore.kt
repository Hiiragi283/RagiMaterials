package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.config.RMConfig
import hiiragi283.core.util.OreDictUtil
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Loader
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

    override fun onPreInit() {
        if (botania) BotaniaIntegration.onPreInit()
        if (embers) EmbersIntegration.onPreInit()
        if (enderIO) EnderIOIntegration.onPreInit()
        if (ic2Ex) IC2exIntegration.onPreInit()
        if (mekanism) MekanismIntegration.onPreInit()
        if (projectRed) ProjectRedIntegration.onPreInit()
        if (railCraft) RailCraftIntegration.onPreInit()
        if (tCon) TConIntegration.onPreInit()
        if (thaum) ThaumIntegration.onPreInit()
        if (thermal) ThermalIntegration.onPreInit()
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


    override fun onInit() {
        OreDictUtil.register(HiiragiShapes.STONE.getOreDict(MaterialCommon.STONE), Blocks.STONE)
        OreDictUtil.register(HiiragiShapes.STONE.getOreDict(MaterialCommon.NETHERRACK), Blocks.NETHERRACK)
        OreDictUtil.register(HiiragiShapes.STONE.getOreDict(MaterialCommon.END_STONE), Blocks.END_STONE)

        OreDictUtil.register(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.GUNPOWDER),
            Items.GUNPOWDER,
            share = "gunpowder"
        )
        OreDictUtil.register(HiiragiShapes.DUST.getOreDict(MaterialCommon.SUGAR), Items.SUGAR, share = "sugar")
        OreDictUtil.register(HiiragiShapes.GEM.getOreDict(MaterialCommon.CHARCOAL), Items.COAL, 1, share = "charcoal")
        OreDictUtil.register(HiiragiShapes.GEM.getOreDict(MaterialCommon.COAL), Items.COAL, share = "coal")
        OreDictUtil.register(
            HiiragiShapes.GEM.getOreDict(MaterialIntegration.ENDER_PEARL),
            Items.ENDER_PEARL,
            share = "enderpearl"
        )
        OreDictUtil.register(HiiragiShapes.STICK.getOreDict(MaterialCommon.WOOD), Items.STICK, share = "stick")

        OreDictUtil.shareOredict("fuelCoke", "gemCoke")

        if (botania) BotaniaIntegration.onInit()
        if (embers) EmbersIntegration.onInit()
        if (enderIO) EnderIOIntegration.onInit()
        if (ic2Ex) IC2exIntegration.onInit()
        if (mekanism) MekanismIntegration.onInit()
        if (projectRed) ProjectRedIntegration.onInit()
        if (railCraft) RailCraftIntegration.onInit()
        if (tCon) TConIntegration.onInit()
        if (thaum) ThaumIntegration.onInit()
        if (thermal) ThermalIntegration.onInit()
    }

    override fun onPostInit() {
        if (botania) BotaniaIntegration.onPostInit()
        if (embers) EmbersIntegration.onPostInit()
        if (enderIO) EnderIOIntegration.onPostInit()
        if (ic2Ex) IC2exIntegration.onPostInit()
        if (mekanism) MekanismIntegration.onPostInit()
        if (projectRed) ProjectRedIntegration.onPostInit()
        if (railCraft) RailCraftIntegration.onPostInit()
        if (tCon) TConIntegration.onPostInit()
        if (thaum) ThaumIntegration.onPostInit()
        if (thermal) ThermalIntegration.onPostInit()
    }

    override fun onComplete() {
        if (botania) BotaniaIntegration.onComplete()
        if (embers) EmbersIntegration.onComplete()
        if (enderIO) EnderIOIntegration.onComplete()
        if (ic2Ex) IC2exIntegration.onComplete()
        if (mekanism) MekanismIntegration.onComplete()
        if (projectRed) ProjectRedIntegration.onComplete()
        if (railCraft) RailCraftIntegration.onComplete()
        if (tCon) TConIntegration.onComplete()
        if (thaum) ThaumIntegration.onComplete()
        if (thermal) ThermalIntegration.onComplete()
    }

}