package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.CraftTweakerAPI
import crafttweaker.IAction

object CraftTweakerCore {

    val listAdd: MutableList<IAction> = mutableListOf()
    val listRemove: MutableList<IAction> = mutableListOf()

    fun register() {
        CraftTweakerAPI.registerBracketHandler(BracketHandlerElement())
        CraftTweakerAPI.registerBracketHandler(BracketHandlerMaterial())
    }

    fun apply() {
        listAdd.forEach { CraftTweakerAPI.apply(it) }
        listRemove.forEach { CraftTweakerAPI.apply(it) }
    }

}