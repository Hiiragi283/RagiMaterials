package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.util.getEntry
import hiiragi283.material.util.registerOreDict
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.FMLInitializationEvent

object HiiragiEnderIOPlugin : HiiragiPluginBase("enderio", "Ender IO", { HiiragiConfigs.INTEGRATION.enderIO }) {

    override fun onInit(event: FMLInitializationEvent) {
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompats.SIGNALUM),
            getEntry<Item>(getResourceLocation("item_material")),
            57
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompats.ENDERIUM),
            getEntry<Item>(getResourceLocation("item_material")),
            58
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialCompats.LUMIUM),
            getEntry<Item>(getResourceLocation("item_material")),
            59
        )
    }

}