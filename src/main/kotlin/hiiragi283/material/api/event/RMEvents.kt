package hiiragi283.material.api.event

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import net.minecraftforge.fml.common.eventhandler.Event

class MaterialRegistryEvent(val registry: HiiragiRegistry<String, HiiragiMaterial>) : Event()

class ShapeRegistryEvent(val registry: HiiragiRegistry<String, HiiragiShape>) : Event()

class ShapeTypeRegistryEvent(val registry: HiiragiRegistry<String, HiiragiShapeType>) : Event()