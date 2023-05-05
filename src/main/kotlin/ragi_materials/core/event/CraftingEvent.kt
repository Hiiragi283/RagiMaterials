package ragi_materials.core.event

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent
import ragi_materials.core.enchant.EnchantmentMaterial
import ragi_materials.core.item.ItemMaterialMiner
import ragi_materials.core.util.addEnchantment

/**
 * Thanks to defeatedcrow!
 * Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/event/OnCraftingDC.java
 */

object CraftingEvent {

    @SubscribeEvent
    fun onCrafting(event: PlayerEvent.ItemCraftedEvent) {
        val output = event.crafting
        if (!output.isEmpty) {
            if (output.item is ItemMaterialMiner) {
                addEnchantment(EnchantmentMaterial, 1, output)
            }
        }
    }
}