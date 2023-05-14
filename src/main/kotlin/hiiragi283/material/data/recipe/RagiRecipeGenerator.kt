package hiiragi283.material.data.recipe

import hiiragi283.material.RagiRegistry
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.FinishedRecipe
import java.util.function.Consumer

class RagiRecipeGenerator(generator: FabricDataGenerator) : FabricRecipeProvider(generator) {

    override fun generateRecipes(exporter: Consumer<FinishedRecipe>) {
        for (item in RagiRegistry.setItems) {
            item.registerRecipe()
        }
    }
}