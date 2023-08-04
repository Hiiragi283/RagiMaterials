package hiiragi283.api.event

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.shape.HiiragiShape
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Cancelable
import net.minecraftforge.fml.common.eventhandler.Event

@Cancelable
class MaterialRegistryEvent(val registry: MutableList<HiiragiMaterial>) : Event()

@Cancelable
class ShapeRegistryEvent(val registry: MutableList<HiiragiShape>) : Event()

object RMEventFactory {

    @JvmStatic
    fun registerMaterial(registry: MutableList<HiiragiMaterial>): MaterialRegistryEvent {
        val event = MaterialRegistryEvent(registry)
        MinecraftForge.EVENT_BUS.post(event)
        return event
    }

    @JvmStatic
    fun registerShape(registry: MutableList<HiiragiShape>): ShapeRegistryEvent {
        val event = ShapeRegistryEvent(registry)
        MinecraftForge.EVENT_BUS.post(event)
        return event
    }

}