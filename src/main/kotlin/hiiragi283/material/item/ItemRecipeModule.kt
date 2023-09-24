package hiiragi283.material.item

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.material.formulaOf
import hiiragi283.material.api.module.IRecipeModuleItem
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

private val RECIPE_MODULE = formulaOf("recipe_module")

sealed class ItemRecipeModule(final override val recipeType: IMachineRecipe.Type) : HiiragiItem(
    "recipe_module_${recipeType.lowercase()}"
), IRecipeModuleItem {

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
        HiiragiRegistries.RECIPE_MODULE.register(recipeType, this)
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = recipeType.getTranslatedName(RECIPE_MODULE)

    //    Extended Classes    //

    object Extractor : ItemRecipeModule(IMachineRecipe.Type.EXTRACTOR)

    object Freezer : ItemRecipeModule(IMachineRecipe.Type.FREEZER)

    object Infuser : ItemRecipeModule(IMachineRecipe.Type.INFUSER)

    object Melter : ItemRecipeModule(IMachineRecipe.Type.MELTER)

    object RockGenerator : ItemRecipeModule(IMachineRecipe.Type.ROCK_GENERATOR)

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