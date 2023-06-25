package hiiragi283.material.api.material

import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiMaterials
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import net.minecraft.client.resource.language.I18n
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import rechellatek.snakeToUpperCamelCase
import kotlin.math.roundToInt

/**
 * @param name Name for this material
 * @param color Material color for this material
 * @param crystalType Type of crystal structure for this material
 * @param formula Chemical formula for this material
 * @param molar Molar Mass for this material
 * @param partsAdditional List of the name of additional parts for this material
 * @param standardState Standard State (under the condition with 1 atm and 298 K) for this material
 * @param tempBoil Boiling point with Kelvin Temperature for this material
 * @param tempBoil Melting point with Kelvin Temperature for this material
 * @param tempBoil Sublimation point with Kelvin Temperature for this material
 * @param translationKey Translation Key for this material: Default is "material.<name>"
 */

@Serializable
class HiiragiMaterial private constructor(
    val name: String,
    val type: MaterialType,
    var color: Int = 0xFFFFFF,
    var crystalType: CrystalType = CrystalType.NONE,
    var formula: String = "",
    var molar: Double = -1.0,
    var partsAdditional: List<String> = listOf(),
    var standardState: StandardState = StandardState.UNDEFINED,
    var tempBoil: Int = -1,
    var tempMelt: Int = -1,
    var tempSubl: Int = -1,
    var translationKey: String = "material.$name",
) {

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty", MaterialType.INTERNAL)

        private val serializer by lazy { Json { prettyPrint = true } }

        @JvmStatic
        fun fromJson(json: String): HiiragiMaterial = Json.decodeFromString(json)
    }

    override fun toString(): String = "Material:$name"

    fun addBracket(): HiiragiMaterial =
        HiiragiMaterial(
            name,
            type,
            color,
            crystalType,
            formula = "($formula)",
            molar,
            partsAdditional,
            standardState,
            tempBoil,
            tempMelt,
            tempSubl
        )

    fun getOreDictName(): String = name.snakeToUpperCamelCase()

    fun appendTooltip(tooltip: MutableList<Text>, part: HiiragiPart = HiiragiPart.EMPTY) {
        if (!isEmpty()) {
            tooltip.add(LiteralText("§e=== Property ==="))
            tooltip.add(TranslatableText("tips.ragi_materials.property.name", getTranslatedName()))
            if (hasFormula())
                tooltip.add(TranslatableText("tips.ragi_materials.property.formula", formula))
            if (hasMolar())
                tooltip.add(TranslatableText("tips.ragi_materials.property.mol", getWeight(part.scale)))
            if (hasTempMelt())
                tooltip.add(TranslatableText("tips.ragi_materials.property.melt", tempMelt))
            if (hasTempBoil())
                tooltip.add(TranslatableText("tips.ragi_materials.property.boil", tempBoil))
            if (hasTempSubl())
                tooltip.add(TranslatableText("tips.ragi_materials.property.subl", tempSubl))
        }
    }

    fun getTranslatedName(name: () -> String = { I18n.translate(translationKey) }): String = name()

    fun getWeight(scale: Double): Double = (molar * scale * 10.0).roundToInt() / 10.0

    fun hasCrystal(): Boolean = crystalType.isCrystal

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun hasStandardState(): Boolean = standardState != StandardState.UNDEFINED

    fun isEmpty(): Boolean = this == EMPTY

    fun isGem(): Boolean = hasCrystal() && !isMetal()

    fun isMetal(): Boolean = type.isMetal

    fun isGas(): Boolean = standardState == StandardState.GAS

    fun isLiquid(): Boolean = standardState == StandardState.LIQUID

    fun isSolid(): Boolean = standardState == StandardState.SOLID

    fun isAdditionalPart(part: String): Boolean = part in partsAdditional

    fun setCrystalType(type: CrystalType) = also {
        if (it.isSolid()) {
            crystalType = type
        } else RagiMaterials.LOGGER.warn("This material has no solid state!")
    }

    //    Builder    //

    open class Builder(private val name: String, private val type: MaterialType) {

        private val components: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

        private fun addComponents(material: HiiragiMaterial, vararg pairs: Pair<HiiragiMaterial, Int>) = also {
            components.putAll(pairs)
            initColor(material)
            initFormula(material)
            initMolar(material)
        }

        //色を自動で生成
        private fun initColor(material: HiiragiMaterial) {
            //material.color = ColorUtil.mixColor(components.map { Color(it.key.color) to it.value }.toMap()).rgb
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
            components.toList().forEach {
                if (it.first.hasMolar()) molar += it.first.molar * it.second
            }
            molar = (molar * 10.0).roundToInt() / 10.0 //小数点1桁まで
            material.molar = molar
        }

        //沸点を自動で生成
        fun initTempBoil(material: HiiragiMaterial) {
            var boil = 0
            components.toList().forEach {
                if (it.first.hasTempBoil()) boil += it.first.tempBoil * it.second
            }
            material.tempBoil = boil
        }

        //融点を自動で生成
        fun initTempMelt(material: HiiragiMaterial) {
            var melt = 0
            components.toList().forEach {
                if (it.first.hasTempMelt()) melt += it.first.tempMelt * it.second
            }
            material.tempMelt = melt
        }

        //昇華点を自動で生成
        fun initTempSubl(material: HiiragiMaterial) {
            var subl = 0
            components.toList().forEach {
                if (it.first.hasTempSubl()) subl += it.first.tempSubl * it.second
            }
            material.tempSubl = subl
        }

        fun initStandardState(material: HiiragiMaterial) {
            //すでに初期化されている場合はパス
            if (material.hasStandardState()) return
            //沸点が有効かつ298 K以下 -> 標準状態で気体
            if (material.hasTempBoil() && material.tempBoil <= 298) {
                material.standardState = StandardState.GAS
                return
            }
            //融点が有効かつ298以下 -> 標準状態で液体
            if (material.hasTempMelt() && material.tempMelt <= 298) {
                material.standardState = StandardState.LIQUID
                return
            }
            //それ以外は固体として扱う
            material.standardState = StandardState.SOLID
        }

        fun build(init: HiiragiMaterial.() -> Unit): HiiragiMaterial {
            val material = HiiragiMaterial(name, type)
            material.init()
            initStandardState(material) //標準状態を初期化
            initCrystalType(material) //結晶構造を初期化
            return material
        }

        fun build(
            vararg components: Pair<HiiragiMaterial, Int>,
            init: HiiragiMaterial.() -> Unit = {}
        ): HiiragiMaterial {
            val material = HiiragiMaterial(name, type)
            addComponents(material, *components)
            material.init()
            initStandardState(material) //標準状態を初期化
            initCrystalType(material) //結晶構造を初期化
            return material
        }

    }

}