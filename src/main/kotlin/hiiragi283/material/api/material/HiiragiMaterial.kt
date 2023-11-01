package hiiragi283.material.api.material

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.event.MaterialBuiltEvent
import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.init.HiiragiIconSets
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapeTypes
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.HiiragiLogger
import hiiragi283.material.util.getTileImplemented
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import rechellatek.snakeToUpperCamelCase
import java.util.function.Function

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
 * @param shapeType determine the type of this material
 * @param tempBoil boiling point with kelvin Temperature, 0 or less will be ignored
 * @param tempMelt melting point with kelvin Temperature, 0 or less will be ignored
 * @param translationKey can be overridden
 */

@Suppress("DataClassPrivateConstructor")
data class HiiragiMaterial private constructor(
    val name: String,
    val index: Int,
    val color: Int,
    private val fluidSupplier: MaterialFluidSupplier,
    val formula: String,
    val iconSet: MaterialIconSet,
    val machineProperty: MachineProperty?,
    val molar: Double,
    private val oreDictAlt: Set<String>,
    val shapeType: HiiragiShapeType,
    val tempBoil: Int,
    val tempMelt: Int,
    val translationKey: String
) : HiiragiJsonSerializable {

    companion object {

        @JvmField
        val UNKNOWN = formulaOf("?")

        @JvmField
        val WILDCARD = build("wildcard", Short.MAX_VALUE.toInt())

        @JvmField
        val RUSSELL = materialOf("russell", 0) {
            color = RagiMaterials.COLOR.rgb
            shapeType = HiiragiShapeTypes.WILDCARD
        }

        @JvmField
        val BLOCK_COLOR: IBlockColor = IBlockColor { _: IBlockState, world: IBlockAccess?, pos: BlockPos?, _: Int ->
            getTileImplemented<PartConvertible.TILE>(world, pos)?.material?.color ?: -1
        }

        @JvmField
        val ITEM_COLOR: IItemColor = IItemColor { stack: ItemStack, _: Int ->
            REGISTRY[stack.metadata]?.color ?: -1
        }

        @JvmStatic
        fun build(
            name: String,
            index: Int,
            type: MaterialType = MaterialType.EMPTY,
            component: Map<HiiragiMaterial, Int> = mapOf(),
            init: Builder.() -> Unit = {}
        ): HiiragiMaterial = Builder(name, index)
            .apply { type.preInit(this, component) }
            .apply(init)
            .build()
            .apply { type.postInit(this, component) }

    }

    //    Registry    //

    fun register() {
        REGISTRY.register(name, this)
        oreDictAlt.forEach { nameAlt: String ->
            REGISTRY.register(nameAlt, this)
        }
        if (!isValidIndex()) {
            HiiragiLogger.error("$this has invalid index: $index !!")
            return
        }
        REGISTRY.register(index, this)
    }

    object REGISTRY {

        private var isLocked: Boolean = false

        private val nameRegistry: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()
        private val indexRegistry: LinkedHashMap<Int, HiiragiMaterial> = linkedMapOf()

        internal fun init() {

            val event = MaterialRegistryEvent()
            MinecraftForge.EVENT_BUS.post(event)

            val nameSorted: List<Pair<String, HiiragiMaterial>> = nameRegistry.toList()
                .sortedBy { (name: String, _: HiiragiMaterial) -> name }
            nameRegistry.clear()
            nameRegistry.putAll(nameSorted)

            val indexSorted: List<Pair<Int, HiiragiMaterial>> = indexRegistry.toList()
                .sortedBy { (index: Int, _: HiiragiMaterial) -> index }
            indexRegistry.clear()
            indexRegistry.putAll(indexSorted)

            isLocked = true

        }

        operator fun get(name: String): HiiragiMaterial? = nameRegistry[name]

        operator fun get(index: Int): HiiragiMaterial? = indexRegistry[index]

        fun getValues(): Collection<HiiragiMaterial> = nameRegistry.values

        fun getValidIndexValues(): Collection<HiiragiMaterial> = indexRegistry.values

        @Synchronized
        internal fun register(name: String, material: HiiragiMaterial) = when {
            isLocked -> throw IllegalStateException("[Material] This registry is locked!")
            contains(name) -> throw IllegalStateException("[Material] The key: $name is already registered!")
            else -> nameRegistry[name] = material
        }

        @Synchronized
        internal fun register(index: Int, material: HiiragiMaterial) = when {
            isLocked -> throw IllegalStateException("[Material] This registry is locked!")
            contains(index) -> throw IllegalStateException("[Material] The key: $index is already registered!")
            else -> indexRegistry[index] = material
        }

        operator fun contains(name: String): Boolean = nameRegistry.contains(name)

        operator fun contains(index: Int): Boolean = indexRegistry.contains(index)

    }

    //    Conversion    //

    fun addBracket(function: Function<String, String> = Function { "($it)" }) = copy(formula = function.apply(formula))

    fun addTooltip(tooltip: MutableList<String>, name: String = getTranslatedName(), scale: Int = 0) {
        tooltip.add("§e=== Material Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", name))
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

    fun createFluid() {
        fluidSupplier.register(this)
    }

    fun getFluid(): Fluid? = if (FluidRegistry.isFluidRegistered(name)) FluidRegistry.getFluid(name) else null

    fun getFluids(): List<Fluid> {
        val list: MutableList<Fluid> = listOfNotNull(getFluid()).toMutableList()
        list.addAll(oreDictAlt.mapNotNull(FluidRegistry::getFluid))
        return list
    }

    fun getFluidStack(amount: Int = 1000): FluidStack? = FluidRegistry.getFluidStack(name, amount)

    fun getFluidStacks(amount: Int = 1000): List<FluidStack> = getFluids().map { FluidStack(it, amount) }

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDictName(): String = name.snakeToUpperCamelCase()

    fun getPart(shape: HiiragiShape): HiiragiPart = HiiragiPart(shape, this)

    fun getTranslatedName(): String = I18n.format(translationKey)

    //    Predicate    //

    fun hasFluid(): Boolean = FluidRegistry.isFluidRegistered(name)

    fun hasFormula(): Boolean = formula.isNotEmpty()

    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    fun hasItemStack(shape: HiiragiShape): Boolean = PartDictionary.hasStack(getPart(shape))

    fun hasMolar(): Boolean = molar > 0.0

    fun hasTempBoil(): Boolean = tempBoil > 0

    fun hasTempMelt(): Boolean = tempMelt > 0

    fun isGem(): Boolean = HiiragiShapes.IS_GEM.canCreateMaterialItem(this)

    fun isMetal(): Boolean = HiiragiShapes.IS_METAL.canCreateMaterialItem(this)

    fun isFluid(): Boolean = isGas() || isLiquid()

    fun isGas(): Boolean = HiiragiShapes.GAS.canCreateMaterialItem(this) || tempBoil < 298

    fun isLiquid(): Boolean = HiiragiShapes.LIQUID.canCreateMaterialItem(this) || (tempMelt < 298 && tempBoil >= 298)

    fun isValidIndex(): Boolean = index >= 0

    fun isSolid(): Boolean = HiiragiShapes.SOLID.canCreateMaterialItem(this) || tempMelt >= 298

    fun isRegistered(): Boolean = REGISTRY.contains(name)

    //    Setter    //

    fun setSmelted(smelted: HiiragiMaterial, count: Int = 1) = also {
        HiiragiRegistries.MATERIAL_SMELTED[this] = smelted to count
    }

    fun setBlockScale(scale: Int) = also {
        HiiragiShapes.BLOCK.setScale(this, scale)
    }

    fun setIngotScale(scale: Int) = also {
        HiiragiShapes.INGOT.setScale(this, scale)
    }

    fun setNuggetScale(scale: Int) = also {
        HiiragiShapes.NUGGET.setScale(this, scale)
    }

    //    Any    //

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        !is HiiragiMaterial -> false
        else -> other.name == this.name
    }

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = "Material:$name"

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        root.addProperty("name", name)
        root.addProperty("index", index)

        root.addProperty("color", color)
        root.addProperty("formula", formula)

        val hasFluid: Boolean = fluidSupplier.hasFluid
        if (hasFluid) {
            root.addProperty("has_fluid", true)
            if (fluidSupplier.hasBucket) {
                root.addProperty("has_fluid_block", true)
            }
        } else {
            root.addProperty("has_fluid", false)
            root.addProperty("has_fluid_block", false)
        }

        root.add("machineProperty", machineProperty?.getJsonElement())
        root.addProperty("molar", molar)

        val oreDictAltJson = JsonArray()
        oreDictAlt.forEach(oreDictAltJson::add)
        root.add("oreDictAlt", oreDictAltJson)

        root.add("shapeType", shapeType.getJsonElement())
        root.addProperty("tempBoil", tempBoil)
        root.addProperty("tempMelt", tempMelt)

        return root

    }

    //    Builder    //

    class Builder(val name: String, val index: Int) {

        val oreDictAlt: MutableSet<String> = mutableSetOf()
        var color: Int = 0xFFFFFF
        var formula: String = ""
        var hasBucket: Boolean = true
        var hasFluid: Boolean = true
        var hasFluidBlock: Boolean = false
        var iconSet: MaterialIconSet = HiiragiIconSets.DEFAULT
        var machineProperty: MachineProperty? = null
        var molar: Double = 0.0
        var shapeType: HiiragiShapeType = HiiragiShapeTypes.INTERNAL
        var tempBoil: Int = 0
        var tempMelt: Int = 0
        var translationKey: String = "hiiragi_material.$name"

        fun build(): HiiragiMaterial {
            MinecraftForge.EVENT_BUS.post(MaterialBuiltEvent(this))
            return HiiragiMaterial(
                name,
                index,
                color,
                MaterialFluidSupplier(hasFluid, hasFluidBlock, hasBucket),
                formula,
                iconSet,
                machineProperty,
                molar,
                oreDictAlt,
                shapeType,
                tempBoil,
                tempMelt,
                translationKey,
            )
        }

    }

}