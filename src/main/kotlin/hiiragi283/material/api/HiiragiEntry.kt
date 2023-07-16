package hiiragi283.material.api

import net.minecraft.util.Identifier

interface HiiragiEntry {

    val identifier: Identifier

    fun register()

}