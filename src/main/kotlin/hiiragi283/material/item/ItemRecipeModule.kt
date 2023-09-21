package hiiragi283.material.item

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.module.IRecipeModuleItem
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

sealed class ItemRecipeModule(final override val recipeType: IMachineRecipe.Type) : HiiragiItem(
    "recipe_module_${recipeType.lowercase()}"
), IRecipeModuleItem {

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
        HiiragiRegistries.RECIPE_MODULE.register(recipeType, this)
    }

    //    Extended Classes    //

    object Extractor : ItemRecipeModule(IMachineRecipe.Type.EXTRACTOR)

    object Infuser : ItemRecipeModule(IMachineRecipe.Type.INFUSER)

    object Smelter : ItemRecipeModule(IMachineRecipe.Type.SMELTER) {

        override fun registerRecipe() {
            CraftingBuilder(ItemStack(this))
                .setPattern("ABA", "BCB", "ABA")
                .setIngredient('A', "stone")
                .setIngredient('B', HiiragiShapes.INGOT.getOreDict(MaterialElements.IRON))
                .setIngredient('C', ItemStack(Blocks.FURNACE))
                .build()
        }

    }

}