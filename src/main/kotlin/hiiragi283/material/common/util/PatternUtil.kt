@file:JvmName("FileUtil")

package hiiragi283.material.common.util

import net.devtech.arrp.json.recipe.JIngredient
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern

//    Pattern    //

fun get3x3(key: Char): JPattern {
    val row = StringBuilder().also {
        it.append(key)
        it.append(key)
        it.append(key)
    }.toString()
    return JPattern.pattern(row, row, row)
}

//    Key    //

fun JKeys.addTag(key: String, tag: String): JKeys = also {
    it.key(key, JIngredient.ingredient().tag(tag))
}