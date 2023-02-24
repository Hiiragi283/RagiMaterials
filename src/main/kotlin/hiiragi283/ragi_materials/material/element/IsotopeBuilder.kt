package hiiragi283.ragi_materials.material.element

class IsotopeBuilder(name: String,
                     material: ElementBuilder,
                     override var molar: Float?,
                     override var formula: String?
                     ) :
    ElementBuilder(name, material.type, material.color!!, material.molar!!, material.formula!!, material.tempMelt!!, material.tempBoil!!)