package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.render.color.RagiColor
import hiiragi283.ragi_materials.util.MaterialUtils
import java.awt.Color
import kotlin.math.roundToInt

open class CompoundBuilder(index: Int, name: String, type: MaterialType, var mapComponents: Map<Any, Int>) :
    MaterialBuilder(index, name, type) {

    init {
        this.setColor(RagiColor.mixColor(getColorMap()))
            formula = MaterialUtils.calcFormula(mapComponents)
            molar = setMolar()
    }

    private fun getColorMap(): MutableMap<Color, Int> {
        //Mapの宣言
        val mapColor: MutableMap<Color, Int> = mutableMapOf()
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //ColorとIntを対応させる
            if (key is MaterialBuilder) mapColor[key.color] = mapComponents.getValue(key)
        }
        return mapColor
    }

    private fun setMolar(): Float {
        //変数の宣言
        var tempMolar = 0.0f
        var divideMolar = 0
        val mapComponents = mapComponents
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //keyがMaterialBuilder型の場合
            if (key is MaterialBuilder) {
                tempMolar += key.molar * mapComponents.getValue(key)
                divideMolar += mapComponents.getValue(key)
            }
        }
        tempMolar /= divideMolar //融点の平均値をとる
        tempMolar = (tempMolar * 100).roundToInt() / 100.0f //平均値を100倍したあと丸め込み，さらに100で割ることで小数点2桁までにする
        return tempMolar
    }
}