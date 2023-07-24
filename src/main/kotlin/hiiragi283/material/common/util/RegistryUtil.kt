@file:JvmName("RegistryUtil")

package hiiragi283.material.common.util

import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun getBlock(identifier: Identifier) = Registry.BLOCK.get(identifier)

fun getItem(identifier: Identifier) = Registry.ITEM.get(identifier)

fun <T : Any> getRegistryEntries(registry: Registry<T>): List<T> = registry.entrySet.map { it.value }

fun BlockState.toItemStack(count: Int = 1): ItemStack = ItemStack(this.block, count)