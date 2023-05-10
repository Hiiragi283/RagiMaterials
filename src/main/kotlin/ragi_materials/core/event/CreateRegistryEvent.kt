package ragi_materials.core.event

import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import net.minecraftforge.registries.RegistryBuilder
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.recipe.*

/**
 * Thanks to SleepyTrousers!
 * Source: https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-base/src/main/java/crazypants/enderio/base/init/ModObjectRegistry.java
 */

object CreateRegistryEvent {

    @SubscribeEvent
    fun createRegistry(event: RegistryEvent.NewRegistry) {
        //独自レジストリの作成
        //BasinRecipe
        RagiRegistry.BASIN_RECIPE = createRegistry(BasinRecipe::class.java, "basin_recipe")
        RagiMaterials.LOGGER.debug("The new registry BASIN_RECIPE is registered!")
        //BlastFurnaceRecipe
        RagiRegistry.BF_RECIPE = createRegistry(BlastFurnaceRecipe::class.java, "bf_recipe")
        RagiMaterials.LOGGER.debug("The new registry BF_RECIPE is registered!")
        //ForgeFurnaceRecipe
        RagiRegistry.FF_RECIPE = createRegistry(ForgeFurnaceRecipe::class.java, "ff_recipe")
        RagiMaterials.LOGGER.debug("The new registry FF_RECIPE is registered!")
        //HopperPressRecipe
        RagiRegistry.HP_RECIPE = createRegistry(HopperPressRecipe::class.java, "hp_recipe")
        RagiMaterials.LOGGER.debug("The new registry HP_RECIPE is registered!")
        //LaboRecipe
        RagiRegistry.LABO_RECIPE = createRegistry(LaboRecipe::class.java, "labo_recipe")
        RagiMaterials.LOGGER.debug("The new registry LABO_RECIPE is registered!")
        //MillRecipe
        RagiRegistry.MILL_RECIPE = createRegistry(MillRecipe::class.java, "mill_recipe")
        RagiMaterials.LOGGER.debug("The new registry MILL_RECIPE is registered!")
    }

    private fun <T : IForgeRegistryEntry.Impl<T>> createRegistry(clazz: Class<T>, path: String): IForgeRegistry<T> = RegistryBuilder<T>().setName(ResourceLocation(RagiMaterials.MOD_ID, path)).setType(clazz).allowModification().create()

}