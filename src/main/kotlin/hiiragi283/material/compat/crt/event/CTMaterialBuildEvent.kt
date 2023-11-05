package hiiragi283.material.compat.crt.event

import crafttweaker.annotations.ZenRegister
import hiiragi283.material.RMReference
import hiiragi283.material.api.event.MaterialBuildEvent
import hiiragi283.material.api.material.HiiragiMaterial
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenGetter

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.event.MaterialBuildEvent")
@ZenRegister
class CTMaterialBuildEvent(val event: MaterialBuildEvent) {

    @ZenGetter("builder")
    fun getBuilder(): HiiragiMaterial.Builder = event.builder

}