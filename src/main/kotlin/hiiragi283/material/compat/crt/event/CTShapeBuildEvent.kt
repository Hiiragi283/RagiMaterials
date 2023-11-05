package hiiragi283.material.compat.crt.event

import crafttweaker.annotations.ZenRegister
import hiiragi283.material.RMReference
import hiiragi283.material.api.event.ShapeBuildEvent
import hiiragi283.material.api.shape.HiiragiShape
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenGetter

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.event.ShapeBuildEvent")
@ZenRegister
class CTShapeBuildEvent(val event: ShapeBuildEvent) {

    @ZenGetter("builder")
    fun getBuilder(): HiiragiShape.Builder = event.builder

}