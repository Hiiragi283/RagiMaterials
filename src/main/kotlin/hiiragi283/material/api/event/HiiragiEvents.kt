package hiiragi283.material.api.event

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraftforge.fml.common.eventhandler.Event

//    Material    //

class MaterialRegistryEvent : Event()

class MaterialBuildEvent(val builder: HiiragiMaterial.Builder) : Event()

//    Shape    //

class ShapeBuildEvent(val builder: HiiragiShape.Builder) : Event()