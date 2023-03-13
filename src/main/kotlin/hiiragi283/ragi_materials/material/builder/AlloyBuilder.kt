package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.material.type.TypeRegistry

class AlloyBuilder(index: Int, name: String, components: Map<MaterialBuilder, Int>) : CompoundBuilder(index, name, TypeRegistry.METAL, components) {

    init {
        initBoil()
        initMelt()
    }

    private fun initBoil() {
        var tempBoil = 0
        var divideBoil = 0
        for (pair in this.components.toList()) {
            //沸点がnullでない場合
            pair.first.tempBoil?.let {
                tempBoil += it * pair.second
                divideBoil += pair.second
            }
        }
        //沸点の平均値をとる
        this.tempBoil = tempBoil / divideBoil
    }

    private fun initMelt() {
        var tempMelt = 0
        var divideMelt = 0
        for (pair in this.components.toList()) {
            //融点がnullでない場合
            pair.first.tempMelt?.let {
                tempMelt += it * pair.second
                divideMelt += pair.second
            }
        }
        //融点の平均値をとる
        this.tempMelt = tempMelt / divideMelt
    }
}