package hiiragi283.material.integration

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.material.MaterialIntegration
import hiiragi283.material.api.material.MaterialRegistry
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

    fun onPreInit() {
        MaterialRegistry.registerMaterial(MaterialIntegration.REDSTONE)
        MaterialRegistry.registerMaterial(MaterialIntegration.LAPIS)
        MaterialRegistry.registerMaterial(MaterialIntegration.GLOWSTONE)
        MaterialRegistry.registerMaterial(MaterialIntegration.ENDER_PEARL)
        if (thermal) {
            RagiMaterials.LOGGER.info("Enabled integration: Thermal Series")
            MaterialRegistry.registerMaterial(MaterialIntegration.MITHRIL)
            MaterialRegistry.registerMaterial(MaterialIntegration.SIGNALUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.LUMIUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.ENDERIUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.PYROTHEUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.CRYOTHEUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.AEROTHEUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.PETROTHEUM)
        }
        if (mekanism) {
            RagiMaterials.LOGGER.info("Enabled integration: Mekanism")
            MaterialRegistry.registerMaterial(MaterialIntegration.OBSIDIAN_REFINED)
            MaterialRegistry.registerMaterial(MaterialIntegration.GLOWSTONE_REFINED)
        }
        if (enderIO) {
            RagiMaterials.LOGGER.info("Enabled integration: Ender IO")
            MaterialRegistry.registerMaterial(MaterialIntegration.ELECTRICAL_STEEL)
            MaterialRegistry.registerMaterial(MaterialIntegration.ENERGETIC_ALLOY)
            MaterialRegistry.registerMaterial(MaterialIntegration.VIBRANT_ALLOY)
            MaterialRegistry.registerMaterial(MaterialIntegration.REDSTONE_ALLOY)
            MaterialRegistry.registerMaterial(MaterialIntegration.CONDUCTIVE_IRON)
            MaterialRegistry.registerMaterial(MaterialIntegration.PULSATING_IRON)
            MaterialRegistry.registerMaterial(MaterialIntegration.DARK_STEEL)
            MaterialRegistry.registerMaterial(MaterialIntegration.SOULARIUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.END_STEEL)
            MaterialRegistry.registerMaterial(MaterialIntegration.IRON_ALLOY)
        }
        if (thaum) {
            RagiMaterials.LOGGER.info("Enabled integration: Thaumcraft")
            MaterialRegistry.registerMaterial(MaterialIntegration.THAUMIUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.VOID_METAL)
        }
        if (botania) {
            RagiMaterials.LOGGER.info("Enabled integration: Botania")
            MaterialRegistry.registerMaterial(MaterialIntegration.MANASTEEL)
            MaterialRegistry.registerMaterial(MaterialIntegration.MANA_DIAMOND)
            MaterialRegistry.registerMaterial(MaterialIntegration.TERRASTEEL)
            MaterialRegistry.registerMaterial(MaterialIntegration.ELEMENTIUM)
            MaterialRegistry.registerMaterial(MaterialIntegration.DRAGONSTONE)
        }
        if (embers) {
            RagiMaterials.LOGGER.info("Enabled integration: Embers")
            MaterialRegistry.registerMaterial(MaterialIntegration.DAWNSTONE)
        }
        if (projectRed) {
            RagiMaterials.LOGGER.info("Enabled integration: ProjectRed")
            MaterialRegistry.registerMaterial(MaterialIntegration.ELECTROTINE)
            MaterialRegistry.registerMaterial(MaterialIntegration.RED_ALLOY)
            MaterialRegistry.registerMaterial(MaterialIntegration.ELECTROTINE_ALLOY)
        }
        if (tCon) {
            RagiMaterials.LOGGER.info("Enabled integration: Tinker's Construct")
            MaterialRegistry.registerMaterial(MaterialIntegration.ARDITE)
            MaterialRegistry.registerMaterial(MaterialIntegration.MANYULLYN)
            MaterialRegistry.registerMaterial(MaterialIntegration.ALUMINIUM_BRASS)
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