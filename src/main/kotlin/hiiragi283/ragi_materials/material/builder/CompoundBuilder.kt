package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.client.color.ColorManager
import hiiragi283.ragi_materials.material.type.MaterialType
import java.awt.Color

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
        this.color = ColorManager.mixColor(mapColor)
    }

    private fun initFormula() {
        //this.formula = MaterialUtil.getFormula(components)
    }

    private fun initMolar() {
        var molar = 0.0f
        for (pair in components.toList()) {
            pair.first.molar?.let { molar = it * pair.second }
        }
        this.molar = if (molar == 0.0f) null else molar
    }

}