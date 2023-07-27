package hiiragi283.material.api.material

import hiiragi283.material.api.item.HiiragiToolMaterial
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.minecraft.client.resource.language.I18n
import net.minecraft.util.Identifier

/**
 * @param name Name for this material
 * @param color Material color for this material
 * @param crystalType Type of crystal structure for this material
 * @param formula Chemical formula for this material
 * @param molar Molar Mass for this material
 * @param tempBoil Boiling point with Kelvin Temperature for this material
 * @param tempBoil Melting point with Kelvin Temperature for this material
 * @param tempBoil Sublimation point with Kelvin Temperature for this material
 * @param translationKey Translation Key for this material: Default is "material.<name>"
 */

@Serializable
data class HiiragiMaterial internal constructor(
    val name: String,
    var burnTime: Int = -1,
    var color: Int = 0xFFFFFF,
    var crystalType: CrystalType = CrystalType.NONE,
    var formula: String = "",
    var hasOre: Boolean = false,
    var isActiveToWater: Boolean = false,
    var molar: Double = -1.0,
    var tempBoil: Int = -1,
    var tempMelt: Int = -1,
    var tempSubl: Int = -1,
    var translationKey: String = "material.$name",
) {

    var toolProperty: HiiragiToolMaterial = HiiragiToolMaterial.EMPTY
    val validShapes: MutableSet<String> = MaterialType.INTERNAL.toSortedSet()

    init {
        if (hasOre) {
            validShapes.add("ore_deep")
            validShapes.add("ore_end")
            validShapes.add("ore_nether")
            validShapes.add("ore_stone")
        }
    }

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty")

        @JvmField
        val UNKNOWN = formulaOf("?")

        private val pretty = Json { prettyPrint = true }

        @JvmStatic
        fun fromJson(json: String): HiiragiMaterial = Json.decodeFromString(json)

    }

    override fun toString(): String = "material:$name"

    fun addBracket(): HiiragiMaterial = copy(formula = "($formula)")

    fun getState(): MaterialState {
        //沸点が有効かつ298 K以下 -> 標準状態で気体
        if (hasTempBoil() && tempBoil <= 298) return MaterialState.GAS
        //融点が有効かつ298以下 -> 標準状態で液体
        if (hasTempMelt() && tempMelt <= 298) return MaterialState.LIQUID
        //それ以外は固体として扱う
        return MaterialState.SOLID
    }

    fun getTagId(): Identifier = hiiragiId(name)

    fun getTranslatedName(): String = I18n.translate(translationKey)

    fun hasCrystal(): Boolean = crystalType.isCrystal

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun hasToolProperty(): Boolean = toolProperty != HiiragiToolMaterial.EMPTY

    fun isEmpty(): Boolean = this == EMPTY

    fun isGem(): Boolean = crystalType.isCrystal

    fun isMetal(): Boolean = crystalType == CrystalType.METAL && !isGem()

    fun isGas(): Boolean = getState() == MaterialState.GAS

    fun isLiquid(): Boolean = getState() == MaterialState.LIQUID

    fun isSolid(): Boolean = getState() == MaterialState.SOLID

    fun setCrystalType(type: CrystalType) = also {
        if (it.isSolid()) {
            crystalType = type
        } else RagiMaterials.LOGGER.warn("This material has no solid state!")
    }

    fun toJson(isPretty: Boolean): String = if (isPretty) pretty.encodeToString(this) else Json.encodeToString(this)

}