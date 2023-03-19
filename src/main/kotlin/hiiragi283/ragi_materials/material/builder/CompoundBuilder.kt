package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import java.awt.Color
import java.math.BigDecimal

open class CompoundBuilder(index: Int, name: String, type: MaterialType, var components: Map<MaterialBuilder, Int>) : MaterialBuilder(index, name, type) {

    init {
        initColor()
        initFormula()
        initMolar()
        register()
    }

    private fun initColor() {
        val mapColor: MutableMap<Color, Int> = mutableMapOf()
        //ColorとIntを対応させる
        for (pair in components.toList()) {
            val material = pair.first
            if (material !is FormulaString) mapColor[material.color] = pair.second
        }
        this.color = RagiColorManager.mixColor(mapColor)
    }

    private fun initFormula() {
        //this.formula = MaterialUtil.getFormula(components)
    }

    private fun initMolar() {
        var molar = BigDecimal.ZERO
        for (pair in components.toList()) {
            pair.first.molar?.let {
                molar = molar.add(it.toBigDecimal() * pair.second.toBigDecimal())
            }
        }
        this.molar = if (molar == BigDecimal.ZERO) null else molar.toFloat()
    }

}