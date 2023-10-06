package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiIC2exPlugin : HiiragiPluginBase("ic2", "IC2ex", { HiiragiConfigs.INTEGRATION.ic2Ex }) {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialCommon.RUBBER),
            getEntry<Item>(getResourceLocation("crafting")),
            0,
            "itemRubber"
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCompat.ENDER_PEARL),
            getEntry<Item>(getResourceLocation("dust")),
            31,
            "dustEnderPearl"
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.EMERALD),
            getEntry<Item>(getResourceLocation("dust")),
            34
        )
        registerOreDict(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialCommon.EMERALD),
            getEntry<Item>(getResourceLocation("dust")),
            35
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.ASH),
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