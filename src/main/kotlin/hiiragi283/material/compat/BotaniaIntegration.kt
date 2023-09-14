package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.getItem
import hiiragi283.material.util.registerOreDict
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object BotaniaIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        MaterialCompat.MANASTEEL.register()
        MaterialCompat.MANA_DIAMOND.register()
        MaterialCompat.TERRASTEEL.register()
        MaterialCompat.ELEMENTIUM.register()
        MaterialCompat.DRAGONSTONE.register()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.MANASTEEL),
            getItem("botania:storage"),
            0
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.TERRASTEEL),
            getItem("botania:storage"),
            1
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.ELEMENTIUM),
            getItem("botania:storage"),
            2
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.MANA_DIAMOND),
            getItem("botania:storage"),
            3
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.DRAGONSTONE),
            getItem("botania:storage"),
            4
        )

        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.MANA_DIAMOND),
            getItem("botania:manaresource"),
            2,
            "manaDiamond"
        )
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.DRAGONSTONE),
            getItem("botania:manaresource"),
            9,
            "elvenDragonstone"
        )
    }

}