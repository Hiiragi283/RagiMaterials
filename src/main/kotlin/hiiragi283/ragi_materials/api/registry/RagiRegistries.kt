package hiiragi283.ragi_materials.api.registry

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.recipe.FFRecipe
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable

object RagiRegistries {

    lateinit var FF_RECIPE: IForgeRegistry<FFRecipe>
    lateinit var LABO_RECIPE: IForgeRegistry<LaboRecipe>
    lateinit var MILL_RECIPE: IForgeRegistry<MillRecipe>

    //エントリーを削除するメソッド
    fun remove(registry: IForgeRegistry<*>, registryName: ResourceLocation) {
        if (registry is IForgeRegistryModifiable<*>) {
            registry.remove(registryName)
            RagiMaterials.LOGGER.warn("The entry $registryName was removed from ${registry::class.java.name}!")
        } else RagiMaterials.LOGGER.warn("The registry ${registry::class.java.name} is not implementing IForgeRegistryModifiable!")
    }

    fun remove(registry: IForgeRegistry<*>, registryName: String) {
        remove(registry, ResourceLocation(registryName))
    }

}