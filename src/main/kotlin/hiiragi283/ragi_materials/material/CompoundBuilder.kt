package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.render.color.RagiColor
import hiiragi283.ragi_materials.util.MaterialUtils
import java.awt.Color

open class CompoundBuilder(index: Int, name: String, type: MaterialType.TypeHandler, var components: Map<Any, Int>) :
    MaterialBuilder(index, name, type) {

    init {
        color = RagiColor.mixColor(getColorMap())
        formula = MaterialUtils.calcFormula(components)
        molar = setMolar()
        register()
    }

    constructor(index: Int, material: MaterialBuilder, amount: Int): this(index, material.name, material.type, mapOf(material to amount))

    private fun getColorMap(): MutableMap<Color, Int> {
        //Mapの宣言
        val mapColor: MutableMap<Color, Int> = mutableMapOf()
        //components内の各keyに対して実行
        for (key in components.keys) {
            //ColorとIntを対応させる
            if (key is MaterialBuilder && key.color !== null) mapColor[key.color!!] = components.getValue(key)
        }
        return mapColor
    }

    private fun setMolar(): Float? {
        //変数の宣言
        var molar = 0.0f
        val components = components
        //components内の各keyに対して実行
        for (key in components.keys) {
            //keyがMaterialBuilder型の場合
            if (key is MaterialBuilder && key.molar !== null) {
                molar += key.molar!! * components.getValue(key)
            }
        }
        return if (molar == 0.0f) null else molar
    }
}