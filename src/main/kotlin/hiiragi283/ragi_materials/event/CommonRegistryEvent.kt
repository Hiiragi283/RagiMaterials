package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.item.ItemFullBottle
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object CommonRegistryEvent {

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        //Blockの自動登録
        RagiRegistry.setBlocks.forEach {
            it.creativeTab = RagiRegistry.TabBlock
            event.registry.register(it)
            RagiLogger.infoDebug("The block ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        //Itemの自動登録
        RagiRegistry.setItems.forEach {
            if (it is ItemFullBottle) it.setCreativeTab(RagiRegistry.TabFullBottle)
            if (it is ItemMaterial) it.setCreativeTab(RagiRegistry.TabMaterial)
            event.registry.register(it)
            RagiLogger.infoDebug("The item ${it.registryName} is registered!")
        }
    }
}