package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLInitializationEvent

object HiiragiBotaniaPlugin : HiiragiPluginBase("botania", "Botania", { HiiragiConfigs.INTEGRATION.botania }) {

    override fun onInit(event: FMLInitializationEvent) {
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompats.MANASTEEL),
            getEntry<Item>(getResourceLocation("storage")),
            0
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompats.TERRASTEEL),
            getEntry<Item>(getResourceLocation("storage")),
            1
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompats.ELEMENTIUM),
            getEntry<Item>(getResourceLocation("storage")),
            2
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompats.MANA_DIAMOND),
            getEntry<Item>(getResourceLocation("storage")),
            3
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompats.DRAGONSTONE),
            getEntry<Item>(getResourceLocation("storage")),
            4
        )

        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompats.MANA_DIAMOND),
            getEntry<Item>(getResourceLocation("manaresource")),
            2,
            "manaDiamond"
        )
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompats.DRAGONSTONE),
            getEntry<Item>(getResourceLocation("manaresource")),
            9,
            "elvenDragonstone"
        )
    }

}