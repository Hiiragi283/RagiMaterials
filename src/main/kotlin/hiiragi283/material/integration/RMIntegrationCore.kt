package hiiragi283.material.integration

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.material.MaterialIntegration
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.OreDictUtil
import hiiragi283.material.util.getBlock
import hiiragi283.material.util.getItem
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Loader

object RMIntegrationCore {

    private fun enableIntegration(name: String, config: Boolean): Boolean =
        RMConfig.INTEGRATION.forceIntegration || (Loader.isModLoaded(name) && config)

    private val botania by lazy { enableIntegration("botania", RMConfig.INTEGRATION.botania) }
    private val enderIO by lazy { enableIntegration("enderio", RMConfig.INTEGRATION.enderIO) }
    private val embers by lazy { enableIntegration("embers", RMConfig.INTEGRATION.embers) }
    private val ic2Ex by lazy { enableIntegration("ic2", RMConfig.INTEGRATION.ic2Ex) }
    private val mekanism by lazy { enableIntegration("mekanism", RMConfig.INTEGRATION.mekanism) }
    private val projectRed by lazy { enableIntegration("projectred-core", RMConfig.INTEGRATION.projectRed) }
    private val railCraft by lazy { enableIntegration("railcraft", RMConfig.INTEGRATION.railCraft) }
    private val thermal by lazy { enableIntegration("thermalfoundation", RMConfig.INTEGRATION.thermal) }
    private val thaum by lazy { enableIntegration("thaumcraft", RMConfig.INTEGRATION.thaum) }
    private val tCon by lazy { enableIntegration("tconstruct", RMConfig.INTEGRATION.tCon) }

    fun onPreInit() {}

