package hiiragi283.material.material

import hiiragi283.material.util.ColorUtil

abstract class HiiragiCompound : HiiragiMaterial() {

    fun of(material: HiiragiMaterial, weight: Int): HiiragiMaterial =
        Builder(material.getName(), material.getIndex()).addComponents(material to weight).build()

    class Builder(name: String, index: Int) : HiiragiMaterial.Builder(name, index) {

        private val components: MutableMap<HiiragiMaterial, Int> = mutableMapOf(EMPTY to 1)

        fun addComponents(vararg pairs: Pair<HiiragiMaterial, Int>) = also {
            pairs.forEach { components[it.first] = it.second }
            initProperties()
        }

        private fun initProperties() {
            initColor()
            initFormula()
            initMolar()
            //initTempBoil()
            //initTempMelt()
            //initTempSubl()
        }

        //色を自動で生成
        private fun initColor() {
            color = ColorUtil.mixColor(components.map { it.key.getColor() to it.value }.toMap())
        }

        //化学式を自動で生成
        private fun initFormula() {
            var result = ""
            for ((material, weight) in components) {
                //文字を代入する
                result += material.getFormula()
                //化学式の下付き数字の桁数調整
                val subscript1 = Char(2080 + (weight % 10))
                val subscript10 = Char(2080 + (weight / 10))
                //2桁目が0でない場合，下付き数字を2桁にする
                val subscript =
                    if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
                //下付き数字を代入する
                if (weight > 1) result += subscript
            }
            formula = result
        }

        //分子量を自動で生成
        private fun initMolar() {
            var molar = 0.0
            components.toList().forEach {
                molar += it.first.getMolar() * it.second
            }
            this.molar = molar
        }

        //沸点を自動で生成
        private fun initTempBoil() {
            var boil = 0.0
            components.toList().forEach {
                boil += it.first.getTempBoil() * it.second
            }
            this.molar = boil
        }

        //融点を自動で生成
        private fun initTempMelt() {
            var melt = 0.0
            components.toList().forEach {
                melt += it.first.getTempMelt() * it.second
            }
            this.molar = melt
        }

        //昇華点を自動で生成
        private fun initTempSubl() {
            var subl = 0.0
            components.toList().forEach {
                subl += it.first.getTempSubl() * it.second
            }
            this.molar = subl
        }

    }

}