package ragi_materials.core.event

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.item.ItemMaterial
import ragi_materials.main.item.ItemFullBottle

object CommonRegistryEvent {

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        val registry = event.registry
        //Blockの自動登録
        RagiRegistry.setBlocks.forEach {
            if (RagiRegistry.availableTabBlock()) it.creativeTab = RagiRegistry.TabBlock
            registry.register(it)
            RagiMaterials.LOGGER.debug("The block ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        val registry = event.registry
        //Itemの自動登録
        RagiRegistry.setItemBlocks.forEach {
            registry.register(it)
            RagiMaterials.LOGGER.debug("The item block ${it.registryName} is registered!")
        }
        RagiRegistry.setItems.forEach {
            if (it is ItemFullBottle && RagiRegistry.availableTabFullBottle()) it.setCreativeTab(RagiRegistry.TabFullBottle)
            if (it is ItemMaterial && RagiRegistry.availableTabMaterial()) it.setCreativeTab(RagiRegistry.TabMaterial)
            registry.register(it)
            RagiMaterials.LOGGER.debug("The item ${it.registryName} is registered!")
        }
    }
}