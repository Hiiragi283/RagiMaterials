package hiiragi283.ragi_materials.util

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
        }.toLowerCase()
    }

    fun String.snakeToLowerCamelCase(): String {
        return SNAKE_CASE.replace(this) {
            it.value.replace("_", "").toUpperCase()
        }
    }

    fun String.snakeToUpperCamelCase(): String {
        return this.snakeToLowerCamelCase().capitalize()
    }
}