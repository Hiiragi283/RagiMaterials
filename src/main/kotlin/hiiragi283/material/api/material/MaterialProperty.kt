package hiiragi283.material.api.material

import kotlinx.serialization.Serializable

fun propertyOf(init: MaterialProperty.() -> Unit): MaterialProperty {
    val property = MaterialProperty()
    property.init()
    return property
}

@Serializable
data class MaterialProperty internal constructor(
    var isMetal: Boolean = true,
    var isGem: Boolean = false,
    var isActiveToWater: Boolean = false
) {

    var burnTime: Int = 0
    var hasOre: Boolean = false

    companion object {

        @JvmField
        val EMPTY = MaterialProperty()

        @JvmField
        val ALKALI_METAL = propertyOf { isActiveToWater = true }

        @JvmField
        val ALKALINE_METAL = propertyOf { isActiveToWater = true }

        @JvmField
        val GEM = propertyOf {
            isMetal = false
            isGem = true
        }

    }

    fun isEmpty(): Boolean = this == EMPTY

}