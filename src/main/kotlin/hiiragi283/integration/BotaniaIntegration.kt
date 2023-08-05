package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.util.OreDictUtil
import hiiragi283.core.util.getItem
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object BotaniaIntegration : AbstractIntegration() {

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.MANASTEEL)
        registry.add(MaterialIntegration.MANA_DIAMOND)
        registry.add(MaterialIntegration.TERRASTEEL)
        registry.add(MaterialIntegration.ELEMENTIUM)
        registry.add(MaterialIntegration.DRAGONSTONE)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        OreDictUtil.register(
            HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.MANASTEEL),
            getItem("botania:storage"),
            0
        )
        OreDictUtil.register(
            HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.TERRASTEEL),
            getItem("botania:storage"),
            1
        )
        OreDictUtil.register(
            HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.ELEMENTIUM),
            getItem("botania:storage"),
            2
        )
        OreDictUtil.register(
            HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.MANA_DIAMOND),
            getItem("botania:storage"),
            3
        )
        OreDictUtil.register(
            HiiragiShapes.BLOCK.getOreDict(MaterialIntegration.DRAGONSTONE),
            getItem("botania:storage"),
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

}