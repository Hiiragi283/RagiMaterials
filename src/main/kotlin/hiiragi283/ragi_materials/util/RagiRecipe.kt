package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.util.RagiUtils.toBracket
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object RagiRecipe {
    //不定形レシピに鉱石辞書をねじ込むメソッド
    fun setOreDict(oreDict: String?): Ingredient {
        //鉱石辞書からItemStackのリストを取得
        val stacks = OreDictionary.getOres(oreDict)
        //結合用のリストを宣言
        val listIngredients: MutableCollection<Ingredient> = ArrayList()
        //stacks内の各keyに対して実行
        for (stack in stacks) {
            //listIngredientsにItemStackを足していく
            listIngredients.add(Ingredient.fromStacks(stack))
        }
        //listIngredientを1つのIngredientにまとめる
        return Ingredient.merge(listIngredients)
    }

    //かまどレシピを追加するメソッド
    fun addFurnace(output: ItemStack, input: ItemStack) {
        GameRegistry.addSmelting(input, output, 0f)
        val bracketIn = input.toBracket()
        val bracketOut = output.toBracket()
        RagiLogger.infoDebug("The smelting recipe $bracketIn -> $bracketOut was added successfully!")
    }

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/recipe/CustomizeVanillaRecipe.java
    */
    //かまどレシピを削除するメソッド
    fun removeFurnace(input: ItemStack) {
        //かまどレシピのマップを取得する
        val mapFurnace = FurnaceRecipes.instance().smeltingList
        //インプットのイテレータを取得する
        val iteratorFurnace = mapFurnace.values.iterator()
        //イテレータの各要素について実行する
        while (iteratorFurnace.hasNext()) {
            //インプットが一致する場合
            if (RagiUtils.isSameStack(mapFurnace[iteratorFurnace.next()]!!, input)) {
                //レシピを削除する
                iteratorFurnace.remove()
                RagiLogger.infoDebug("The smelting input " + input.toBracket() + " was removed successfully!")
            }
        }
    }

    //定型クラフトレシピを追加するメソッド
    fun addShaped(output: ItemStack, vararg inputs: Any?) {
        //registryNameからResource Locationを生成
        val path = output.item.registryName!!.resourcePath + "_" + output.metadata
        val location = ResourceLocation(Reference.MOD_ID, path)
        //レシピを追加する
        GameRegistry.addShapedRecipe(location, location, output, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    //定型クラフトレシピを名前付きで追加するメソッド
    fun addShaped(registryName: String, output: ItemStack?, vararg inputs: Any?) {
        //レシピを上書きする
        GameRegistry.addShapedRecipe(ResourceLocation(registryName), ResourceLocation(registryName), output!!, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$registryName> was overrided successfully!")
    }

    //不定型クラフトレシピを追加するメソッド
    fun addShapeless(output: ItemStack, vararg inputs: Ingredient?) {
        //registryNameからResource Locationを生成
        val path = output.item.registryName!!.resourcePath + "_" + output.metadata
        val location = ResourceLocation(Reference.MOD_ID, path)
        //レシピを追加する
        GameRegistry.addShapelessRecipe(location, location, output, *inputs)
        RagiLogger.infoDebug("The recipe <recipe:$location> was added successfully!")
    }

    //不定型クラフトレシピを名前付きで追加するメソッド
    fun addShapeless(registryName: String, output: ItemStack?, vararg inputs: Ingredient?) {
        //レシピを上書きする
        GameRegistry.addShapelessRecipe(
            ResourceLocation(registryName), ResourceLocation(registryName), output!!, *inputs
        )
        RagiLogger.infoDebug("The recipe <recipe:$registryName> was overrided successfully!")
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
            GameRegistry.addShapedRecipe(
                location, location, recipeBefore.recipeOutput, "A", 'A', RagiUtils.getStack("minecraft:barrier", 1, 0)
            )
            RagiLogger.infoDebug("The recipe <recipe:$registryName> was removed successfully!")
        } else {
            RagiLogger.warnDebug("The recipe <recipe:$registryName> was not found...")
        }
    }
}