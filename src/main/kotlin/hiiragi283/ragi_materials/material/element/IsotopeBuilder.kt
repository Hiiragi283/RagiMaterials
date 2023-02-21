package hiiragi283.ragi_materials.material.element

import hiiragi283.ragi_materials.material.MaterialBuilder

class IsotopeBuilder(name: String,
                     material: MaterialBuilder,
                     override var molar: Float?,
                     override var formula: String?
                     ) :
    ElementBuilder(name, material.type, material.color, molar, formula, material.tempMelt, material.tempBoil)