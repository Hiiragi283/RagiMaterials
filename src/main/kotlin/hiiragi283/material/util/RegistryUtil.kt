@file:JvmName("RegistryUtil")

package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.potion.Potion
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun getBlock(id: Identifier): Block = Registry.BLOCK.get(id)
fun getEnchant(id: Identifier): Enchantment = Registry.ENCHANTMENT.get(id) ?: Enchantments.FORTUNE
fun getEntity(id: Identifier): EntityType<*> = Registry.ENTITY_TYPE.get(id)
fun getFluid(id: Identifier): Fluid = Registry.FLUID.get(id)
fun getItem(id: Identifier): Item = Registry.ITEM.get(id)
fun getPotion(id: Identifier): Potion = Registry.POTION.get(id)
fun getSound(id: Identifier): SoundEvent = Registry.SOUND_EVENT.get(id) ?: SoundEvents.ENTITY_ITEM_PICKUP