package ragi_materials.core.material

import net.minecraft.client.resources.I18n
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.MaterialType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.ColorUtil
import ragi_materials.core.util.getMaterialFromName
import ragi_materials.core.util.snakeToUpperCamelCase
import java.awt.Color

/**
 * Register your custom materials until Pre-Initialization
 */

class RagiMaterial private constructor(
        val index: Int = -1,
        val name: String = "empty",
        val type: MaterialType = TypeRegistry.INTERNAL,
        var burnTime: Int = -1,
        var color: Color = Color(0xFFFFFF),
        var components: List<Pair<RagiMaterial, Int>> = listOf(EMPTY to 1),
        var crystalType: EnumCrystalType = EnumCrystalType.NONE,
        var formula: String? = null,
        var mapSubMaterials: Map<EnumSubMaterial, RagiMaterial?> = mapOf(),
        var mapTemp: Map<EnumTemp, Int?> = mapOf(),
        var molar: Float? = null,
        var oredictAlt: String? = null,
        var rarity: IRarity = EnumRarity.COMMON
) {

    companion object {
        @JvmStatic
        val EMPTY = RagiMaterial()

        //NBTタグから素材を取得するメソッド
        @JvmStatic
        fun readFromNBT(tag: NBTTagCompound) = getMaterialFromName(tag.getString("material"))
    }

    //nameから液体を取得するメソッド
    fun getFluid(): Fluid? = FluidRegistry.getFluid(this.name)

    //放射性崩壊後の素材を取得するメソッド
    fun getDecayed() = mapSubMaterials[EnumSubMaterial.DECAYED]

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String = this.name.snakeToUpperCamelCase()

    //部品を取得するメソッド
    fun getPart(part: MaterialPart, amount: Int = 1): ItemStack = if (isValidPart(part)) ItemStack(RagiRegistry.mapMaterialParts[part]!!, amount, index) else ItemStack.EMPTY

    //融点を取得するメソッド
    fun getTempMelt() = mapTemp[EnumTemp.MELTING]

    //沸点を取得するメソッド
    fun getTempBoil() = mapTemp[EnumTemp.BOILING]

    //昇華点を取得するメソッド
    fun getTempSubl() = mapTemp[EnumTemp.SUBLIMATION]

    //materialのツールチップを生成するメソッド
    fun getTooltip(tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", I18n.format("material.$name"))) //名称
        formula?.let { tooltip.add(I18n.format("tips.ragi_materials.property.formula", it)) } //化学式
        molar?.let { tooltip.add(I18n.format("tips.ragi_materials.property.mol", it)) } //モル質量
        getTempMelt()?.let { tooltip.add(I18n.format("tips.ragi_materials.property.melt", it)) } //融点
        getTempBoil()?.let { tooltip.add(I18n.format("tips.ragi_materials.property.boil", it)) } //沸点
        getTempSubl()?.let { tooltip.add(I18n.format("tips.ragi_materials.property.subl", it)) }//昇華点
    }

    //素材が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY
    fun isNotEmpty(): Boolean = !isEmpty()

    //部品と素材の組み合わせが有効か判定するメソッド
    fun isValidPart(part: MaterialPart): Boolean = part.type in type.list

    //()つきの化学式を返すメソッド
    fun setBracket(): RagiMaterial = Formula("(${this.formula})").build()

    //NBTタグに素材を書き込むメソッド
    fun writeToNBT(tag: NBTTagCompound) = tag.also { it.setString("material", name) }

    fun writeToNBT() = writeToNBT(NBTTagCompound())

    class Builder(val index: Int = -1, val name: String = "empty", val type: MaterialType = TypeRegistry.INTERNAL) {

        var burnTime = -1
        var color = Color(0xFFFFFF)
        var components = listOf(EMPTY to 1)
        var crystalType = EnumCrystalType.NONE
        var formula: String? = null
        var molar: Float? = null
        var oredictAlt: String? = null
        var rarity: IRarity = EnumRarity.COMMON
        var tempBoil: Int? = null
        var tempMelt: Int? = null
        var tempSubl: Int? = null
        private var mapSubMaterial: MutableMap<EnumSubMaterial, RagiMaterial?> = mutableMapOf()

        //燃焼時間を設定するメソッド
        fun setBurnTime(time: Int) = also { it.burnTime = time }

        //色を設定するメソッド
        fun setColor(color: Color) = also { it.color = color }

        //結晶の構造を設定するメソッド
        fun setCrystalType(type: EnumCrystalType) = also { it.crystalType = type }

        //関連した素材を設定するメソッド
        fun setSubMaterial(type: EnumSubMaterial, material: RagiMaterial) = also { it.mapSubMaterial[type] = material }

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

        //昇華点を設定するメソッド
        fun setTempSubl(subl: Int?) = also { it.tempSubl = subl }

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
            return ColorUtil.mixColor(mapColor)
        }

        //化学式を自動で生成するメソッド
        private fun initFormula(): String {
            //変数の宣言・初期化
            var formula = ""
            var subscript: String
            var subscript1 = '\u2080'
            var subscript10 = '\u2080'
            components.forEach {
                //文字を代入する
                formula += it.first.formula
                val weight = it.second
                //化学式の下付き数字の桁数調整
                if (weight in 2..9) subscript1 = '\u2080' + weight
                else if (weight in 10..99) {
                    subscript1 = '\u2080' + (weight % 10)
                    subscript10 = '\u2080' + (weight / 10)
                }
                //2桁目が0でない場合，下付き数字を2桁にする
                subscript = if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
                //下付き数字を代入する
                if (weight > 1) formula += subscript
            }
            //化学式を返す
            return formula
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
                pair.first.getTempBoil()?.let {
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
                pair.first.getTempMelt()?.let {
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
        fun setSimple(pair: Pair<RagiMaterial, Int>) = also {
            setComponents(listOf(pair))
            tempMelt = pair.first.getTempMelt()
            tempBoil = pair.first.getTempBoil()
            tempSubl = pair.first.getTempSubl()
        }

        fun build(): RagiMaterial = RagiMaterial(
                index,
                name,
                type,
                burnTime,
                color,
                components,
                crystalType,
                formula,
                mapSubMaterial,
                mapOf(
                        EnumTemp.MELTING to tempMelt,
                        EnumTemp.BOILING to tempBoil,
                        EnumTemp.SUBLIMATION to tempSubl
                ),
                molar,
                oredictAlt,
                rarity,
        ).also {
            //indexが負の値でない場合
            if (it.index >= 0) {
                //同じindex, nameで登録されていない場合
                if (MaterialRegistry.mapIndex[it.index] == null && MaterialRegistry.mapName[it.name] == null) {
                    MaterialRegistry.list.add(it)
                    MaterialRegistry.mapIndex[it.index] = it
                    MaterialRegistry.mapName[it.name] = it
                    PartRegistry.list.forEach { part ->
                        if (it.isValidPart(part)) {
                            MaterialRegistry.validPair.add(part to it)
                        }
                    }
                } else RagiMaterials.LOGGER.warn("The material ${it.name} indexed ${it.index} is duplicated with ${MaterialRegistry.mapIndex[it.index]?.name}!")
            } else RagiMaterials.LOGGER.warn("The index ${it.index} is smaller than 0!")
        }
    }

    //元素用のクラス
    class Element(val name: String, val type: MaterialType, val color: Color, val molar: Float, val formula: String, val tempMelt: Int? = null, val tempBoil: Int? = null, val tempSubl: Int? = null) {

        fun build() = RagiMaterial(-1, name, type, color = color, formula = formula, molar = molar, mapTemp = mapOf(EnumTemp.MELTING to tempMelt, EnumTemp.BOILING to tempBoil, EnumTemp.SUBLIMATION to tempSubl)).also {
            if (MaterialRegistry.mapElement[it.name] == null) {
                MaterialRegistry.mapElement[it.name] = it
            } else RagiMaterials.LOGGER.warn("The material ${it.name} is duplicated with ${MaterialRegistry.mapElement[it.name]?.name}!")
        }
    }

    //化学式用のクラス
    class Formula(val name: String) {

        fun build() = RagiMaterial(formula = name)

    }
}