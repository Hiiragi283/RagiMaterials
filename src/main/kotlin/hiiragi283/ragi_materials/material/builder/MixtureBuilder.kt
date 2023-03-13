package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.material.type.MaterialType
import java.awt.Color

class MixtureBuilder(index: Int, name: String, type: MaterialType, private val list: List<Any>) : MaterialBuilder(index, name, type) {

    init {
        color = Color(0xFFFFFF)
        molar = null
        register()
        setFormula()
    }

    private fun setFormula() {
        //変数の宣言・初期化
        var formula = ""
        //list内の各要素に対して実行
        for (i in list) {
            if (i is MaterialBuilder) formula += ",${i.formula}"
            else if (i is String) formula += ",$i"
        }
        this.formula = "(${formula.substring(1)})"
    }
}