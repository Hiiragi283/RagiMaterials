package hiiragi283.ragi_materials.crafting

import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object CraftingManager {

    //不定形レシピに鉱石辞書をねじ込むメソッド
    fun setOreDict(oreDict: String): Ingredient {
        val stacks = OreDictionary.getOres(oreDict)
        val listIngredients = ArrayList<Ingredient>()
        stacks.forEach { listIngredients.add(Ingredient.fromStacks(it)) }
        return Ingredient.merge(listIngredients)
    }

    //定型クラフトレシピを追加するメソッド
    fun addShaped(output: ItemStack, vararg inputs: Any) {
        val location = ResourceLocation(output.item.registryName!!.toString() + "_" + output.metadata)
        GameRegistry.addShapedRecipe(location, location, output, *inputs)
    }

    //定型クラフトレシピを名前付きで追加するメソッド
    fun addShaped(registryName: String, output: ItemStack, vararg inputs: Any) {
        GameRegistry.addShapedRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
    }

    //不定型クラフトレシピを追加するメソッド
    fun addShapeless(output: ItemStack, vararg inputs: Ingredient) {
        val location = ResourceLocation(output.item.registryName!!.toString() + "_" + output.metadata)
        GameRegistry.addShapelessRecipe(location, location, output, *inputs)
    }

    //不定型クラフトレシピを名前付きで追加するメソッド
    fun addShapeless(registryName: String, output: ItemStack, vararg inputs: Ingredient) {
        GameRegistry.addShapelessRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
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