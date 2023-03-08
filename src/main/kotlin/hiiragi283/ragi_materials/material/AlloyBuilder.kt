package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.TypeRegistry

class AlloyBuilder(index: Int, name: String, components: Map<Any, Int>) : CompoundBuilder(index, name, TypeRegistry.METAL, components) {

    init {
        setBoil()
        setMelt()
    }

    private fun setBoil() {
        //変数の宣言
        var tempBoil = 0
        var divideBoil = 0
        val components = this.components
        //components内の各keyに対して実行
        for (key in components.keys) {
            //keyがMaterialBuilder型，かつ沸点がnullでない場合
            if (key is MaterialBuilder) key.tempBoil?.let {
                tempBoil += it * components.getValue(key)
                divideBoil += components.getValue(key)
            }
        }
        //沸点の平均値をとる
        tempBoil /= divideBoil
        this.tempBoil = tempBoil
    }

    private fun setMelt() {
        //変数の宣言
        var tempMelt = 0
        var divideMelt = 0
        val components = this.components
        //components内の各keyに対して実行
        for (key in components.keys) {
            //keyがMaterialBuilder型，かつ融点がnullでない場合
            if (key is MaterialBuilder) key.tempMelt?.let {
                tempMelt += it * components.getValue(key)
                divideMelt += components.getValue(key)
            }
        }
        //融点の平均値をとる
        tempMelt /= divideMelt
        this.tempMelt = tempMelt
    }
}