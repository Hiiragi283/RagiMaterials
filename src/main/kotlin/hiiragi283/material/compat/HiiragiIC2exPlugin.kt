package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.getItem
import hiiragi283.material.util.registerOreDict
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiIC2exPlugin : IHiiragiPlugin {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialCommon.RUBBER),
            getItem("ic2:crafting"),
            0,
            "itemRubber"
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCompat.ENDER_PEARL),
            getItem("ic2:dust"),
            31,
            "dustEnderPearl"
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.EMERALD),
            getItem("ic2:dust"),
            34
        )
        registerOreDict(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialCommon.EMERALD),
            getItem("ic2:dust"),
            35
        )
        registerOreDict(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.ASH),
            getItem("ic2:misc_resource"),
            0,
        )
        registerOreDict(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialElements.IODINE),
            getItem("ic2:misc_resource"),
            6,
        )
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM235),
            getItem("ic2:nuclear"),
            1,
        )
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM),
            getItem("ic2:nuclear"),
            2,
        )
        registerOreDict(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.PLUTONIUM),
            getItem("ic2:nuclear"),
            3,
        )
        registerOreDict(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM235),
            getItem("ic2:nuclear"),
            5,
        )
        registerOreDict(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM),
            getItem("ic2:nuclear"),
            6,
        )
        registerOreDict(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.PLUTONIUM),
            getItem("ic2:nuclear"),
            6,
        )
    }

}