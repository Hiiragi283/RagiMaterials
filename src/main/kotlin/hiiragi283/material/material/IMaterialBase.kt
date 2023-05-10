package hiiragi283.material.material

import hiiragi283.material.material.type.MaterialType
import java.awt.Color

interface IMaterialBase<T : Any> {

    val name: String
    val type: MaterialType
    val color: Color
    val formula: String
    val molar: Float?

    fun register(): T

}