package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialElements
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.util.OreDictUtil
import hiiragi283.core.util.getItem

object IC2exIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
    }

    override fun onInit() {
    }

    override fun onPostInit() {
        OreDictUtil.register(
            HiiragiShapes.INGOT.getOreDict(MaterialCommon.RUBBER),
            getItem("ic2:crafting"),
            0,
            "itemRubber"
        )
        OreDictUtil.register(
            HiiragiShapes.DUST.getOreDict(MaterialIntegration.ENDER_PEARL),
            getItem("ic2:dust"),
            31,
            "dustEnderPearl"
        )
        OreDictUtil.register(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.EMERALD),
            getItem("ic2:dust"),
            34
        )
        OreDictUtil.register(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialCommon.EMERALD),
            getItem("ic2:dust"),
            35
        )
        OreDictUtil.register(
            HiiragiShapes.DUST.getOreDict(MaterialCommon.ASH),
            getItem("ic2:misc_resource"),
            0,
        )
        OreDictUtil.register(
            HiiragiShapes.DUST_TINY.getOreDict(MaterialElements.IODINE),
            getItem("ic2:misc_resource"),
            6,
        )
        OreDictUtil.register(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM235),
            getItem("ic2:nuclear"),
            1,
        )
        OreDictUtil.register(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.URANIUM),
            getItem("ic2:nuclear"),
            2,
        )
        OreDictUtil.register(
            HiiragiShapes.INGOT.getOreDict(MaterialElements.PLUTONIUM),
            getItem("ic2:nuclear"),
            3,
        )
        OreDictUtil.register(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM235),
            getItem("ic2:nuclear"),
            5,
        )
        OreDictUtil.register(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.URANIUM),
            getItem("ic2:nuclear"),
            6,
        )
        OreDictUtil.register(
            HiiragiShapes.NUGGET.getOreDict(MaterialElements.PLUTONIUM),
            getItem("ic2:nuclear"),
            6,
        )
    }

    override fun onComplete() {
    }

}