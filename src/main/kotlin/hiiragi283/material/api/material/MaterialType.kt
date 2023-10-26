package hiiragi283.material.api.material

import hiiragi283.material.init.HiiragiIconSets
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.util.HiiragiColor
import java.awt.Color
import kotlin.math.roundToInt

sealed class MaterialType(val name: String) {

    init {
        HiiragiRegistries.MATERIAL_TYPE.register(name, this)
    }

    //    Pre Init    //

    open fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {}

    //    Post Init    //

    open fun postInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {}

    object EMPTY : MaterialType("EMPTY")

    object ELEMENT : MaterialType("ELEMENT") {

        override fun postInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            material.setSmelted(material)
        }

    }

    object ISOTOPE : MaterialType("ISOTOPE") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            val parent: HiiragiMaterial = components.toList()[0].first
            builder.color = parent.color
            builder.crystalType = parent.crystalType
            builder.machineProperty = parent.machineProperty
            builder.tempBoil = parent.tempBoil
            builder.tempMelt = parent.tempMelt
            builder.shapeType = parent.shapeType
        }

        override fun postInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            material.setSmelted(material)
        }

    }

    object COMPOUND : MaterialType("COMPOUND") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            initColor(builder, components)
            initFormula(builder, components)
            initMolar(builder, components)
        }

        //色を自動で生成
        private fun initColor(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            builder.color = HiiragiColor.mixColor(components.map { Color(it.key.color) to it.value }).rgb
        }

        //化学式を自動で生成
        private fun initFormula(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            val stringBuilder = StringBuilder()
            for ((builderIn: HiiragiMaterial, weight: Int) in components) {
                //化学式を持たない場合はパス
                if (!builderIn.hasFormula()) continue
                stringBuilder.append(builderIn.formula)
                //値が1の場合はパス
                if (weight == 1) continue
                //化学式の下付き数字の桁数調整
                val subscript1: Char = '\u2080' + (weight % 10)
                val subscript10: Char = '\u2080' + (weight / 10)
                //2桁目が0でない場合，下付き数字を2桁にする
                stringBuilder.append(
                    StringBuilder().apply {
                        if (subscript10 != '\u2080') append(subscript10)
                        append(subscript1)
                    }
                )
            }
            builder.formula = stringBuilder.toString()
        }

        //分子量を自動で生成
        private fun initMolar(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            var molar = 0.0
            components
                .filter { it.key.hasMolar() }
                .forEach { molar += it.key.molar * it.value }
            molar = (molar * 10.0).roundToInt() / 10.0 //小数点1桁まで
            builder.molar = molar
        }

    }

    object ALLOTROPE : MaterialType("ALLOTROPE") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            val parent: HiiragiMaterial = components.toList()[0].first
            ISOTOPE.preInit(builder, components)
            builder.formula = parent.formula
            builder.molar = parent.molar
        }

    }

    object ALLOY : MaterialType("ALLOY") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            COMPOUND.preInit(builder, components)
            builder.iconSet = HiiragiIconSets.METAL
            initTempBoil(builder, components)
            initTempMelt(builder, components)
        }

        //沸点を自動で生成
        private fun initTempBoil(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            var boil = 0
            var sum = 0
            components
                .filter { it.key.hasTempBoil() }
                .forEach { (material: HiiragiMaterial, weight: Int) ->
                    boil += material.tempBoil * weight
                    sum += weight
                }
            builder.tempBoil = if (sum == 0) -1 else boil / sum
        }

        //融点を自動で生成
        private fun initTempMelt(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            var melt = 0
            var sum = 0
            components
                .filter { it.key.hasTempMelt() }
                .forEach { (material: HiiragiMaterial, weight: Int) ->
                    melt += material.tempMelt * weight
                    sum += weight
                }
            builder.tempMelt = if (sum == 0) -1 else melt / sum
        }

        override fun postInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            material.setSmelted(material)
        }

    }

    object MIXTURE : MaterialType("MIXTURE") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            initFormula(builder, components.keys)
            builder.molar = -1.0//Invalidate molar
        }

        private fun initFormula(builder: HiiragiMaterial.Builder, components: Set<HiiragiMaterial>) {
            val stringBuilder = StringBuilder("(")
            components
                .map { "${it.formula}, " }
                .forEach { stringBuilder.append(it) }
            stringBuilder.setLength(stringBuilder.length - 2) //末尾の", "を取り除く
            stringBuilder.append(")")
            builder.formula = stringBuilder.toString()
        }

    }

    object FORMULA : MaterialType("FORMULA")

    object HYDRATE : MaterialType("HYDRATE") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            val (parent: HiiragiMaterial, amountWater: Int) = components.toList()[0]
            initFormula(builder, parent, amountWater)
            builder.molar = parent.molar + amountWater * MaterialCommons.WATER.molar
        }

        private fun initFormula(builder: HiiragiMaterial.Builder, parent: HiiragiMaterial, amountWater: Int) {
            builder.formula = StringBuilder(parent.formula).apply {
                append("・")
                append(amountWater)
                append(MaterialCommons.WATER.formula)
            }.toString()
        }

        override fun postInit(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
            material.setSmelted(components.toList()[0].first)
        }

    }

    object POLYMER : MaterialType("POLYMER") {

        override fun preInit(builder: HiiragiMaterial.Builder, components: Map<HiiragiMaterial, Int>) {
            COMPOUND.preInit(builder, components)
            builder.formula = "(${builder.formula})n"
            builder.molar = -1.0 //Invalidate molar
        }

    }

}