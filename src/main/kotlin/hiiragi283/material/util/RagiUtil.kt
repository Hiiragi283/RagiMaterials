@file:JvmName("RagiUtil")

package hiiragi283.material.util

import net.minecraft.server.command.CommandOutput
import net.minecraft.util.Rarity

fun Boolean.toInt() = if (this) 1 else 0

fun Int.toBool() = this % 2 != 0

fun executeCommand(sender: CommandOutput, command: String) {}

fun getEnumRarity(name: String): Rarity {
    return when (name) {
        "Uncommon" -> Rarity.UNCOMMON
        "Rare" -> Rarity.RARE
        "Epic" -> Rarity.EPIC
        else -> Rarity.COMMON
    }
}