@file:JvmName("RegistryUtil")

package hiiragi283.material.util

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun getItem(identifier: Identifier) = Registry.ITEM.get(identifier)