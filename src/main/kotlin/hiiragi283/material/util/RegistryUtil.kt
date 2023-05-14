@file:JvmName("RegistryUtil")

package hiiragi283.material.util

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid

fun getBlock(location: ResourceLocation): Block = Registry.BLOCK.get(location)
fun getEnchant(location: ResourceLocation): Enchantment = Registry.ENCHANTMENT.get(location) ?: Enchantments.ALL_DAMAGE_PROTECTION
fun getEntity(location: ResourceLocation): EntityType<*> = Registry.ENTITY_TYPE.get(location)
fun getFluid(location: ResourceLocation): Fluid = Registry.FLUID.get(location)
fun getItem(location: ResourceLocation): Item = Registry.ITEM.get(location)
fun getPotion(location: ResourceLocation): Potion = Registry.POTION.get(location)
fun getSound(location: ResourceLocation): SoundEvent = Registry.SOUND_EVENT.get(location) ?: SoundEvents.AMBIENT_CAVE