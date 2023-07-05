package hiiragi283.material.material

import com.google.gson.GsonBuilder
import hiiragi283.material.RagiMaterials
import hiiragi283.material.part.HiiragiPart
import hiiragi283.material.util.ColorUtil
import net.minecraft.client.resources.I18n
import rechellatek.snakeToUpperCamelCase
import java.awt.Color
import kotlin.math.roundToInt

/**
 * @param name Name for this material
 * @param index Index for this material
 */

data class HiiragiMaterial(
    val name: String,
    val index: Int
) {

    var color: Int = 0xFFFFFF
    var crystalType: CrystalType = CrystalType.NONE
    var formula: String = ""
    var molar: Double = -1.0
    var partsAdditional: List<String> = listOf()
    var tempBoil: Int = -1
    var tempMelt: Int = -1
    var tempSubl: Int = -1
    var translationKey: String = "material.$name"

    val translatedName: String
        get() = I18n.format(translationKey)

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty", -1)

        private val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        private val gson = GsonBuilder().create()

        @JvmStatic
        fun fromJson(json: String): HiiragiMaterial = gson.fromJson(json, HiiragiMaterial::class.java)

    }

    fun addBracket(): HiiragiMaterial = copy().also { formula = "($formula)" }

    fun getOreDictName() = name.snakeToUpperCamelCase()

    fun getState(): MaterialState {
        //沸点が有効かつ298 K以下 -> 標準状態で気体
        if (hasTempBoil() && tempBoil <= 298) return MaterialState.GAS
        //融点が有効かつ298以下 -> 標準状態で液体
        if (hasTempMelt() && tempMelt <= 298) return MaterialState.LIQUID
        //それ以外は固体として扱う
        return MaterialState.SOLID
    }

    fun getTooltip(tooltip: MutableList<String>, part: HiiragiPart = HiiragiPart.EMPTY) {
        if (!isEmpty()) {
            tooltip.add("§e=== Property ===")
            tooltip.add(I18n.format("tips.ragi_materials.property.name", translatedName))
            if (hasFormula())
                tooltip.add(I18n.format("tips.ragi_materials.property.formula", formula))
            if (hasMolar())
                tooltip.add(I18n.format("tips.ragi_materials.property.mol", getWeight(part.scale)))
            if (hasTempMelt())
                tooltip.add(I18n.format("tips.ragi_materials.property.melt", tempMelt))
            if (hasTempBoil())
                tooltip.add(I18n.format("tips.ragi_materials.property.boil", tempBoil))
            if (hasTempSubl())
                tooltip.add(I18n.format("tips.ragi_materials.property.subl", tempSubl))
        }
    }

    fun getWeight(scale: Double): Double = (molar * scale * 10.0).roundToInt() / 10.0

    fun hasCrystal(): Boolean = crystalType.isCrystal

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun isEmpty(): Boolean = this == EMPTY

    fun isGem(): Boolean = hasCrystal() && !isMetal()

    fun isMetal(): Boolean = crystalType == CrystalType.METAL

    fun isGas(): Boolean = getState() == MaterialState.GAS

    fun isLiquid(): Boolean = getState() == MaterialState.LIQUID

    fun isSolid(): Boolean = getState() == MaterialState.SOLID

    fun isAdditionalPart(part: String): Boolean = part in partsAdditional

    fun setCrystalType(type: CrystalType) = also {
        if (it.isSolid()) {
            crystalType = type
        } else RagiMaterials.LOGGER.warn("This material has no solid state!")
    }

    fun toJson(isPretty: Boolean): String = if (isPretty) gsonPretty.toJson(this) else gson.toJson(this)

    //    General    //

    override fun toString(): String = "Material:${this.name}"

    //    Builder    //

    open class Builder(private val name: String, private val index: Int) {

        private val components: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

        private fun addComponents(material: HiiragiMaterial, vararg pairs: Pair<HiiragiMaterial, Int>) = also {
            components.putAll(pairs)
            initColor(material)
            initFormula(material)
            initMolar(material)
        }

        //色を自動で生成
        private fun initColor(material: HiiragiMaterial) {
            material.color = ColorUtil.mixColor(components.map { Color(it.key.color) to it.value }).rgb
        }

        private fun initCrystalType(material: HiiragiMaterial) {
            //固相を持たない場合は強制的にNONE
            if (!material.isSolid()) material.crystalType = CrystalType.NONE
        }

        //化学式を自動で生成
        private fun initFormula(material: HiiragiMaterial) {
            var result = ""
            for ((material1, weight) in components) {
                //化学式を持たない場合はパス
                if (!material1.hasFormula()) continue
                result += material1.formula
                //値が1の場合はパス
                if (weight == 1) continue
                //化学式の下付き数字の桁数調整
                val subscript1 = '\u2080' + (weight % 10)
                val subscript10 = '\u2080' + (weight / 10)
                //2桁目が0でない場合，下付き数字を2桁にする
                result += StringBuilder().also {
                    if (subscript10 != '\u2080') it.append(subscript10)
                    it.append(subscript1)
                }.toString()
            }
            material.formula = result
        }

        //分子量を自動で生成
        private fun initMolar(material: HiiragiMaterial) {
            var molar = 0.0
            components
                .filter { it.key.hasMolar() }
                .forEach { molar += it.key.tempSubl * it.value }
            molar = (molar * 10.0).roundToInt() / 10.0 //小数点1桁まで
            material.molar = molar
        }

        //沸点を自動で生成
        fun initTempBoil(material: HiiragiMaterial) {
            var boil = 0
            components
                .filter { it.key.hasTempBoil() }
                .forEach { boil += it.key.tempSubl * it.value }
            material.tempBoil = boil
        }

        //融点を自動で生成
        fun initTempMelt(material: HiiragiMaterial) {
            var melt = 0
            components
                .filter { it.key.hasTempMelt() }
                .forEach { melt += it.key.tempSubl * it.value }
            material.tempMelt = melt
        }

        //昇華点を自動で生成
        fun initTempSubl(material: HiiragiMaterial) {
            var subl = 0
            components
                .filter { it.key.hasTempSubl() }
                .forEach { subl += it.key.tempSubl * it.value }
            material.tempSubl = subl
        }

        fun build(init: HiiragiMaterial.() -> Unit): HiiragiMaterial {
            val material = HiiragiMaterial(name, index)
            material.init()
            initBuild(material)
            return material
        }

        fun build(
            vararg components: Pair<HiiragiMaterial, Int>,
            init: HiiragiMaterial.() -> Unit = {}
        ): HiiragiMaterial {
            val material = HiiragiMaterial(name, index)
            addComponents(material, *components)
            material.init()
            initBuild(material)
            return material
        }

        private fun initBuild(material: HiiragiMaterial) {
            //initStandardState(material) //標準状態を初期化
            initCrystalType(material) //結晶構造を初期化
        }

    }

}