package hiiragi283.material.material

import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor
import kotlinx.serialization.Serializable
import rechellatek.snakeToUpperCamelCase
import java.awt.Color

/**
 * @param name Name for this material
 * @param index Index for this material
 * @param color Material color for this material
 * @param formula Chemical formula for this material
 * @param molar Molar Mass for this material
 * @param tempBoil Boiling point with Kelvin Temperature for this material
 * @param tempBoil Melting point with Kelvin Temperature for this material
 * @param tempBoil Sublimation point with Kelvin Temperature for this material
 */

@Serializable
open class HiiragiMaterial(
    val name: String,
    val index: Int,
    var color: Int = 0xFFFFFF,
    var formula: String = "",
    var molar: Double = -1.0,
    var standardState: StandardState = StandardState.UNDEFINED,
    var tempBoil: Int = -1,
    var tempMelt: Int = -1,
    var tempSubl: Int = -1
) {

    private val components: MutableMap<HiiragiMaterial, Int>
        get() = mutableMapOf(EMPTY to 1)

    val translationKey: String
        get() = "material.$name"

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty", -1)
    }

    fun addComponents(vararg pairs: Pair<HiiragiMaterial, Int>) = also {
        pairs.forEach { components[it.first] = it.second }
        initColor()
        initFormula()
        initMolar()
    }

    //色を自動で生成
    fun initColor() {
        color = ColorUtil.mixColor(components.map { Color(it.key.color) to it.value }.toMap()).rgb
    }

    //化学式を自動で生成
    fun initFormula() {
        var result = ""
        for ((material, weight) in components) {
            //文字を代入する
            result += material.formula
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
    fun initMolar() {
        var molar = 0.0
        components.toList().forEach {
            molar += it.first.molar * it.second
        }
        this.molar = molar
    }

    //沸点を自動で生成
    fun initTempBoil() {
        var boil = 0.0
        components.toList().forEach {
            boil += it.first.tempBoil * it.second
        }
        this.molar = boil
    }

    //融点を自動で生成
    fun initTempMelt() {
        var melt = 0.0
        components.toList().forEach {
            melt += it.first.tempMelt * it.second
        }
        this.molar = melt
    }

    //昇華点を自動で生成
    fun initTempSubl() {
        var subl = 0.0
        components.toList().forEach {
            subl += it.first.tempSubl * it.second
        }
        this.molar = subl
    }

    fun hasFormula(): Boolean = formula.isBlank()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun isEmpty(): Boolean = this == EMPTY

    fun isGas(): Boolean = standardState == StandardState.GAS

    fun isLiquid(): Boolean = standardState == StandardState.LIQUID

    fun isSolid(): Boolean = standardState == StandardState.SOLID

    fun getOreDictName() = name.snakeToUpperCamelCase()

    //物質の標準状態での相を返すメソッド
    fun getState(): StandardState {
        //沸点と融点が有効な場合
        if (hasTempBoil() && hasTempMelt()) {
            //沸点が298 K以下 -> 標準状態で気体
            if (tempBoil <= 298) return StandardState.GAS
            //融点が常温以下 -> 標準状態で液体
            else if (tempMelt <= 298) return StandardState.LIQUID
        }
        //それ以外は固体として扱う
        return StandardState.SOLID
    }

    //    General    //

    override fun equals(other: Any?): Boolean =
        if (other !== null && other is HiiragiMaterial) this.name == other.name else false

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = "Material:${this.name}"

    //    Builder    //

    open class Builder(val name: String, private val index: Int) {

        var color: Color = RagiColor.WHITE
        var formula: String = ""
        var molar: Double = -1.0
        var tempBoil: Int = -1
        var tempMelt: Int = -1
        var tempSubl: Int = -1

        fun setColor(color: Color) = also { this.color = color }
        fun setFormula(formula: String) = also { this.formula = formula }
        fun setMolar(molar: Double) = also { this.molar = molar }
        fun setTempBoil(temp: Int) = also { this.tempBoil = temp }
        fun setTempMelt(temp: Int) = also { this.tempMelt = temp }
        fun setTempSubl(temp: Int) = also { this.tempSubl = temp }

        open fun build(): HiiragiMaterial = HiiragiMaterial(
            name,
            index,
            color.rgb,
            formula,
            molar,
            StandardState.UNDEFINED,
            tempBoil,
            tempMelt,
            tempSubl
        ).also {
            it.standardState = it.getState() //標準状態を初期化
        }

        fun build(init: HiiragiMaterial.() -> Unit): HiiragiMaterial {
            return HiiragiMaterial(name, index).also {
                it.init()
                it.standardState = it.getState() //標準状態を初期化
            }
        }

    }

}