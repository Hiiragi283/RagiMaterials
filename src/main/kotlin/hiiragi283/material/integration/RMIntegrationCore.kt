package hiiragi283.material.integration

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.material.MaterialIntegration
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.OreDictUtil
import hiiragi283.material.util.getBlock
import hiiragi283.material.util.getItem
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.registries.IForgeRegistry

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

    fun register(registry: IForgeRegistry<HiiragiMaterial>) {
        registry.register(MaterialIntegration.REDSTONE)
        registry.register(MaterialIntegration.LAPIS)
        registry.register(MaterialIntegration.GLOWSTONE)
        registry.register(MaterialIntegration.ENDER_PEARL)
        if (thermal) {
            RagiMaterials.LOGGER.info("Enabled integration: Thermal Series")
            registry.register(MaterialIntegration.MITHRIL)
            registry.register(MaterialIntegration.SIGNALUM)
            registry.register(MaterialIntegration.LUMIUM)
            registry.register(MaterialIntegration.ENDERIUM)
            registry.register(MaterialIntegration.PYROTHEUM)
            registry.register(MaterialIntegration.CRYOTHEUM)
            registry.register(MaterialIntegration.AEROTHEUM)
            registry.register(MaterialIntegration.PETROTHEUM)
        }
        if (mekanism) {
            RagiMaterials.LOGGER.info("Enabled integration: Mekanism")
            registry.register(MaterialIntegration.OBSIDIAN_REFINED)
            registry.register(MaterialIntegration.GLOWSTONE_REFINED)
        }
        if (enderIO) {
            RagiMaterials.LOGGER.info("Enabled integration: Ender IO")
            registry.register(MaterialIntegration.ELECTRICAL_STEEL)
            registry.register(MaterialIntegration.ENERGETIC_ALLOY)
            registry.register(MaterialIntegration.VIBRANT_ALLOY)
            registry.register(MaterialIntegration.REDSTONE_ALLOY)
            registry.register(MaterialIntegration.CONDUCTIVE_IRON)
            registry.register(MaterialIntegration.PULSATING_IRON)
            registry.register(MaterialIntegration.DARK_STEEL)
            registry.register(MaterialIntegration.SOULARIUM)
            registry.register(MaterialIntegration.END_STEEL)
            registry.register(MaterialIntegration.IRON_ALLOY)
        }
        if (thaum) {
            RagiMaterials.LOGGER.info("Enabled integration: Thaumcraft")
            registry.register(MaterialIntegration.THAUMIUM)
            registry.register(MaterialIntegration.VOID_METAL)
        }
        if (botania) {
            RagiMaterials.LOGGER.info("Enabled integration: Botania")
            registry.register(MaterialIntegration.MANASTEEL)
            registry.register(MaterialIntegration.MANA_DIAMOND)
            registry.register(MaterialIntegration.TERRASTEEL)
            registry.register(MaterialIntegration.ELEMENTIUM)
            registry.register(MaterialIntegration.DRAGONSTONE)
        }
        if (embers) {
            RagiMaterials.LOGGER.info("Enabled integration: Embers")
            registry.register(MaterialIntegration.DAWNSTONE)
        }
        if (projectRed) {
            RagiMaterials.LOGGER.info("Enabled integration: ProjectRed")
            registry.register(MaterialIntegration.ELECTROTINE)
            registry.register(MaterialIntegration.RED_ALLOY)
            registry.register(MaterialIntegration.ELECTROTINE_ALLOY)
        }
        if (tCon) {
            RagiMaterials.LOGGER.info("Enabled integration: Tinker's Construct")
            registry.register(MaterialIntegration.ARDITE)
            registry.register(MaterialIntegration.MANYULLYN)
            registry.register(MaterialIntegration.ALUMINIUM_BRASS)
        }
    }


    fun onInit() {
        OreDictUtil.register(ShapeRegistry.STONE.getOreDict(MaterialCommon.STONE), Blocks.STONE)
        OreDictUtil.register(ShapeRegistry.STONE.getOreDict(MaterialCommon.NETHERRACK), Blocks.NETHERRACK)
        OreDictUtil.register(ShapeRegistry.STONE.getOreDict(MaterialCommon.END_STONE), Blocks.END_STONE)

        OreDictUtil.register(
            ShapeRegistry.DUST.getOreDict(MaterialCommon.GUNPOWDER),
            Items.GUNPOWDER,
            share = "gunpowder"
        )
        OreDictUtil.register(ShapeRegistry.DUST.getOreDict(MaterialCommon.SUGAR), Items.SUGAR, share = "sugar")
        OreDictUtil.register(ShapeRegistry.GEM.getOreDict(MaterialCommon.CHARCOAL), Items.COAL, 1, share = "charcoal")
        OreDictUtil.register(ShapeRegistry.GEM.getOreDict(MaterialCommon.COAL), Items.COAL, share = "coal")
        OreDictUtil.register(
            ShapeRegistry.GEM.getOreDict(MaterialIntegration.ENDER_PEARL),
            Items.ENDER_PEARL,
            share = "enderpearl"
        )
        OreDictUtil.register(ShapeRegistry.STICK.getOreDict(MaterialCommon.WOOD), Items.STICK, share = "stick")

        //OreDictUtil.shareOredict("dustSaltpeter", "dustNiter")
        OreDictUtil.shareOredict("fuelCoke", "gemCoke")
    }

    fun onPostInit() {
        if (botania) {
            OreDictUtil.register(
                ShapeRegistry.BLOCK.getOreDict(MaterialIntegration.MANASTEEL),
                getBlock("botania:storage"),
                0
            )
            OreDictUtil.register(
                ShapeRegistry.BLOCK.getOreDict(MaterialIntegration.TERRASTEEL),
                getBlock("botania:storage"),
                1
            )
            OreDictUtil.register(
                ShapeRegistry.BLOCK.getOreDict(MaterialIntegration.ELEMENTIUM),
                getBlock("botania:storage"),
                2
            )
            OreDictUtil.register(
                ShapeRegistry.BLOCK.getOreDict(MaterialIntegration.MANA_DIAMOND),
                getBlock("botania:storage"),
                3
            )
            OreDictUtil.register(
                ShapeRegistry.BLOCK.getOreDict(MaterialIntegration.DRAGONSTONE),
                getBlock("botania:storage"),
                4
            )

            OreDictUtil.register(
                ShapeRegistry.GEM.getOreDict(MaterialIntegration.MANA_DIAMOND),
                getItem("botania:manaresource"),
                2,
                "manaDiamond"
            )
            OreDictUtil.register(
                ShapeRegistry.GEM.getOreDict(MaterialIntegration.DRAGONSTONE),
                getItem("botania:manaresource"),
                9,
                "elvenDragonstone"
            )
        }
        if (enderIO) {
            OreDictUtil.register(
                ShapeRegistry.BALL.getOreDict(MaterialIntegration.SIGNALUM),
                getItem("enderio:item_material"),
                57
            )
            OreDictUtil.register(
                ShapeRegistry.BALL.getOreDict(MaterialIntegration.LUMIUM),
                getItem("enderio:item_material"),
                58
            )
            OreDictUtil.register(
                ShapeRegistry.BALL.getOreDict(MaterialIntegration.ENDERIUM),
                getItem("enderio:item_material"),
                59
            )
        }
        if (ic2Ex) {
            OreDictUtil.register(
                ShapeRegistry.INGOT.getOreDict(MaterialCommon.RUBBER),
                getItem("ic2:crafting"),
                0,
                "itemRubber"
            )
            OreDictUtil.register(
                ShapeRegistry.DUST.getOreDict(MaterialIntegration.ENDER_PEARL),
                getItem("ic2:dust"),
                31,
                "dustEnderPearl"
            )
            OreDictUtil.register(
                ShapeRegistry.DUST.getOreDict(MaterialCommon.EMERALD),
                getItem("ic2:dust"),
                34
            )
            OreDictUtil.register(
                ShapeRegistry.DUST_TINY.getOreDict(MaterialCommon.EMERALD),
                getItem("ic2:dust"),
                35
            )
            OreDictUtil.register(
                ShapeRegistry.DUST.getOreDict(MaterialCommon.ASH),
                getItem("ic2:misc_resource"),
                0,
            )
            OreDictUtil.register(
                ShapeRegistry.DUST_TINY.getOreDict(MaterialElements.IODINE),
                getItem("ic2:misc_resource"),
                6,
            )
            OreDictUtil.register(
                ShapeRegistry.INGOT.getOreDict(MaterialElements.URANIUM235),
                getItem("ic2:nuclear"),
                1,
            )
            OreDictUtil.register(
                ShapeRegistry.INGOT.getOreDict(MaterialElements.URANIUM),
                getItem("ic2:nuclear"),
                2,
            )
            OreDictUtil.register(
                ShapeRegistry.INGOT.getOreDict(MaterialElements.PLUTONIUM),
                getItem("ic2:nuclear"),
                3,
            )
            OreDictUtil.register(
                ShapeRegistry.NUGGET.getOreDict(MaterialElements.URANIUM235),
                getItem("ic2:nuclear"),
                5,
            )
            OreDictUtil.register(
                ShapeRegistry.NUGGET.getOreDict(MaterialElements.URANIUM),
                getItem("ic2:nuclear"),
                6,
            )
            OreDictUtil.register(
                ShapeRegistry.NUGGET.getOreDict(MaterialElements.PLUTONIUM),
                getItem("ic2:nuclear"),
                6,
            )
        }
        if (thaum) {
            PartRegistry.registerTag("quicksilver", HiiragiPart(ShapeRegistry.GEM, MaterialCommon.CINNABAR))
            PartRegistry.registerTag(
                "nuggetQuicksilver",
                HiiragiPart(ShapeRegistry.NUGGET, MaterialCommon.CINNABAR)
            )
        }
    }

}