@file:JvmName("EnchantmentUtil")

package hiiragi283.material.util

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack

fun addEnchantment(enchantment: Enchantment, level: Int, stack: ItemStack) {
    val map = EnchantmentHelper.get(stack)
    map[enchantment] = level
    EnchantmentHelper.set(map, stack)
}

fun addEnchantments(vararg pairs: Pair<Enchantment, Int>, stack: ItemStack) {
    val map = EnchantmentHelper.get(stack)
    for (pair in pairs) {
        map[pair.first] = pair.second
    }
    EnchantmentHelper.set(map, stack)
}

fun hasEnchantment(enchantment: Enchantment, stack: ItemStack) = EnchantmentHelper.getLevel(enchantment, stack) > 0