    fun register(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.REDSTONE)
        registry.add(MaterialIntegration.LAPIS)
        registry.add(MaterialIntegration.GLOWSTONE)
        registry.add(MaterialIntegration.ENDER_PEARL)
        if (thermal) {
            RagiMaterials.LOGGER.info("Enabled integration: Thermal Series")
            registry.add(MaterialIntegration.MITHRIL)
            registry.add(MaterialIntegration.SIGNALUM)
            registry.add(MaterialIntegration.LUMIUM)
            registry.add(MaterialIntegration.ENDERIUM)
            registry.add(MaterialIntegration.PYROTHEUM)
            registry.add(MaterialIntegration.CRYOTHEUM)
            registry.add(MaterialIntegration.AEROTHEUM)
            registry.add(MaterialIntegration.PETROTHEUM)
        }
        if (mekanism) {
            RagiMaterials.LOGGER.info("Enabled integration: Mekanism")
            registry.add(MaterialIntegration.OBSIDIAN_REFINED)
            registry.add(MaterialIntegration.GLOWSTONE_REFINED)
        }
        if (enderIO) {
            RagiMaterials.LOGGER.info("Enabled integration: Ender IO")
            registry.add(MaterialIntegration.ELECTRICAL_STEEL)
            registry.add(MaterialIntegration.ENERGETIC_ALLOY)
            registry.add(MaterialIntegration.VIBRANT_ALLOY)
            registry.add(MaterialIntegration.REDSTONE_ALLOY)
            registry.add(MaterialIntegration.CONDUCTIVE_IRON)
            registry.add(MaterialIntegration.PULSATING_IRON)
            registry.add(MaterialIntegration.DARK_STEEL)
            registry.add(MaterialIntegration.SOULARIUM)
            registry.add(MaterialIntegration.END_STEEL)
            registry.add(MaterialIntegration.IRON_ALLOY)
        }
        if (thaum) {
            RagiMaterials.LOGGER.info("Enabled integration: Thaumcraft")
            registry.add(MaterialIntegration.THAUMIUM)
            registry.add(MaterialIntegration.VOID_METAL)
        }
        if (botania) {
            RagiMaterials.LOGGER.info("Enabled integration: Botania")
            registry.add(MaterialIntegration.MANASTEEL)
            registry.add(MaterialIntegration.MANA_DIAMOND)
            registry.add(MaterialIntegration.TERRASTEEL)
            registry.add(MaterialIntegration.ELEMENTIUM)
            registry.add(MaterialIntegration.DRAGONSTONE)
        }
        if (embers) {
            RagiMaterials.LOGGER.info("Enabled integration: Embers")
            registry.add(MaterialIntegration.DAWNSTONE)
        }
        if (projectRed) {
            RagiMaterials.LOGGER.info("Enabled integration: ProjectRed")
            registry.add(MaterialIntegration.ELECTROTINE)
            registry.add(MaterialIntegration.RED_ALLOY)
            registry.add(MaterialIntegration.ELECTROTINE_ALLOY)
        }
        if (tCon) {
            RagiMaterials.LOGGER.info("Enabled integration: Tinker's Construct")
            registry.add(MaterialIntegration.ARDITE)
            registry.add(MaterialIntegration.MANYULLYN)
            registry.add(MaterialIntegration.ALUMINIUM_BRASS)
        }
    }


    fun onInit() {
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

        //OreDictUtil.shareOredict("dustSaltpeter", "dustNiter")
        OreDictUtil.shareOredict("fuelCoke", "gemCoke")
    }

    fun onPostInit() {
        if (botania) {
            OreDictUtil.register(
                HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.MANASTEEL),
                getBlock("botania:storage"),
                0
            )
            OreDictUtil.register(
                HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.TERRASTEEL),
                getBlock("botania:storage"),
                1
            )
            OreDictUtil.register(
                HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.ELEMENTIUM),
                getBlock("botania:storage"),
                2
            )
            OreDictUtil.register(
                HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.MANA_DIAMOND),
                getBlock("botania:storage"),
                3
            )
            OreDictUtil.register(
                HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.DRAGONSTONE),
                getBlock("botania:storage"),
                4
            )

            OreDictUtil.register(
                HiiragiShapes.GEM.getOreDict(MaterialIntegration.MANA_DIAMOND),
                getItem("botania:manaresource"),
                2,
                "manaDiamond"
            )
            OreDictUtil.register(
                HiiragiShapes.GEM.getOreDict(MaterialIntegration.DRAGONSTONE),
                getItem("botania:manaresource"),
                9,
                "elvenDragonstone"
            )
        }
        if (enderIO) {
            OreDictUtil.register(
                HiiragiShapes.BALL.getOreDict(MaterialIntegration.SIGNALUM),
                getItem("enderio:item_material"),
                57
            )
            OreDictUtil.register(
                HiiragiShapes.BALL.getOreDict(MaterialIntegration.LUMIUM),
                getItem("enderio:item_material"),
                58
            )
            OreDictUtil.register(
                HiiragiShapes.BALL.getOreDict(MaterialIntegration.ENDERIUM),
                getItem("enderio:item_material"),
                59
            )
        }
        if (ic2Ex) {
            OreDictUtil.register(
                HiiragiShapes.INGOT.getOreDict(MaterialCommon.RUBBER),
                getItem("ic2:crafting"),
                0,
                "itemRubber"
            )
            OreDictUtil.register(
                HiiragiShapes.DUST.getOreDict(MaterialIntegration.ENDER_PEARL),
                getItem("ic2:dust"),
                31,
                "dustEnderPearl"
            )
            OreDictUtil.register(
                HiiragiShapes.DUST.getOreDict(MaterialCommon.EMERALD),
                getItem("ic2:dust"),
                34
            )
            OreDictUtil.register(
                HiiragiShapes.DUST_TINY.getOreDict(MaterialCommon.EMERALD),
                getItem("ic2:dust"),
                35
            )
            OreDictUtil.register(
                HiiragiShapes.DUST.getOreDict(MaterialCommon.ASH),
                getItem("ic2:misc_resource"),
                0,
            )
            OreDictUtil.register(
                HiiragiShapes.DUST_TINY.getOreDict(MaterialElements.IODINE),
                getItem("ic2:misc_resource"),
                6,
            )
            OreDictUtil.register(
                HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM235),
                getItem("ic2:nuclear"),
                1,
            )
            OreDictUtil.register(
                HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM),
                getItem("ic2:nuclear"),
                2,
            )
            OreDictUtil.register(
                HiiragiShapes.INGOT.getOreDict(MaterialElements.PLUTONIUM),
                getItem("ic2:nuclear"),
                3,
            )
            OreDictUtil.register(
                HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM235),
                getItem("ic2:nuclear"),
                5,
            )
            OreDictUtil.register(
                HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM),
                getItem("ic2:nuclear"),
                6,
            )
            OreDictUtil.register(
                HiiragiShapes.NUGGET.getOreDict(MaterialElements.PLUTONIUM),
                getItem("ic2:nuclear"),
                6,
            )
        }
        if (thaum) {
            PartRegistry.registerTag("quicksilver", HiiragiPart(HiiragiShapes.GEM, MaterialCommon.CINNABAR))
            PartRegistry.registerTag(
                "nuggetQuicksilver",
                HiiragiPart(HiiragiShapes.NUGGET, MaterialCommon.CINNABAR)
            )
        }
    }

}