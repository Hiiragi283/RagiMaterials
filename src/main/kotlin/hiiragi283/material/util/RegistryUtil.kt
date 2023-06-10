@file:JvmName("RegistryUtil")

package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.init.PotionTypes
import net.minecraft.init.SoundEvents
import net.minecraft.item.Item
import net.minecraft.potion.Potion
import net.minecraft.potion.PotionType
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries

fun getBlock(location: ResourceLocation): Block {
    return ForgeRegistries.BLOCKS.getValue(location) ?: Blocks.AIR
}

fun getBlock(location: String) = getBlock(ResourceLocation(location))

fun getEnchantment(location: ResourceLocation): Enchantment {
    return ForgeRegistries.ENCHANTMENTS.getValue(location) ?: Enchantment.getEnchantmentByID(0)!!
}

fun getEnchantment(location: String) = getEnchantment(ResourceLocation(location))

fun getItem(location: ResourceLocation): Item {
    return ForgeRegistries.ITEMS.getValue(location) ?: Items.AIR
}

fun getItem(location: String) = getItem(ResourceLocation(location))

fun getPotion(location: ResourceLocation): Potion {
    return ForgeRegistries.POTIONS.getValue(location) ?: Potion.getPotionById(1)!!
}

fun getPotion(location: String) = getPotion(ResourceLocation(location))

fun getPotionType(location: ResourceLocation): PotionType {
    return ForgeRegistries.POTION_TYPES.getValue(location) ?: PotionTypes.EMPTY
}

fun getPotionType(location: String) = getPotionType(ResourceLocation(location))

fun getSound(location: ResourceLocation): SoundEvent {
    return ForgeRegistries.SOUND_EVENTS.getValue(location) ?: SoundEvents.AMBIENT_CAVE
}

fun getSound(location: String) = getSound(ResourceLocation(location))