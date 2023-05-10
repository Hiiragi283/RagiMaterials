package ragi_materials.core.event

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.enchant.EnchantmentMaterial
import ragi_materials.core.item.ItemMaterial
import ragi_materials.main.item.ItemFullBottle

object CommonRegistryEvent {

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        val registry = event.registry
        //Blockの自動登録
        for (block in RagiRegistry.setBlocks) {
            if (RagiRegistry.availableTabBlock()) block.creativeTab = RagiRegistry.TabBlock
            registry.register(block)
            RagiMaterials.LOGGER.debug("The block ${block.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        val registry = event.registry
        //Itemの自動登録
        for (itemBlock in RagiRegistry.setItemBlocks) {
            registry.register(itemBlock)
            RagiMaterials.LOGGER.debug("The item block ${itemBlock.registryName} is registered!")

        }
        for (item in RagiRegistry.setItems) {
            when (item) {
                is ItemFullBottle -> item.setCreativeTab(RagiRegistry.TabFullBottle)
                is ItemMaterial -> item.setCreativeTab(RagiRegistry.TabMaterial)
                else -> item.setCreativeTab(CreativeTabs.MISC)
            }
            registry.register(item)
            RagiMaterials.LOGGER.debug("The item ${item.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerEnchantment(event: RegistryEvent.Register<Enchantment>) {
        val registry = event.registry
        registry.register(EnchantmentMaterial)
    }
}