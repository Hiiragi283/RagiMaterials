package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.ColorManager
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.snakeToUpperCamelCase
import net.minecraft.item.EnumRarity
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

import java.awt.Color

data class RagiMaterial private constructor(
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

        val list: MutableList<RagiMaterial> = mutableListOf()
        val mapIndex: LinkedHashMap<Int, RagiMaterial> = linkedMapOf()
        val mapName: LinkedHashMap<String, RagiMaterial> = linkedMapOf()
        val mapElement: LinkedHashMap<String, RagiMaterial> = linkedMapOf()

        val validPair: MutableList<Pair<MaterialPart, RagiMaterial>> = mutableListOf()
    }

    //nameから液体を取得するメソッド
    fun getFluid(): Fluid? = FluidRegistry.getFluid(this.name)

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String = this.name.snakeToUpperCamelCase()

    //素材が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY
    fun isNotEmpty(): Boolean = !isEmpty()

    //()つきの化学式を返すメソッド
    fun setBracket(): RagiMaterial = Formula("(${this.formula})").build()

    class Builder(val index: Int = -1, val name: String = "empty", val type: MaterialType = TypeRegistry.INTERNAL) {

        var burnTime = -1
        var color = Color(0xFFFFFF)
        var components = listOf(EMPTY to 1)
        var crystalType = EnumCrystalType.NONE
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
            builder.formula = MaterialUtil.getFormula(components)
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
            return ColorManager.mixColor(mapColor)
        }

        //モル質量を自動で生成するメソッド
        private fun initMolar(): Float? {
            var molar = 0.0f
            components.forEach { pair -> pair.first.molar?.let { molar = it * pair.second } }
            return if (molar == 0.0f) null else molar
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
            components.forEach { formula += ",${it.first.formula}" }
            return "(${formula.substring(1)})"
        }

        //素材を単体に設定するメソッド
        fun setSimple(pair: Pair<RagiMaterial, Int>) = run {
            setComponents(listOf(pair))
        }

        fun build() = RagiMaterial(
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
            //indexが負の値でない場合
            if (it.index >= 0) {
                //同じindex, nameで登録されていない場合
                if (mapIndex[it.index] == null && mapName[it.name] == null) {
                    list.add(it)
                    mapIndex[it.index] = it
                    mapName[it.name] = it
                    PartRegistry.list.forEach { part ->
                        if (MaterialUtil.isValidPart(part, it)) {
                            validPair.add(part to it)
                        }
                    }
                } else RagiLogger.warn("The material ${it.name} indexed ${it.index} is duplicated with ${mapIndex[it.index]}!")
            } else RagiLogger.warn("The index ${it.index} is smaller than 0!")
        }
    }

    //元素用のクラス
    class Element(val name: String, val type: MaterialType, val color: Color, val molar: Float, val formula: String, val tempMelt: Int, val tempBoil: Int) {

        fun build() = RagiMaterial(-1, name, type, color = color, formula = formula, molar = molar, tempMelt = tempMelt, tempBoil = tempBoil).also {
            if (mapElement[it.name] == null) {
                mapElement[it.name] = it
            } else RagiLogger.warn("The material ${it.name} is duplicated with ${mapElement[it.name]}!")
        }
    }

    //化学式用のクラス
    class Formula(val name: String) {

        fun build() = RagiMaterial(formula = name)

    }
}