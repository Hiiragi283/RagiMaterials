package hiiragi283.core.util

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

//クラフトレシピを削除するメソッド

class CraftingBuilder(private val location: ResourceLocation, private val output: ItemStack) {

    constructor(location: String, output: ItemStack) : this(ResourceLocation(location), output)

    constructor(output: ItemStack) : this(output.toLocation("_").append("_" + output.metadata), output)

    //    Shaped    //

    private val params: MutableList<Any> = mutableListOf()

    fun setPattern(vararg pattern: String) = also { params.addAll(pattern) }

    fun setIngredient(mark: Char, input: ItemStack) = also {
        params.add(mark)
        params.add(input)
    }

    fun setIngredient(mark: Char, oredict: String) = also {
        params.add(mark)
        params.add(oredict)
    }

    fun buildShaped() {
        GameRegistry.addShapedRecipe(location, null, output, *params.toTypedArray())
    }

    //    Shapeless    //

    private val ingredients: MutableList<Ingredient> = mutableListOf()

    fun addIngredient(vararg ings: Ingredient) = also {
        ingredients.addAll(ings)
    }

    fun buildShapeless() {
        GameRegistry.addShapelessRecipe(location, null, output, *ingredients.toTypedArray())
    }

}