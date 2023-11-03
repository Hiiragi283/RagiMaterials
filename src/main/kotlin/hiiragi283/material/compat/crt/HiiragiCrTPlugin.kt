package hiiragi283.material.compat.crt

import crafttweaker.CraftTweakerAPI
import hiiragi283.material.RMReference
import hiiragi283.material.compat.HiiragiPluginBase

object HiiragiCrTPlugin : HiiragiPluginBase("crafttweaker", "Crafttweaker", { true }) {

    fun registerMaterial() {
        CraftTweakerAPI.tweaker.loadScript(false, RMReference.MOD_ID)
    }

}