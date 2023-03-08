package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.TypeRegistry

class FormulaString(override val name: String): MaterialBuilder(-1, name, TypeRegistry.INTERNAL) {

    init {
        formula = name
    }

}