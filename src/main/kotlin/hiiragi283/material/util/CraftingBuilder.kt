package hiiragi283.material.util

import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

class CraftingBuilder(private val location: ResourceLocation, private val output: ItemStack) {

    constructor(location: String, output: ItemStack) : this(ResourceLocation(location), output)

    constructor(output: ItemStack) : this(output.toLocation(), output)

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

    fun build() {
        GameRegistry.addShapedRecipe(location, location, output, *params.toTypedArray())
    }

}