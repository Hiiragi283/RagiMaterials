package hiiragi283.api.material

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.fluid.MaterialFluid
import hiiragi283.api.fluid.MaterialFluidSupplier
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.shape.HiiragiShape
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import rechellatek.snakeToUpperCamelCase

/**
 * An object which contains several properties of material
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiMaterial]>
 *
 *   === Index Range ===
 *
 *            <= -1 ... Not registered
 *
 *          1 ~ 118 ... Periodic Table
 *
 *        120 ~ 199 ... Isotopes
 *
 *      1000 ~ 1900 ... Integration for other mods
 *
 *    10010 ~ 11899 ... Compounds: 1 + Atomic Number + Index
 *
 *         >= 32768 ... Not registered
 *
 * @param name should be unique
 * @param index follows above format
 * @param color see also [hiiragi283.material.util.HiiragiColor]
 * @param crystalType see also [CrystalType]
 * @param formula empty value will be ignored
 * @param molar 0 or less value will be ignored
 * @param tempBoil boiling point with kelvin Temperature, 0 or less will be ignored
 * @param tempMelt melting point with kelvin Temperature, 0 or less will be ignored
 * @param tempSubl sublimation point with kelvin Temperature, 0 or less will be ignored
 * @param translationKey can be overridden
 */

data class HiiragiMaterial internal constructor(
    val name: String,
    val index: Int,
    var color: Int = 0xFFFFFF,
    var crystalType: CrystalType = CrystalType.NONE,
    var formula: String = "",
    private var hardness: MaterialHardness = MaterialHardness.NORMAL,
    var molar: Double = -1.0,
    var tempBoil: Int = -1,
    var tempMelt: Int = -1,
    var tempSubl: Int = -1,
    var translationKey: String = "hiiragi_material.$name"
) {

    var fluidSupplier: MaterialFluidSupplier? = MaterialFluidSupplier({ MaterialFluid(this) })

    /**
     * a list of alternative Ore Dictionary names: aluminum, chrome, saltpeter, ...
     */
    val oreDictAlt: MutableList<String> = mutableListOf()

    /**
     * a set of shape names that is acceptable to this material
     */
    val validShapes: MutableSet<String> = MaterialType.INTERNAL.toSortedSet()

    companion object {
        @JvmField
        val EMPTY = HiiragiMaterial("empty", -1)

        @JvmField
        val UNKNOWN = formulaOf("?")

        private val gson: Gson = Gson()

        private val pretty: Gson = GsonBuilder().setPrettyPrinting().create()

    }

    fun toJson(isPretty: Boolean): String {

        val root = JsonObject()

        root.addProperty("name", name)
        root.addProperty("index", index)
        root.addProperty("color", color)
        root.addProperty("crystalType", crystalType.toString())
        root.addProperty("formula", formula)
        root.addProperty("hardness", hardness.toString())
        root.addProperty("molar", molar)
        root.addProperty("tempBoil", tempBoil)
        root.addProperty("tempMelt", tempMelt)
        root.addProperty("tempSubl", tempSubl)
        root.addProperty("translationKey", translationKey)

        val oreDictArray = JsonArray()
        oreDictAlt.forEach { oreDictArray.add(it) }
        root.add("oreDictAlt", oreDictArray)

        val validShapesArray = JsonArray()
        validShapes.forEach { validShapesArray.add(it) }
        root.add("validShapes", validShapesArray)

        return if (isPretty) pretty.toJson(root) else gson.toJson(root)

    }

    /**
     * Adds bracket to chemical formula
     *
     * Example:  "H2O" -> "(H2O)"
     */
    fun addBracket(): HiiragiMaterial = copy(formula = "($formula)")

    fun addTooltip(tooltip: MutableList<String>, shape: HiiragiShape) {
        addTooltip(tooltip, shape.getTranslatedName(this), shape.scale)
    }

    fun addTooltip(tooltip: MutableList<String>, name: String, scale: Int) {
        if (isEmpty()) return
        tooltip.add(I18n.format("tips.ragi_materials.property.name", name))
        tooltip.add("§e=== Property ===")
        if (hasFormula())
            tooltip.add(I18n.format("tips.ragi_materials.property.formula", formula))
        if (hasMolar())
            tooltip.add(I18n.format("tips.ragi_materials.property.mol", molar))
        if (scale > 0)
            tooltip.add(I18n.format("tips.ragi_materials.property.scale", scale))
        if (hasTempMelt())
            tooltip.add(I18n.format("tips.ragi_materials.property.melt", tempMelt))
        if (hasTempBoil())
            tooltip.add(I18n.format("tips.ragi_materials.property.boil", tempBoil))
        if (hasTempSubl())
            tooltip.add(I18n.format("tips.ragi_materials.property.subl", tempSubl))
    }

    fun getAllItemStack(): List<ItemStack> =
        HiiragiRegistry.getShapes().flatMap { HiiragiPart(it, this).getAllItemStack() }

    fun getFluid(): Fluid =
        if (FluidRegistry.isFluidRegistered(name)) FluidRegistry.getFluid(name) else MaterialFluid.EMPTY

    fun getHardness(): MaterialHardness = if (isSolid()) hardness else MaterialHardness.FLUID

    /**
     * Converts material name with UCC format
     *
     * Example: "sulfuric_acid" -> "SulfuricAcid"
     */
    fun getOreDictName(): String = name.snakeToUpperCamelCase()

    /**
     * Converts [oreDictAlt] with UCC format
     * @return a new list of Ore Dictionary names
     */
    fun getOreDictNameAlt(): List<String> = oreDictAlt.map { it.snakeToUpperCamelCase() }

    /**
     * Gets the standard state of this material
     * @return [MaterialState.GAS], [MaterialState.LIQUID], [MaterialState.SOLID]
     */

    fun getState(): MaterialState {
        //沸点が有効かつ298 K以下 -> 標準状態で気体
        if (hasTempBoil() && tempBoil <= 298) return MaterialState.GAS
        //融点が有効かつ298以下 -> 標準状態で液体
        if (hasTempMelt() && tempMelt <= 298) return MaterialState.LIQUID
        //それ以外は固体として扱う
        return MaterialState.SOLID
    }

    fun getTranslatedName(): String = I18n.format(translationKey)

    fun hasOreDictAlt(): Boolean = oreDictAlt.isNotEmpty()

    fun hasFluid(): Boolean = FluidRegistry.isFluidRegistered(name)

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil >= 0

    fun hasTempMelt(): Boolean = tempMelt >= 0

    fun hasTempSubl(): Boolean = tempSubl >= 0

    fun isEmpty(): Boolean = this == EMPTY || name == "empty"

    fun isGem(): Boolean = crystalType.isCrystal && !isMetal()

    fun isMetal(): Boolean = crystalType == CrystalType.METAL

    fun isGas(): Boolean = getState() == MaterialState.GAS

    fun isLiquid(): Boolean = getState() == MaterialState.LIQUID

    fun isValidIndex(): Boolean = index > 0

    fun isSolid(): Boolean = getState() == MaterialState.SOLID

    fun setHardness(hardness: MaterialHardness) = also { if (isSolid()) this.hardness = hardness }

    fun toMaterialStack(amount: Int = 144): MaterialStack = MaterialStack(this, amount)

    //    General    //

    override fun toString(): String = "Material:${name}"

}