package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.material.type.MaterialType
import java.awt.Color

class MixtureBuilder(index: Int, name: String, type: MaterialType, private val list: List<Any>) : MaterialBuilder(index, name, type) {

    init {
        color = Color(0xFFFFFF)
        initFormula()
        molar = null
        register()
    }

    private fun initFormula() {
        var formula = ""
        for (i in list) {
            if (i is MaterialBuilder) formula += ",${i.formula}"
            else if (i is String) formula += ",$i"
        }
        this.formula = "(${formula.substring(1)})"
    }
}