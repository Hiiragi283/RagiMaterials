package hiiragi283.ragi_materials.material.builder


import hiiragi283.ragi_materials.material.type.MaterialType
import java.awt.Color

open class ElementBuilder(
        index: Int,
        name: String,
        type: MaterialType,
        override var color: Color,
        override var molar: Float?,
        override var formula: String?,
        override var tempMelt: Int?,
        override var tempBoil: Int?
) : MaterialBuilder(index, name, type)