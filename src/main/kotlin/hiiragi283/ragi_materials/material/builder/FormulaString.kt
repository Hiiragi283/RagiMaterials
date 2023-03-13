package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.material.type.TypeRegistry

class FormulaString(override val name: String) : MaterialBuilder(-1, name, TypeRegistry.INTERNAL) {

    init {
        formula = name
    }

}