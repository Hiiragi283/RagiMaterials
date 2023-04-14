package hiiragi283.ragi_materials.recipe.workbench

import hiiragi283.ragi_materials.crafting.RecipeEmpty
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.toLocation
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.*
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry

object RagiCraftingManager {

    val list: MutableSet<IRecipe> = mutableSetOf()

    //定型クラフトレシピを追加するメソッド
    fun addShaped(output: ItemStack, vararg inputs: Any) {
        GameRegistry.addShapedRecipe(output.toLocation(), output.toLocation(), output, *inputs)
    }

    //定型クラフトレシピを名前付きで追加するメソッド
    fun addShaped(registryName: String, output: ItemStack, vararg inputs: Any) {
        GameRegistry.addShapedRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
    }

    fun addShaped(output: ItemStack, inputs: NonNullList<Ingredient>, width: Int, height: Int, registryName: ResourceLocation = output.toLocation()) {
        list.add(ShapedRecipes(registryName.toString(), width, height, inputs, output).setRegistryName(registryName))
    }

    //不定型クラフトレシピを追加するメソッド
    fun addShapeless(output: ItemStack, vararg inputs: Ingredient) {
        GameRegistry.addShapelessRecipe(output.toLocation(), output.toLocation(), output, *inputs)
    }

    //不定型クラフトレシピを名前付きで追加するメソッド
    fun addShapeless(registryName: String, output: ItemStack, vararg inputs: Ingredient) {
        GameRegistry.addShapelessRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
    }

    fun addShapeless(output: ItemStack, inputs: NonNullList<Ingredient>, registryName: ResourceLocation = output.toLocation()) {
        list.add(ShapelessRecipes(registryName.toString(), output, inputs).setRegistryName(registryName))
    }

    //クラフトレシピを削除するメソッド
    fun removeCrafting(registryName: String) {
        val location = ResourceLocation(registryName)
        val recipeBefore = CraftingManager.getRecipe(location)
        if (recipeBefore !== null) {
            ForgeRegistries.RECIPES.register(RecipeEmpty(location))
        } else RagiLogger.warnDebug("The recipe <recipe:$registryName> was not found...")
    }
}