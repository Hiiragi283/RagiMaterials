package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.getItem
import hiiragi283.material.util.registerOreDict
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiEnderIOPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.ELECTRICAL_STEEL.register()
        MaterialCompat.ENERGETIC_ALLOY.register()
        MaterialCompat.VIBRANT_ALLOY.register()
        MaterialCompat.REDSTONE_ALLOY.register()
        MaterialCompat.CONDUCTIVE_IRON.register()
        MaterialCompat.PULSATING_IRON.register()
        MaterialCompat.DARK_STEEL.register()
        MaterialCompat.SOULARIUM.register()
        MaterialCompat.END_STEEL.register()
        MaterialCompat.IRON_ALLOY.register()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompat.SIGNALUM),
            getItem("enderio:item_material"),
            57
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompat.ENDERIUM),
            getItem("enderio:item_material"),
            58
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompat.LUMIUM),
            getItem("enderio:item_material"),
            59
        )
    }

}