package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.recipe.FFRecipe
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import hiiragi283.ragi_materials.api.registry.RagiRegistries
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.RegistryBuilder

object NewRegistryEvent {

    /**
     * Thanks to SleepyTrousers!
     * Source: https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-base/src/main/java/crazypants/enderio/base/init/ModObjectRegistry.java
     */

    @SubscribeEvent
    fun createRegistry(event: RegistryEvent.NewRegistry) {
        //独自レジストリの作成
        //FFRecipe
        RagiRegistries.FF_RECIPE = RegistryBuilder<FFRecipe>().setName(ResourceLocation(RagiMaterials.MOD_ID, "ff_recipe")).setType(FFRecipe::class.java).create()
        RagiMaterials.LOGGER.debug("The new registry FF_RECIPE is registered!")
        //LaboRecipe
        RagiRegistries.LABO_RECIPE = RegistryBuilder<LaboRecipe>().setName(ResourceLocation(RagiMaterials.MOD_ID, "labo_recipe")).setType(LaboRecipe::class.java).create()
        RagiMaterials.LOGGER.debug("The new registry LABO_RECIPE is registered!")
        //MillRecipe
        RagiRegistries.MILL_RECIPE = RegistryBuilder<MillRecipe>().setName(ResourceLocation(RagiMaterials.MOD_ID, "mill_recipe")).setType(MillRecipe::class.java).create()
        RagiMaterials.LOGGER.debug("The new registry MILL_RECIPE is registered!")
    }
}