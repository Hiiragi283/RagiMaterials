package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.RagiMaterials
import hiiragi283.core.util.OreDictUtil
import hiiragi283.core.util.getItem

object EnderIOIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: Ender IO")
        registry.add(MaterialIntegration.ELECTRICAL_STEEL)
        registry.add(MaterialIntegration.ENERGETIC_ALLOY)
        registry.add(MaterialIntegration.VIBRANT_ALLOY)
        registry.add(MaterialIntegration.REDSTONE_ALLOY)
        registry.add(MaterialIntegration.CONDUCTIVE_IRON)
        registry.add(MaterialIntegration.PULSATING_IRON)
        registry.add(MaterialIntegration.DARK_STEEL)
        registry.add(MaterialIntegration.SOULARIUM)
        registry.add(MaterialIntegration.END_STEEL)
        registry.add(MaterialIntegration.IRON_ALLOY)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
        OreDictUtil.register(
            HiiragiShapes.BALL.getOreDict(MaterialIntegration.SIGNALUM),
            getItem("enderio:item_material"),
            57
        )
        OreDictUtil.register(
            HiiragiShapes.BALL.getOreDict(MaterialIntegration.LUMIUM),
            getItem("enderio:item_material"),
            58
        )
        OreDictUtil.register(
            HiiragiShapes.BALL.getOreDict(MaterialIntegration.ENDERIUM),
            getItem("enderio:item_material"),
            59
        )
    }

    override fun onComplete() {
    }

}