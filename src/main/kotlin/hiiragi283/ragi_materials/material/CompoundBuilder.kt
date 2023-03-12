package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.client.render.color.RagiColor
import java.awt.Color
import java.math.BigDecimal

open class CompoundBuilder(index: Int, name: String, type: MaterialType, var components: Map<MaterialBuilder, Int>) : MaterialBuilder(index, name, type) {

    init {
        setColor()
        setFormula(MaterialUtil.getFormula(components))
        setMolar()
        register()
    }

    private fun setColor() {
        //Mapの宣言
        val mapColor: MutableMap<Color, Int> = mutableMapOf()
        //components内の各keyに対して実行
        for (key in components.keys) {
            //ColorとIntを対応させる
            if (key !is FormulaString) mapColor[key.color] = components.getValue(key)
        }
        this.color = RagiColor.mixColor(mapColor)
    }

    private fun setMolar() {
        //変数の宣言
        val molar = BigDecimal.ZERO
        val components = components
        //components内の各keyに対して実行
        for (key in components.keys) {
            key.molar?.let { molar.add(it.toBigDecimal() * components.getValue(key).toBigDecimal()) }
        }
        this.molar = if (molar == BigDecimal.ZERO) null else molar.toFloat()
    }
}