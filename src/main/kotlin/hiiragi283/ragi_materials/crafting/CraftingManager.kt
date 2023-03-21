package hiiragi283.ragi_materials.crafting

import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object CraftingManager {
    //不定形レシピに鉱石辞書をねじ込むメソッド
    fun setOreDict(oreDict: String): Ingredient {
        //鉱石辞書からItemStackのリストを取得
        val stacks = OreDictionary.getOres(oreDict)
        //結合用のリストを宣言
        val listIngredients: MutableCollection<Ingredient> = ArrayList()
        //listIngredientsにItemStackを足していく
        stacks.forEach {listIngredients.add(Ingredient.fromStacks(it))}
        //listIngredientを1つのIngredientにまとめる
        return Ingredient.merge(listIngredients)
    }

    //定型クラフトレシピを追加するメソッド
    fun addShaped(output: ItemStack, vararg inputs: Any) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(output.item.registryName!!.toString() + "_" + output.metadata)
        //レシピを追加する
        GameRegistry.addShapedRecipe(location, location, output, *inputs)
        //RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    //定型クラフトレシピを名前付きで追加するメソッド
    fun addShaped(registryName: String, output: ItemStack, vararg inputs: Any) {
        //レシピを上書きする
        GameRegistry.addShapedRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
        //RagiLogger.infoDebug("The recipe <recipe:$registryName> was added successfully!")
    }

    //不定型クラフトレシピを追加するメソッド
    fun addShapeless(output: ItemStack, vararg inputs: Ingredient) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(output.item.registryName!!.toString() + "_" + output.metadata)
        //レシピを追加する
        GameRegistry.addShapelessRecipe(location, location, output, *inputs)
        //RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    //不定型クラフトレシピを名前付きで追加するメソッド
    fun addShapeless(registryName: String, output: ItemStack, vararg inputs: Ingredient) {
        //レシピを上書きする
        GameRegistry.addShapelessRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output, *inputs)
        //RagiLogger.infoDebug("The recipe <recipe:$registryName> was added successfully!")
    }

    //クラフトレシピを削除するメソッド
    fun removeCrafting(registryName: String) {
        //registryNameからResource Locationを生成
        val location = ResourceLocation(registryName)
        //locationからレシピを取得
        val recipeBefore = CraftingManager.getRecipe(location)
        //取得したレシピがnullでない場合
        if (recipeBefore !== null) {
            //レシピを置き換える
            GameRegistry.addShapedRecipe(location, location, recipeBefore.recipeOutput, "A", 'A', RagiUtil.getStack("minecraft:barrier", 1, 0))
            //RagiLogger.infoDebug("The recipe <recipe:$registryName> was removed successfully!")
        } //else RagiLogger.warnDebug("The recipe <recipe:$registryName> was not found...")
    }
}