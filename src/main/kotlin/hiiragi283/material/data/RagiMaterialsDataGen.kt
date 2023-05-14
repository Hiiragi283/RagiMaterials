package hiiragi283.material.data

import hiiragi283.material.data.recipe.RagiRecipeGenerator
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object RagiMaterialsDataGen : DataGeneratorEntrypoint {

    override fun onInitializeDataGenerator(DataGenerator: FabricDataGenerator) {

        //レシピの登録
        DataGenerator.addProvider(RagiRecipeGenerator(DataGenerator))
    }
}