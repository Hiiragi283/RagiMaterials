package ragi_materials.core.tile

import net.minecraftforge.items.IItemHandler
import ragi_materials.core.recipe.RecipeBase

interface ITileCachable<T : RecipeBase<T>> {

    var cache: T?

    fun cacheRecipe(inventory: IItemHandler, recipes: Collection<T>): Boolean {
        var result = false
        //cacheが空の場合，新規で検索する
        if (cache == null) {
            for (recipe in recipes) {
                if (recipe.match(inventory)) {
                    cache = recipe
                    result = true
                    break
                }
            }
        }
        //cacheがある場合，それが現在のレシピに適応できないなら空にする
        else if (cache!!.match(inventory)) result = true else cache = null
        return result
    }
}