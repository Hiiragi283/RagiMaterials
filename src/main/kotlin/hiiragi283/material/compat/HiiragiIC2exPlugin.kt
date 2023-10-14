package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.init.materials.MaterialElements
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiIC2exPlugin : HiiragiPluginBase("ic2", "IC2ex", { HiiragiConfigs.INTEGRATION.ic2Ex }) {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialCommons.RUBBER),
            getEntry<Item>(getResourceLocation("crafting")),
            0,
            "itemRubber"
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCompats.ENDER_PEARL),
            getEntry<Item>(getResourceLocation("dust")),
            31,
            "dustEnderPearl"
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommons.EMERALD),
            getEntry<Item>(getResourceLocation("dust")),
            34
        )
        registerOreDict(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialCommons.EMERALD),
            getEntry<Item>(getResourceLocation("dust")),
            35
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommons.ASH),
            getEntry<Item>(getResourceLocation("misc_resource")),
            0,
        )
        registerOreDict(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialElements.IODINE),
            getEntry<Item>(getResourceLocation("misc_resource")),
            6,
        )
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM235),
            getEntry<Item>(getResourceLocation("nuclear")),
            1,
        )
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM),
            getEntry<Item>(getResourceLocation("nuclear")),
            2,
        )
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.PLUTONIUM),
            getEntry<Item>(getResourceLocation("nuclear")),
            3,
        )
        registerOreDict(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM235),
            getEntry<Item>(getResourceLocation("nuclear")),
            5,
        )
        registerOreDict(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM),
            getEntry<Item>(getResourceLocation("nuclear")),
            6,
        )
        registerOreDict(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.PLUTONIUM),
            getEntry<Item>(getResourceLocation("nuclear")),
            6,
        )
    }

}