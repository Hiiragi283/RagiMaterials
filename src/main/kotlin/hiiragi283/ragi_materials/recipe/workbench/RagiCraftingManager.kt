package hiiragi283.ragi_materials.recipe.workbench

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.registry.RagiRegistries
import hiiragi283.ragi_materials.util.toLocation1
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.Ingredient
import net.minecraft.item.crafting.ShapedRecipes
import net.minecraft.item.crafting.ShapelessRecipes
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry

object RagiCraftingManager {

    //定型クラフトレシピを追加するメソッド
    fun addShaped(output: ItemStack, vararg inputs: Any) {
        GameRegistry.addShapedRecipe(output.toLocation1(), output.toLocation1(), output, *inputs)
        RagiMaterials.LOGGER.debug("The recipe ${output.toLocation1()} is registered!")
    }

    fun addShaped(registryName: String, output: ItemStack, vararg inputs: Any) {
        GameRegistry.addShapedRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
        RagiMaterials.LOGGER.debug("The recipe $registryName is registered!")
    }

    fun addShaped(output: ItemStack, inputs: NonNullList<Ingredient>, width: Int, height: Int, registryName: ResourceLocation = output.toLocation1()) {
        ForgeRegistries.RECIPES.register(ShapedRecipes(registryName.toString(), width, height, inputs, output).setRegistryName(registryName))
    }

    fun addShaped(output: ItemStack, inputs: NonNullList<Ingredient>, width: Int, height: Int, registryName: String = output.toLocation1().toString()) {
        ForgeRegistries.RECIPES.register(ShapedRecipes(registryName, width, height, inputs, output).setRegistryName(registryName))
    }

    //不定型クラフトレシピを追加するメソッド
    fun addShapeless(output: ItemStack, vararg inputs: Ingredient) {
        GameRegistry.addShapelessRecipe(output.toLocation1(), output.toLocation1(), output, *inputs)
        RagiMaterials.LOGGER.debug("The recipe ${output.toLocation1()} is registered!")
    }

    fun addShapeless(registryName: String, output: ItemStack, vararg inputs: Ingredient) {
        GameRegistry.addShapelessRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
        RagiMaterials.LOGGER.debug("The recipe $registryName is registered!")
    }

    fun addShapeless(output: ItemStack, inputs: NonNullList<Ingredient>, registryName: ResourceLocation = output.toLocation1()) {
        ForgeRegistries.RECIPES.register(ShapelessRecipes(registryName.toString(), output, inputs).setRegistryName(registryName))
    }

    fun addShapeless(output: ItemStack, inputs: NonNullList<Ingredient>, registryName: String = output.toLocation1().toString()) {
        ForgeRegistries.RECIPES.register(ShapelessRecipes(registryName, output, inputs).setRegistryName(registryName))
    }

    //クラフトレシピを削除するメソッド
    fun remove(registryName: ResourceLocation) {
        CraftingManager.getRecipe(registryName)?.let {
            RagiRegistries.remove(ForgeRegistries.RECIPES, registryName)
        } ?: RagiMaterials.LOGGER.debug("The recipe $registryName was not found...")
    }

    fun remove(registryName: String) {
        remove(ResourceLocation((registryName)))
    }
}