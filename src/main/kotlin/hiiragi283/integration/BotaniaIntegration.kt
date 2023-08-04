package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.RagiMaterials
import hiiragi283.core.util.OreDictUtil
import hiiragi283.core.util.getBlock
import hiiragi283.core.util.getItem

object BotaniaIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: Botania")
        registry.add(MaterialIntegration.MANASTEEL)
        registry.add(MaterialIntegration.MANA_DIAMOND)
        registry.add(MaterialIntegration.TERRASTEEL)
        registry.add(MaterialIntegration.ELEMENTIUM)
        registry.add(MaterialIntegration.DRAGONSTONE)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
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

    override fun onComplete() {
    }

}