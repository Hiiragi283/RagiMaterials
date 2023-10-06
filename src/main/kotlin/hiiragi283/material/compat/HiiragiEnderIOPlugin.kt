package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiEnderIOPlugin : HiiragiPluginBase("enderio", "Ender IO", { HiiragiConfigs.INTEGRATION.enderIO }) {

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
            getEntry<Item>(getResourceLocation("item_material")),
            57
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompat.ENDERIUM),
            getEntry<Item>(getResourceLocation("item_material")),
            58
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompat.LUMIUM),
            getEntry<Item>(getResourceLocation("item_material")),
            59
        )
    }

}