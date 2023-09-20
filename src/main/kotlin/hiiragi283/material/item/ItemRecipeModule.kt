package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.module.IRecipeModuleItem
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

abstract class ItemRecipeModule(override val recipeType: IMachineRecipe.Type) : HiiragiItem(
    "recipe_module_${recipeType.lowercase()}"
), IRecipeModuleItem {

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