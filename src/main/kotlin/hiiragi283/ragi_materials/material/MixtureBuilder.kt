package hiiragi283.ragi_materials.material

class MixtureBuilder(index: Int, name: String, type: MaterialType, private val list: List<Any>): MaterialBuilder(index, name, type) {

    init {
        color = null
        formula = calcFormula()
        molar = null
        register()
    }

    private fun calcFormula(): String {
        //変数の宣言・初期化
        var formula = ""
        //list内の各要素に対して実行
        for (i in list) {
            if (i is MaterialBuilder) formula += ",${i.formula}"
            else if (i is String) formula += ",$i"
        }
        return "(${formula.substring(1)})" //化学式を整形して返す
    }
}