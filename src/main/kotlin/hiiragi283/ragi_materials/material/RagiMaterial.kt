package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraft.item.EnumRarity
import net.minecraftforge.common.IRarity
import java.awt.Color
import java.math.BigDecimal

data class RagiMaterial(
        val index: Int = -1,
        val name: String = "empty",
        val type: MaterialType = TypeRegistry.INTERNAL,
        var burnTime: Int = -1,
        var color: Color = Color(0xFFFFFF),
        var components: List<Pair<RagiMaterial, Int>> = listOf(EMPTY to 1),
        var crystalType: EnumCrystalType = EnumCrystalType.NONE,
        var decayed: RagiMaterial? = null,
        var formula: String? = null,
        var molar: Float? = null,
        var oredictAlt: String? = null,
        var rarity: IRarity = EnumRarity.COMMON,
        var tempBoil: Int? = null,
        var tempMelt: Int? = null
) {

    companion object {
        val EMPTY = RagiMaterial()
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String = this.name.snakeToUpperCamelCase()

    //素材が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY
    fun isNotEmpty(): Boolean = !isEmpty()

    class Builder(val index: Int, val name: String, val type: MaterialType) {

        var burnTime = -1
        var color: Color = Color(0xFFFFFF)
        var components: List<Pair<RagiMaterial, Int>> = listOf(EMPTY to 1)
        var crystalType: EnumCrystalType = EnumCrystalType.NONE
        var decayed: RagiMaterial? = null
        var formula: String? = null
        var molar: Float? = null
        var oredictAlt: String? = null
        var rarity: IRarity = EnumRarity.COMMON
        var tempBoil: Int? = null
        var tempMelt: Int? = null

        //燃焼時間を設定するメソッド
        fun setBurnTime(time: Int) = also { it.burnTime = time }

        //色を設定するメソッド
        fun setColor(color: Color) = also { it.color = color }

        //結晶の構造を設定するメソッド
        fun setCrystalType(type: EnumCrystalType) = also { it.crystalType = type }

        //崩壊後の素材を設定するメソッド
        fun setDecayed(material: RagiMaterial) = also { it.decayed = material }

        //化学式を設定するメソッド
        fun setFormula(formula: String?) = also { it.formula = formula }

        //モル質量を設定するメソッド
        fun setMolarMass(molar: Float?) = also { it.molar = molar }

        //モル質量を設定するメソッド
        fun setOreDictAlt(oredict: String?) = also { it.oredictAlt = oredict }

        //レア度を設定するメソッド
        fun setRarity(rarity: IRarity) = also { it.rarity = rarity }

        //融点を設定するメソッド
        fun setTempMelt(melt: Int?) = also { it.tempMelt = melt }

        //沸点を設定するメソッド
        fun setTempBoil(boil: Int?) = also { it.tempBoil = boil }

        //素材の組成を設定し，そこから自動的に物性を生成するメソッド
        fun setComponents(components: List<Pair<RagiMaterial, Int>>) = also { builder ->
            builder.components = components
            val materials = builder.components.toMap().keys
            //自動で生成
            builder.color = initColor()
            builder.formula = initFormula()
            builder.molar = initMolar()
            //合金の場合
            if (materials.all { it.type == TypeRegistry.METAL }) {
                builder.tempBoil = initBoil()
                builder.tempMelt = initMelt()
            }
        }

        //色を自動で生成するメソッド
        private fun initColor(): Color {
            val mapColor: MutableMap<Color, Int> = mutableMapOf()
            components.forEach { mapColor[it.first.color] = it.second }
            return RagiColorManager.mixColor(mapColor)
        }

        //化学式を自動で生成するメソッド
        private fun initFormula(): String {
            return MaterialUtil.getFormula(components)
        }

        //モル質量を自動で生成するメソッド
        private fun initMolar(): Float? {
            var molar = BigDecimal.ZERO
            components.forEach { pair -> pair.first.molar?.let { molar = molar.add(it.toBigDecimal() * pair.second.toBigDecimal()) } }
            return if (molar == BigDecimal.ZERO) null else molar.toFloat()
        }

        //沸点を自動で生成するメソッド
        private fun initBoil(): Int? {
            var tempBoil = 0
            var divideBoil = 0
            components.forEach { pair ->
                pair.first.tempBoil?.let {
                    tempBoil += it * pair.second
                    divideBoil += pair.second
                }
            }
            //沸点の平均値をとる
            return if (divideBoil == 0) null else tempBoil / divideBoil
        }

        //融点を自動で生成するメソッド
        private fun initMelt(): Int? {
            var tempMelt = 0
            var divideMelt = 0
            components.forEach { pair ->
                pair.first.tempMelt?.let {
                    tempMelt += it * pair.second
                    divideMelt += pair.second
                }
            }
            //融点の平均値をとる
            return if (divideMelt == 0) null else tempMelt / divideMelt
        }

        //素材を混合物に設定するメソッド
        fun setMixture() = also {
            it.formula = initFormulaMixture()
            it.molar = null
        }

        //混合物用の化学式を自動で生成するメソッド
        private fun initFormulaMixture(): String {
            var formula = ""
            components.forEach { formula += it.first.name }
            return "(${formula.substring(1)})"
        }

        //素材を単体に設定するメソッド
        private fun setSimple() = also {
            val material = components[0].first
            it.tempBoil = material.tempBoil
            it.tempMelt = material.tempMelt
        }

        fun build(): RagiMaterial = RagiMaterial(
                index,
                name,
                type,
                burnTime,
                color,
                components,
                crystalType,
                decayed,
                formula,
                molar,
                oredictAlt,
                rarity,
                tempBoil,
                tempMelt
        ).also {
            MaterialRegistryNew.list.add(it)
            MaterialRegistryNew.mapIndex[it.index] = it
            MaterialRegistryNew.mapName[it.name] = it
            PartRegistry.list.forEach { part -> if (MaterialUtil.isValidPart(part, it)) MaterialRegistryNew.validPair.add(part to it) }
        }
    }

    //元素用のクラス
    class Element(val name: String, val type: MaterialType, val color: Color, val molar: Float, val formula: String, val tempMelt: Int, val tempBoil: Int) {

        fun build(): RagiMaterial = RagiMaterial(-1, name, type, color = color, formula = formula, molar = molar, tempMelt = tempMelt, tempBoil = tempBoil)

    }

    //化学式用のクラス
    class Formula(val name: String) {

        fun build(): RagiMaterial {
            return RagiMaterial(formula = name)
        }
    }
}