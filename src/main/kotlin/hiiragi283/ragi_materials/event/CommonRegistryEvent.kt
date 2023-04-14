package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.item.ItemFullBottle
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.recipe.workbench.RagiCraftingManager
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.ForgeRegistry

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
    @SubscribeEvent
    fun registerRecipe(event: RegistryEvent.Register<IRecipe>) {
        val registry = event.registry
        val registryRecipe = registry as ForgeRegistry<IRecipe>
        //クラフトレシピの自動登録
        RagiCraftingManager.listAdd.forEach {
            registry.register(it)
        }

        /**
         * Thanks to Kotori!
         * Source: https://kotori316.blog.fc2.com/blog-entry-17.html
         */

        //クラフトレシピの削除
        RagiCraftingManager.listRemove.forEach { location ->
            //レシピが存在する場合のみ削除
            registry.getValue(location)?.let {
                registryRecipe.remove(location)
            }
        }
    }
}