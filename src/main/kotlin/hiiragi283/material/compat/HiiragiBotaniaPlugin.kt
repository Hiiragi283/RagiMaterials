package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiBotaniaPlugin : HiiragiPluginBase("botania", "Botania", HiiragiConfigs.INTEGRATION::botania) {

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
            getEntry<Item>(getResourceLocation("storage")),
            0
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.TERRASTEEL),
            getEntry<Item>(getResourceLocation("storage")),
            1
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.ELEMENTIUM),
            getEntry<Item>(getResourceLocation("storage")),
            2
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.MANA_DIAMOND),
            getEntry<Item>(getResourceLocation("storage")),
            3
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.DRAGONSTONE),
            getEntry<Item>(getResourceLocation("storage")),
            4
        )

        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.MANA_DIAMOND),
            getEntry<Item>(getResourceLocation("manaresource")),
            2,
            "manaDiamond"
        )
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.DRAGONSTONE),
            getEntry<Item>(getResourceLocation("manaresource")),
            9,
            "elvenDragonstone"
        )
    }

}