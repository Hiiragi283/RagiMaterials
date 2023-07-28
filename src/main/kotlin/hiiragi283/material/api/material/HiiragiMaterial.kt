package hiiragi283.material.api.material

import hiiragi283.material.RagiMaterials
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistryEntry
import rechellatek.snakeToUpperCamelCase

/*
  === Index Range ===
          <= -1 ... Not registered
        1 ~ 118 ... Periodic Table
      120 ~ 199 ... Isotopes
    1000 ~ 1900 ... Integration for other mods
  10010 ~ 11899 ... Compounds (1 + Atomic Number + Index)
       >= 32768 ... Not registered
*/

@Serializable
data class HiiragiMaterial internal constructor(
    val name: String,
    val index: Int,
    var color: Int = 0xFFFFFF,
    var crystalType: CrystalType = CrystalType.NONE,
    var formula: String = "",
    var molar: Double = -1.0,
    var oreDictAlt: MutableList<String> = mutableListOf(),
    var tempBoil: Int = -1,
    var tempMelt: Int = -1,
    var tempSubl: Int = -1,
    var translationKey: String = "material.$name"
) : IForgeRegistryEntry<HiiragiMaterial> {

    val validShapes: MutableSet<String> = MaterialType.INTERNAL.toSortedSet()

    val translatedName: String
        get() = I18n.format(translationKey)

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty", -1)

        @JvmField
        val UNKNOWN = formulaOf("?")

        private val pretty = Json { prettyPrint = true }

        @JvmStatic
        fun fromJson(json: String): HiiragiMaterial = Json.decodeFromString(json)

    }

    fun addBracket(): HiiragiMaterial = copy(formula = "($formula)")

    fun getOreDictName(): String = name.snakeToUpperCamelCase()

    fun getOreDictNameAlt(): List<String> = oreDictAlt.map { it.snakeToUpperCamelCase() }

    fun getState(): MaterialState {
        //沸点が有効かつ298 K以下 -> 標準状態で気体
        if (hasTempBoil() && tempBoil <= 298) return MaterialState.GAS
        //融点が有効かつ298以下 -> 標準状態で液体
        if (hasTempMelt() && tempMelt <= 298) return MaterialState.LIQUID
        //それ以外は固体として扱う
        return MaterialState.SOLID
    }

    fun hasOreDictAlt(): Boolean = oreDictAlt.isNotEmpty()

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun isEmpty(): Boolean = this.name == "empty"

    fun isGem(): Boolean = crystalType.isCrystal && !isMetal()

    fun isMetal(): Boolean = crystalType == CrystalType.METAL

    fun isGas(): Boolean = getState() == MaterialState.GAS

    fun isLiquid(): Boolean = getState() == MaterialState.LIQUID

    fun isSolid(): Boolean = getState() == MaterialState.SOLID

    fun setCrystalType(type: CrystalType) = also {
        if (it.isSolid()) {
            crystalType = type
        } else RagiMaterials.LOGGER.warn("This material has no solid state!")
    }

    fun toJson(isPretty: Boolean): String = if (isPretty) pretty.encodeToString(this) else Json.encodeToString(this)

    //    General    //

    override fun toString(): String = "Material:${this.name}"

    //    IForgeRegistryEntry    //

    private val location: ResourceLocation
        get() = ResourceLocation(RagiMaterials.MODID, name)

    override fun getRegistryName(): ResourceLocation = location

    @Suppress("UNCHECKED_CAST")
    override fun getRegistryType(): Class<HiiragiMaterial> = this::class.java as Class<HiiragiMaterial>

    override fun setRegistryName(name: ResourceLocation): HiiragiMaterial {
        throw IllegalArgumentException("Cannot override Registry Name!")
    }

}