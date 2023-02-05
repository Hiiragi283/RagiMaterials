package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.util.MaterialUtils
import hiiragi283.ragi_materials.util.RagiColor
import java.awt.Color

open class CompoundBuilder(index: Int, name: String, type: MaterialType, private val mapComponents: Map<Any, Int>) :
    MaterialBuilder(index, name, type) {

    init {
        this.setColor(RagiColor.mixColor(getColorMap()))
            .setComponents(mapComponents)
            .setFormula(MaterialUtils.calcFormula(mapComponents))
            .setMolarMass(setMolar())
    }

    private fun getColorMap(): MutableMap<Color, Int> {
        //Mapの宣言
        val mapColor: MutableMap<Color, Int> = mutableMapOf()
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //ColorとIntを対応させる
            if (key is MaterialBuilder) mapColor[key.getColor()] = mapComponents.getValue(key)
        }
        return mapColor
    }

    private fun setMolar(): Float {
        //変数の宣言
        var tempMolar: Float = 0.0f
        var divideMolar: Int = 0
        val mapComponents = getComponents()
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //keyがMaterialBuilder型の場合
            if (key is MaterialBuilder) {
                tempMolar += key.getMolarMass() * mapComponents.getValue(key)
                divideMolar += mapComponents.getValue(key)
            }
        }
        //融点の平均値をとる
        tempMolar /= divideMolar
        return tempMolar
    }
}