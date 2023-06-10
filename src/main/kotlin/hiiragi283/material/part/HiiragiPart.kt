package hiiragi283.material.part

import hiiragi283.material.RagiMaterials

data class HiiragiPart(val name: String, val scale: Double) {

    val translationKey = "item.${RagiMaterials.MODID}.$name.name"

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

}