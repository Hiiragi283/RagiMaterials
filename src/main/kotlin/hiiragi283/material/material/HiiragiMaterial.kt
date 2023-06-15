package hiiragi283.material.material

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.ColorUtil
import kotlinx.serialization.Serializable
import rechellatek.snakeToUpperCamelCase
import java.awt.Color

/**
 * @param name Name for this material
 * @param index Index for this material
 * @param color Material color for this material
 * @param crystalType Type of crystal structure for this material
 * @param formula Chemical formula for this material
 * @param molar Molar Mass for this material
 * @param standardState Standard State (under the condition with 1 atm and 298 K) for this material
 * @param tempBoil Boiling point with Kelvin Temperature for this material
 * @param tempBoil Melting point with Kelvin Temperature for this material
 * @param tempBoil Sublimation point with Kelvin Temperature for this material
 */

@Serializable
class HiiragiMaterial(
    val name: String,
    val index: Int,
    var color: Int = 0xFFFFFF,
    var crystalType: CrystalType = CrystalType.NONE,
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

    fun getOreDictName() = name.snakeToUpperCamelCase()

    fun hasCrystal(): Boolean = crystalType.isCrystal

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun hasStandardState(): Boolean = standardState != StandardState.UNDEFINED

    fun isEmpty(): Boolean = this == EMPTY

    fun isGem(): Boolean = hasCrystal() && !isMetal()

    fun isMetal(): Boolean = crystalType == CrystalType.METAL

    fun isGas(): Boolean = standardState == StandardState.GAS

    fun isLiquid(): Boolean = standardState == StandardState.LIQUID

    fun isSolid(): Boolean = standardState == StandardState.SOLID

    fun setCrystalType(type: CrystalType) = also {
        if (it.isSolid()) {
            crystalType = type
        } else RagiMaterials.LOGGER.warn("This material has no solid state!")
    }

    //    General    //

    override fun equals(other: Any?): Boolean =
        if (other !== null && other is HiiragiMaterial) this.name == other.name else false

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = "Material:${this.name}"

    //    Initializer    //

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

    fun initCrystalType() {
        //固相を持たない場合は強制的にNONE
        if (!isSolid()) crystalType = CrystalType.NONE
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

    fun initStandardState() {
        //すでに初期化されている場合はパス
        if (hasStandardState()) return
        //沸点と融点が有効な場合
        if (hasTempBoil() && hasTempMelt()) {
            //沸点が298 K以下 -> 標準状態で気体
            if (tempBoil <= 298) {
                standardState = StandardState.GAS
                return
            }
            //融点が常温以下 -> 標準状態で液体
            else if (tempMelt <= 298) {
                standardState = StandardState.LIQUID
                return
            }
        }
        //それ以外は固体として扱う
        standardState = StandardState.SOLID
    }

    //    Builder    //

    open class Builder(private val name: String, private val index: Int) {

        fun build(init: HiiragiMaterial.() -> Unit): HiiragiMaterial {
            val material = HiiragiMaterial(name, index)
            material.init()
            material.initStandardState() //標準状態を初期化
            material.initCrystalType() //結晶構造を初期化
            return material
        }

    }

}