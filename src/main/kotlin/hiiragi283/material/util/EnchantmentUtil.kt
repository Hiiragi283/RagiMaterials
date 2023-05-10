@file:JvmName("EnchantmentUtil")

package hiiragi283.material.util

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack

fun addEnchantment(enchantment: Enchantment, level: Int, stack: ItemStack) {
    val map = EnchantmentHelper.getEnchantments(stack)
    map[enchantment] = level
    EnchantmentHelper.setEnchantments(map, stack)
}

fun addEnchantments(vararg pairs: Pair<Enchantment, Int>, stack: ItemStack) {
    val map = EnchantmentHelper.getEnchantments(stack)
    for (pair in pairs) {
        map[pair.first] = pair.second
    }
    EnchantmentHelper.setEnchantments(map, stack)
}

fun hasEnchantment(enchantment: Enchantment, stack: ItemStack) =
    EnchantmentHelper.getEnchantmentLevel(enchantment, stack) > 0