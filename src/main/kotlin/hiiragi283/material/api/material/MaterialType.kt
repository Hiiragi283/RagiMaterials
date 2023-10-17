package hiiragi283.material.api.material

import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.util.HiiragiColor
import java.awt.Color
import kotlin.math.roundToInt

sealed class MaterialType(val name: String) {

    open fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {}

    open fun postInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {}

    init {
        HiiragiRegistries.MATERIAL_TYPE.register(name, this)
    }

    object ELEMENT : MaterialType("ELEMENT")

    object ISOTOPE : MaterialType("ISOTOPE") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            val parent: HiiragiMaterial = components.toList()[0].first
            material.color = parent.color
            material.tempBoil = parent.tempBoil
            material.tempMelt = parent.tempMelt
            material.shapeType = parent.shapeType
        }

    }

    object COMPOUND : MaterialType("COMPOUND") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            initColor(material, components)
            initFormula(material, components)
            initMolar(material, components)
        }

        //色を自動で生成
        private fun initColor(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            material.color = HiiragiColor.mixColor(components.map { Color(it.key.color) to it.value }).rgb
        }

        //化学式を自動で生成
        private fun initFormula(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            val builder = StringBuilder()
            for ((materialIn: HiiragiMaterial, weight: Int) in components) {
                //化学式を持たない場合はパス
                if (!materialIn.hasFormula()) continue
                builder.append(materialIn.formula)
                //値が1の場合はパス
                if (weight == 1) continue
                //化学式の下付き数字の桁数調整
                val subscript1: Char = '\u2080' + (weight % 10)
                val subscript10: Char = '\u2080' + (weight / 10)
                //2桁目が0でない場合，下付き数字を2桁にする
                builder.append(
                    StringBuilder().also { builderInner: StringBuilder ->
                        if (subscript10 != '\u2080') builderInner.append(subscript10)
                        builderInner.append(subscript1)
                    }
                )
            }
            material.formula = builder.toString()
        }

        //分子量を自動で生成
        private fun initMolar(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            var molar = 0.0
            components
                .filter { it.key.hasMolar() }
                .forEach { molar += it.key.molar * it.value }
            molar = (molar * 10.0).roundToInt() / 10.0 //小数点1桁まで
            material.molar = molar
        }

    }

    object ALLOTROPE : MaterialType("ALLOTROPE") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            val parent: HiiragiMaterial = components.toList()[0].first
            ISOTOPE.preInit(material, components)
            material.formula = parent.formula
            material.molar = parent.molar
        }

    }

    object ALLOY : MaterialType("ALLOY") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            COMPOUND.preInit(material, components)
            initTempBoil(material, components)
            initTempMelt(material, components)
        }

        //沸点を自動で生成
        private fun initTempBoil(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            var boil = 0
            var sum = 0
            components
                .filter { it.key.hasTempBoil() }
                .forEach { (material: HiiragiMaterial, weight: Int) ->
                    boil += material.tempBoil * weight
                    sum += weight
                }
            material.tempBoil = if (sum == 0) -1 else boil / sum
        }

        //融点を自動で生成
        private fun initTempMelt(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            var melt = 0
            var sum = 0
            components
                .filter { it.key.hasTempMelt() }
                .forEach { (material: HiiragiMaterial, weight: Int) ->
                    melt += material.tempMelt * weight
                    sum += weight
                }
            material.tempMelt = if (sum == 0) -1 else melt / sum
        }

    }

    object MIXTURE : MaterialType("MIXTURE") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            initFormula(material, components.keys)
            material.molar = -1.0//Invalidate molar
        }

        private fun initFormula(material: HiiragiMaterial, components: Set<HiiragiMaterial>) {
            val builder = StringBuilder("(")
            components
                .map { "${it.formula}, " }
                .forEach { builder.append(it) }
            builder.setLength(builder.length - 2) //末尾の", "を取り除く
            builder.append(")")
            material.formula = builder.toString()
        }

    }

    object FORMULA : MaterialType("FORMULA")

    object HYDRATE : MaterialType("HYDRATE") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            val (parent: HiiragiMaterial, amountWater: Int) = components.toList()[0]
            initFormula(material, parent, amountWater)
            material.molar = parent.molar + amountWater * MaterialCommons.WATER.molar
        }

        private fun initFormula(material: HiiragiMaterial, parent: HiiragiMaterial, amountWater: Int) {
            material.formula = StringBuilder(parent.formula).also { builder: StringBuilder ->
                builder.append("・")
                builder.append(amountWater)
                builder.append(MaterialCommons.WATER.formula)
            }.toString()
        }

    }

    object POLYMER : MaterialType("POLYMER") {

        override fun preInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            COMPOUND.preInit(material, components)
            material.formula = "(${material.formula})n"
            material.molar = -1.0 //Invalidate molar
        }

    }

}