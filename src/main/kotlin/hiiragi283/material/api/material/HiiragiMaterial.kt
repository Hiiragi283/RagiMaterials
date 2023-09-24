package hiiragi283.material.api.material

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.api.shape.HiiragiShapes
import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import rechellatek.snakeToUpperCamelCase

/**
 * An object which contains several properties of material
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
 * @param formula empty value will be ignored
 * @param molar 0 or less value will be ignored
 * @param tempBoil boiling point with kelvin Temperature, 0 or less will be ignored
 * @param tempMelt melting point with kelvin Temperature, 0 or less will be ignored
 * @param translationKey can be overridden
 */
data class HiiragiMaterial(
    val name: String,
    val index: Int,
    var color: Int = 0xFFFFFF,
    var formula: String = "",
    var molar: Double = 0.0,
    var shapeType: HiiragiShapeType = HiiragiShapeTypes.INTERNAL,
    var tempBoil: Int = 0,
    var tempMelt: Int = 0,
    var translationKey: String = "hiiragi_material.$name"
) {

    val oreDictAlt: MutableList<String> = mutableListOf()
    var fluidBlock: Block? = null
    var fluidSupplier: () -> Fluid? = { MaterialFluid(this) }

    var machineProperty: IMachineProperty? = null
    fun addBracket() = copy(formula = "($formula)")

    fun addTooltip(tooltip: MutableList<String>, shape: HiiragiShape) {
        addTooltip(tooltip, shape.getTranslatedName(this), shape.scale)
    }

    fun addTooltip(tooltip: MutableList<String>, name: String, scale: Int) {
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
    }

    fun createFluid(): Fluid? = fluidSupplier()?.setBlock(fluidBlock)

    fun getAllItemStack(): List<ItemStack> = HiiragiRegistries.SHAPE.getValues()
        .flatMap { shape: HiiragiShape -> HiiragiPart(shape, this).getAllItemStack() }

    fun getFluid(): Fluid? = if (FluidRegistry.isFluidRegistered(name)) FluidRegistry.getFluid(name) else null

    fun getFluids(): List<Fluid> {
        val list: MutableList<Fluid> = listOfNotNull(getFluid()).toMutableList()
        list.addAll(oreDictAlt.mapNotNull(FluidRegistry::getFluid))
        return list
    }

    fun getFluidStack(amount: Int = 1000): FluidStack? = FluidRegistry.getFluidStack(name, amount)

    fun getOreDictName(): String = name.snakeToUpperCamelCase()

    fun getOreDictNameAlt(): List<String> = oreDictAlt.map { it.snakeToUpperCamelCase() }

    fun getPart(shape: HiiragiShape): HiiragiPart = HiiragiPart(shape, this)

    fun getTranslatedName(): String = I18n.format(translationKey)

    fun hasOreDictAlt(): Boolean = oreDictAlt.isNotEmpty()

    fun hasFluid(): Boolean = FluidRegistry.isFluidRegistered(name)

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil > 0

    fun hasTempMelt(): Boolean = tempMelt > 0

    fun isGem(): Boolean = HiiragiShapes.GEM.isValid(this)

    fun isMetal(): Boolean = HiiragiShapes.METAL.isValid(this)

    fun isFluid(): Boolean = isGas() || isLiquid()

    fun isGas(): Boolean = HiiragiShapes.GAS.isValid(this)

    fun isLiquid(): Boolean = HiiragiShapes.LIQUID.isValid(this)

    fun isValidIndex(): Boolean = index >= 0

    fun isSolid(): Boolean = HiiragiShapes.SOLID.isValid(this)

    fun toMaterialStack(amount: Int = 144): MaterialStack = MaterialStack(this, amount)

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        !is HiiragiMaterial -> false
        else -> other.name == this.name
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + index
        result = 31 * result + color
        result = 31 * result + formula.hashCode()
        result = 31 * result + molar.hashCode()
        result = 31 * result + shapeType.hashCode()
        result = 31 * result + tempBoil
        result = 31 * result + tempMelt
        result = 31 * result + translationKey.hashCode()
        return result
    }

    override fun toString(): String = "Material:$name"

    companion object {

        @JvmField
        val UNKNOWN = formulaOf("?")

        @JvmField
        val RUSSELL = materialOf("russell", 0) {
            color = RagiMaterials.COLOR.rgb
            shapeType = HiiragiShapeTypes.WILDCARD
        }

    }

    //    Registration    //

    fun register() {
        HiiragiRegistries.MATERIAL.register(name, this)
        HiiragiRegistries.MATERIAL_INDEX.register(index, this)
    }

}