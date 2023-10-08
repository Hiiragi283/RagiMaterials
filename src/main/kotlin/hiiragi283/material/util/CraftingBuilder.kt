package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.fml.common.registry.GameRegistry

class CraftingBuilder(private val location: ResourceLocation, private val output: ItemStack) {

    constructor(location: String, output: ItemStack) : this(ResourceLocation(location), output)

    constructor(output: ItemStack, alt: String = "") : this(output.toLocation("_").append(alt), output)

    fun build() {
        when {
            params.isNotEmpty() -> GameRegistry.addShapedRecipe(location, null, output, *params.toTypedArray())
            ingredients.isNotEmpty() -> GameRegistry.addShapelessRecipe(location, null, output, *ingredients.toTypedArray())
            else -> throw IllegalStateException("Either shaped parameters or shapeless parameters should be empty!")
        }
    }

    //    Shaped    //

    private val params: MutableList<Any> = mutableListOf()

    fun setPattern(vararg pattern: String) = also { params.addAll(pattern) }

    fun setIngredient(mark: Char, input: ItemStack) = also {
        params.add(mark)
        params.add(input)
    }

    fun setIngredient(mark: Char, input: Block, isWild: Boolean = false) =
        setIngredient(mark, ItemStack(input, 1, if (isWild) Short.MAX_VALUE.toInt() else 0))

    fun setIngredient(mark: Char, input: Item, isWild: Boolean = false) =
        setIngredient(mark, ItemStack(input, 1, if (isWild) Short.MAX_VALUE.toInt() else 0))

    fun setIngredient(mark: Char, oredict: String) = also {
        params.add(mark)
        params.add(oredict)
    }

    //    Shapeless    //

    private val ingredients: MutableList<Ingredient> = mutableListOf()

    fun addIngredient(obj: Any) = also {
        ingredients.add(CraftingHelper.getIngredient(obj))
    }

}