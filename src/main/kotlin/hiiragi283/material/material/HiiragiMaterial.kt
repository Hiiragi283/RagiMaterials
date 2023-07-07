package hiiragi283.material.material

import com.google.gson.GsonBuilder
import hiiragi283.material.RagiMaterials
import hiiragi283.material.part.HiiragiPart
import net.minecraft.client.resources.I18n
import rechellatek.snakeToUpperCamelCase
import kotlin.math.roundToInt

/**
 * @param name Name for this material
 * @param index Index for this material
 */

data class HiiragiMaterial internal constructor(
    val name: String,
    val index: Int,
    var color: Int = 0xFFFFFF,
    var crystalType: CrystalType = CrystalType.NONE,
    var formula: String = "",
    var molar: Double = -1.0,
    var tempBoil: Int = -1,
    var tempMelt: Int = -1,
    var tempSubl: Int = -1,
    var translationKey: String = "material.$name"
) {

    var partsAdditional: List<String> = listOf()

    val translatedName: String
        get() = I18n.format(translationKey)

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty", -1)

        @JvmField
        val UNKNOWN = formulaOf("?")

        private val gsonPretty = GsonBuilder().setPrettyPrinting().create()

        private val gson = GsonBuilder().create()

        @JvmStatic
        fun fromJson(json: String): HiiragiMaterial = gson.fromJson(json, HiiragiMaterial::class.java)

    }

    fun addBracket(): HiiragiMaterial = copy(formula = "($formula)")

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

    fun isEmpty(): Boolean = this.name == "empty"

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

}