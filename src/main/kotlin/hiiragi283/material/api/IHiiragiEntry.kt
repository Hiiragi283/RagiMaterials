package hiiragi283.material.api

import net.minecraft.util.Identifier

interface IHiiragiEntry {

    val identifier: Identifier

    fun register()

    fun registerModel() {}

    fun registerTag() {}

    fun registerRecipe() {}

}