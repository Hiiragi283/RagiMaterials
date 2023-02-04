package hiiragi283.ragi_materials.material

class AlloyBuilder(index: Int, name: String, mapComponents: Map<Any, Int>) :
    CompoundBuilder(index, name, MaterialType.METAL, mapComponents) {

    init {
        this.setTempMelt(setMelt()).setTempBoil(setBoil())
    }

    private fun setMelt(): Int {
        //変数の宣言
        var tempMelt: Int = 0
        var divideMelt: Int = 0
        val mapComponents = getComponents()
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //keyがMaterialBuilder型の場合
            if (key is MaterialBuilder) {
                tempMelt += key.getTempMelt() * mapComponents.getValue(key)
                divideMelt += mapComponents.getValue(key)
            }
        }
        //融点の平均値をとる
        tempMelt /= divideMelt
        return tempMelt
    }

    private fun setBoil(): Int {
        //変数の宣言
        var tempBoil: Int = 0
        var divideBoil: Int = 0
        val mapComponents = getComponents()
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //keyがMaterialBuilder型の場合
            if (key is MaterialBuilder) {
                tempBoil += key.getTempBoil() * mapComponents.getValue(key)
                divideBoil += mapComponents.getValue(key)
            }
        }
        //沸点の平均値をとる
        tempBoil /= divideBoil
        return tempBoil
    }
}