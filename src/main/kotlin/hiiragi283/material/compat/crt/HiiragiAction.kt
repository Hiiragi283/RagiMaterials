package hiiragi283.material.compat.crt

import crafttweaker.IAction

class HiiragiAction(private val description: String, private val action: Runnable) : IAction {

    override fun apply() = action.run()

    override fun describe(): String = description

}