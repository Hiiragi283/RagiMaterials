package hiiragi283.material.api.event

import hiiragi283.material.api.material.HiiragiMaterial
import net.minecraftforge.fml.common.eventhandler.Event

class MaterialRegistryEvent : Event()

class MaterialBuiltEvent(val builder: HiiragiMaterial.Builder) : Event()

class ShapeRegistryEvent : Event()