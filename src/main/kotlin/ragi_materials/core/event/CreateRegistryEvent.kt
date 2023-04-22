package ragi_materials.core.event

import ragi_materials.core.RagiMaterials
import ragi_materials.core.recipe.FFRecipe
import ragi_materials.core.recipe.LaboRecipe
import ragi_materials.core.recipe.MillRecipe
import ragi_materials.core.RagiRegistry
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import net.minecraftforge.registries.RegistryBuilder

/**
 * Thanks to SleepyTrousers!
 * Source: https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-base/src/main/java/crazypants/enderio/base/init/ModObjectRegistry.java
 */

object CreateRegistryEvent {

    @SubscribeEvent
    fun createRegistry(event: RegistryEvent.NewRegistry) {
        //独自レジストリの作成
        //FFRecipe
        RagiRegistry.FF_RECIPE = createRegistry(FFRecipe::class.java, "ff_recipe")
        RagiMaterials.LOGGER.debug("The new registry FF_RECIPE is registered!")
        //LaboRecipe
        RagiRegistry.LABO_RECIPE = createRegistry(LaboRecipe::class.java, "labo_recipe")
        RagiMaterials.LOGGER.debug("The new registry LABO_RECIPE is registered!")
        //MillRecipe
        RagiRegistry.MILL_RECIPE = createRegistry(MillRecipe::class.java, "mill_recipe")
        RagiMaterials.LOGGER.debug("The new registry MILL_RECIPE is registered!")
    }

    private fun <T : IForgeRegistryEntry.Impl<T>> createRegistry(clazz: Class<T>, path: String): IForgeRegistry<T> = RegistryBuilder<T>().setName(ResourceLocation(RagiMaterials.MOD_ID, path)).setType(clazz).allowModification().create()

}