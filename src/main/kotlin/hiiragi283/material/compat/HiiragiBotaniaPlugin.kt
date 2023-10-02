package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiBotaniaPlugin : IHiiragiPlugin {

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
            getEntry<Item>("botania:storage"),
            0
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.TERRASTEEL),
            getEntry<Item>("botania:storage"),
            1
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.ELEMENTIUM),
            getEntry<Item>("botania:storage"),
            2
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.MANA_DIAMOND),
            getEntry<Item>("botania:storage"),
            3
        )
        registerOreDict(
            HiiragiShapes.BLOCK.getOreDict(MaterialCompat.DRAGONSTONE),
            getEntry<Item>("botania:storage"),
            4
        )

        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.MANA_DIAMOND),
            getEntry<Item>("botania:manaresource"),
            2,
            "manaDiamond"
        )
        registerOreDict(
            HiiragiShapes.GEM.getOreDict(MaterialCompat.DRAGONSTONE),
            getEntry<Item>("botania:manaresource"),
            9,
            "elvenDragonstone"
        )
    }

}