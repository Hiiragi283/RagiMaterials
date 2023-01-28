package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.MaterialUtils
import hiiragi283.ragi_materials.util.RagiColor
import java.awt.Color

class CompoundBuilder(override val index: Int, override val name: String, private val mapComponents: Map<Any, Int>) :
    MaterialBuilder(index, name, MaterialType.METAL) {

    init {
        this.setColor(RagiColor.mixColor(getColorMap()))
        this.setFormula(MaterialUtils.calcFormula(mapComponents))
        this.setTempMelt(setMelt())
        this.setTempBoil(setBoil())
    }

    private fun getColorMap(): MutableMap<Color, Int> {
        //Mapの宣言
        val mapColor: MutableMap<Color, Int> = mutableMapOf()
        //mapComponents内の各keyに対して実行
        for (i in mapComponents.keys) {
            //ColorとIntを対応させる
            if (i is MaterialBuilder) mapColor[i.getColor()] = mapComponents.getValue(i)
        }
        return mapColor
    }

    private fun setMelt(): Int {
        //変数の宣言
        var tempMelt: Int = 0
        var divideMelt: Int = 0
        //mapComponents内の各keyに対して実行
        for (i in mapComponents.keys) {
            //keyがMaterialBuilder型の場合
            if (i is MaterialBuilder) {
                tempMelt += i.getTempMelt() * mapComponents.getValue(i)
                divideMelt += mapComponents.getValue(i)
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
        //mapComponents内の各keyに対して実行
        for (i in mapComponents.keys) {
            //keyがMaterialBuilder型の場合
            if (i is MaterialBuilder) {
                tempBoil += i.getTempBoil() * mapComponents.getValue(i)
                divideBoil += mapComponents.getValue(i)
            }
        }
        //沸点の平均値をとる
        tempBoil /= divideBoil
        return tempBoil
    }
}