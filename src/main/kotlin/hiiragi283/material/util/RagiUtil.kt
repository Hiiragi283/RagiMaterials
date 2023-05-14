@file:JvmName("RagiUtil")

package hiiragi283.material.util

import net.minecraft.world.item.Rarity

fun Boolean.toInt(): Int = if (this) 1 else 0

fun Int.toBool(): Boolean = this % 2 != 0

fun getEnumRarity(name: String): Rarity {
    return when (name) {
        "Uncommon" -> Rarity.UNCOMMON
        "Rare" -> Rarity.RARE
        "Epic" -> Rarity.EPIC
        else -> Rarity.COMMON
    }
}