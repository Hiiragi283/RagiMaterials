package hiiragi283.material.integration

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.material.MaterialIntegration
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.util.OreDictUtil
import hiiragi283.material.util.getBlock
import hiiragi283.material.util.getItem
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraftforge.fml.common.Loader

object RMIntegrationCore {

    fun enableBotania() = Loader.isModLoaded("botania")
    fun enableEIO() = Loader.isModLoaded("enderio")
    fun enableEmbers() = Loader.isModLoaded("embers")
    fun enableIC2() = Loader.isModLoaded("ic2")
    fun enableMek() = Loader.isModLoaded("mekanism")
    fun enableTE() = Loader.isModLoaded("thermalfoundation")
    fun enableThaum() = Loader.isModLoaded("thaumcraft")

    fun onPreInit() {}

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
        if (enableBotania()) {
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
        if (enableEIO()) {
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
        if (enableIC2()) {
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
        if (enableThaum()) {
            PartRegistry.registerTag("quicksilver", HiiragiPart(ShapeRegistry.GEM, MaterialCommon.CINNABAR))
            PartRegistry.registerTag(
                "nuggetQuicksilver",
                HiiragiPart(ShapeRegistry.NUGGET, MaterialCommon.CINNABAR)
            )
        }
    }

}