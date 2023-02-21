package hiiragi283.ragi_materials.material

class AlloyBuilder(index: Int, name: String, components: Map<Any, Int>) :
    CompoundBuilder(index, name, MaterialType.METAL, components) {

    init {
        tempMelt = setMelt()
        tempBoil = setBoil()
    }

    private fun setMelt(): Int {
        //変数の宣言
        var tempMelt = 0
        var divideMelt = 0
        val components = this.components
        //components内の各keyに対して実行
        for (key in components.keys) {
            //keyがMaterialBuilder型，かつ融点がnullでない場合
            if ((key is MaterialBuilder) && (key.tempMelt !== null)) {
                tempMelt += key.tempMelt!! * components.getValue(key)
                divideMelt += components.getValue(key)
            }
        }
        //融点の平均値をとる
        tempMelt /= divideMelt
        return tempMelt
    }

    private fun setBoil(): Int {
        //変数の宣言
        var tempBoil = 0
        var divideBoil = 0
        val components = this.components
        //components内の各keyに対して実行
        for (key in components.keys) {
            //keyがMaterialBuilder型，かつ沸点がnullでない場合
            if ((key is MaterialBuilder) && (key.tempBoil !== null)) {
                tempBoil += key.tempBoil!! * components.getValue(key)
                divideBoil += components.getValue(key)
            }
        }
        //沸点の平均値をとる
        tempBoil /= divideBoil
        return tempBoil
    }
}