package hiiragi283.ragi_materials.util

import java.util.*

/*
  Thanks to RechellaTek!
  Source: https://play.kotlinlang.org/embed?short=56r8wKERc&theme=darcula&readOnly=true
*/

object RegexStatics {

    private val CAMEL_CASE = "(?<=[a-zA-Z])[A-Z]".toRegex()
    private val SNAKE_CASE = "_[a-zA-Z]".toRegex()


    // String extensions
    fun String.camelToSnakeCase(): String {
        return CAMEL_CASE.replace(this) {
            "_${it.value}"
        }.lowercase(Locale.getDefault())
    }

    fun String.snakeToLowerCamelCase(): String {
        return SNAKE_CASE.replace(this) {
            it.value.replace("_", "").uppercase(Locale.getDefault())
        }
    }

    fun String.snakeToUpperCamelCase(): String {
        return this.snakeToLowerCamelCase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}