@file:JvmName("CraftingUtil")

package ragi_materials.core.util

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.Ingredient
import net.minecraft.item.crafting.ShapedRecipes
import net.minecraft.item.crafting.ShapelessRecipes
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import ragi_materials.core.RagiMaterials

//定型クラフトレシピを追加するメソッド
fun addCraftingShaped(output: ItemStack, vararg inputs: Any) {
    GameRegistry.addShapedRecipe(output.toLocation(), output.toLocation(), output, *inputs)
    RagiMaterials.LOGGER.debug("The recipe ${output.toLocation()} is registered!")
}

fun addCraftingShaped(alt: Int, output: ItemStack, vararg inputs: Any) {
    val location = ResourceLocation("${output.toLocation()}_$alt")
    GameRegistry.addShapedRecipe(location, location, output, *inputs)
    RagiMaterials.LOGGER.debug("The recipe $location is registered!")
}

fun addCraftingShaped(registryName: String, output: ItemStack, vararg inputs: Any) {
    GameRegistry.addShapedRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
    RagiMaterials.LOGGER.debug("The recipe $registryName is registered!")
}

fun addCraftingShaped(output: ItemStack, inputs: NonNullList<Ingredient>, width: Int, height: Int, registryName: ResourceLocation = output.toLocation()) {
    ForgeRegistries.RECIPES.register(ShapedRecipes(registryName.toString(), width, height, inputs, output).setRegistryName(registryName))
}

fun addCraftingShaped(output: ItemStack, inputs: NonNullList<Ingredient>, width: Int, height: Int, registryName: String = output.toLocation().toString()) {
    ForgeRegistries.RECIPES.register(ShapedRecipes(registryName, width, height, inputs, output).setRegistryName(registryName))
}

//不定型クラフトレシピを追加するメソッド
fun addCraftingShapeless(output: ItemStack, vararg inputs: Ingredient) {
    GameRegistry.addShapelessRecipe(output.toLocation(), output.toLocation(), output, *inputs)
    RagiMaterials.LOGGER.debug("The recipe ${output.toLocation()} is registered!")
}

fun addCraftingShapeless(alt: Int, output: ItemStack, vararg inputs: Ingredient) {
    val location = ResourceLocation("${output.toLocation()}_$alt")
    GameRegistry.addShapelessRecipe(location, location, output, *inputs)
    RagiMaterials.LOGGER.debug("The recipe $location is registered!")
}

fun addCraftingShapeless(registryName: String, output: ItemStack, vararg inputs: Ingredient) {
    GameRegistry.addShapelessRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
    RagiMaterials.LOGGER.debug("The recipe $registryName is registered!")
}

fun addCraftingShapeless(output: ItemStack, inputs: NonNullList<Ingredient>, registryName: ResourceLocation = output.toLocation()) {
    ForgeRegistries.RECIPES.register(ShapelessRecipes(registryName.toString(), output, inputs).setRegistryName(registryName))
}

fun addCraftingShapeless(output: ItemStack, inputs: NonNullList<Ingredient>, registryName: String = output.toLocation().toString()) {
    ForgeRegistries.RECIPES.register(ShapelessRecipes(registryName, output, inputs).setRegistryName(registryName))
}

//クラフトレシピを削除するメソッド
fun removeCrafting(registryName: ResourceLocation) {
    CraftingManager.getRecipe(registryName)?.let {
        removeRegistryEntry(ForgeRegistries.RECIPES, registryName)
    } ?: RagiMaterials.LOGGER.debug("The recipe $registryName was not found...")
}

fun removeCrafting(registryName: String) {
    removeCrafting(ResourceLocation((registryName)))
}