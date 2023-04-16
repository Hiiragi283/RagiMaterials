package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.recipe.FFRecipe
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import hiiragi283.ragi_materials.item.ItemFullBottle
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.recipe.FFRecipeRegistry
import hiiragi283.ragi_materials.recipe.LaboRecipeRegistry
import hiiragi283.ragi_materials.recipe.MillRecipeRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object CommonRegistryEvent {

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        val registry = event.registry
        //Blockの自動登録
        RagiInit.setBlocks.forEach {
            it.creativeTab = RagiInit.TabBlock
            registry.register(it)
            RagiMaterials.LOGGER.debug("The block ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        val registry = event.registry
        //Itemの自動登録
        RagiInit.setItemBlocks.forEach {
            registry.register(it)
            RagiMaterials.LOGGER.debug("The item block ${it.registryName} is registered!")
        }
        RagiInit.setItems.forEach {
            if (it is ItemFullBottle) it.setCreativeTab(RagiInit.TabFullBottle)
            if (it is ItemMaterial) it.setCreativeTab(RagiInit.TabMaterial)
            registry.register(it)
            RagiMaterials.LOGGER.debug("The item ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerFFRecipe(event: RegistryEvent.Register<FFRecipe>) {
        val registry = event.registry
        //Forge Furnaceレシピの自動登録
        FFRecipeRegistry.set.forEach {
            registry.register(it)
            RagiMaterials.LOGGER.debug("The FF recipe ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerLaboRecipe(event: RegistryEvent.Register<LaboRecipe>) {
        val registry = event.registry
        //Laboratory Tableレシピの自動登録
        LaboRecipeRegistry.set.forEach {
            registry.register(it)
            RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerMillRecipe(event: RegistryEvent.Register<MillRecipe>) {
        val registry = event.registry
        //Stone Millレシピの自動登録
        MillRecipeRegistry.set.forEach {
            registry.register(it)
            RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
        }
    }
}