package hiiragi283.material.api.material

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import crafttweaker.api.liquid.ILiquidDefinition
import crafttweaker.api.liquid.ILiquidStack
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.event.MaterialBuildEvent
import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.fluid.MaterialFluidBlock
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.compat.crt.part.IHiiragiPart
import hiiragi283.material.compat.crt.toIItemStacks
import hiiragi283.material.compat.crt.toILiquidDefinition
import hiiragi283.material.compat.crt.toILiquidStack
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
import stanhebben.zenscript.annotations.*
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
@ZenClass("${RMReference.MOD_ID}.material.HiiragiMaterial")
@ZenRegister
data class HiiragiMaterial private constructor(
    @JvmField @ZenProperty val name: String,
    @JvmField @ZenProperty val index: Int,
    @JvmField @ZenProperty val color: Int,
    @JvmField @ZenProperty val formula: String,
    @JvmField val iconSet: MaterialIconSet,
    @JvmField val machineProperty: MachineProperty?,
    @JvmField @ZenProperty val molar: Double,
    @JvmField val shapeType: HiiragiShapeType,
    @JvmField @ZenProperty val tempBoil: Int,
    @JvmField @ZenProperty val tempMelt: Int,
    @JvmField @ZenProperty val translationKey: String
) : HiiragiJsonSerializable {

    companion object {

        @JvmField
        val DEFAULT = materialOf("default", 0) {
            color = RagiMaterials.COLOR.rgb
            shapeType = HiiragiShapeTypes.WILDCARD
        }

        @JvmField
        val UNKNOWN = formulaOf("?")

        @JvmField
        val WILDCARD = build("wildcard", Short.MAX_VALUE.toInt())

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

        fun getMap(): Map<String, HiiragiMaterial> = nameRegistry

        fun getValidIndexMap(): Map<Int, HiiragiMaterial> = indexRegistry

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

    @ZenMethod
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

    fun getFluid(): Fluid? = if (FluidRegistry.isFluidRegistered(name)) FluidRegistry.getFluid(name) else null

    fun getFluidStack(amount: Int = 1000): FluidStack? = FluidRegistry.getFluidStack(name, amount)

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    @ZenMethod
    fun getOreDictName(): String = name.snakeToUpperCamelCase()

    fun getPart(shape: HiiragiShape): HiiragiPart = HiiragiPart(shape, this)

    @ZenGetter("translatedName")
    fun getTranslatedName(): String = I18n.format(translationKey)

    //    Predicate    //

    @ZenMethod
    fun hasFluid(): Boolean = FluidRegistry.isFluidRegistered(name)

    @ZenMethod
    fun hasFormula(): Boolean = formula.isNotEmpty()

    @ZenMethod
    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    @ZenMethod
    fun hasItemStack(shape: HiiragiShape): Boolean = PartDictionary.hasStack(getPart(shape))

    @ZenMethod
    fun hasMolar(): Boolean = molar > 0.0

    @ZenMethod
    fun hasTempBoil(): Boolean = tempBoil > 0

    @ZenMethod
    fun hasTempMelt(): Boolean = tempMelt > 0

    @ZenMethod
    fun isGem(): Boolean = HiiragiShapes.IS_GEM.canCreateMaterialItem(this)

    @ZenMethod
    fun isMetal(): Boolean = HiiragiShapes.IS_METAL.canCreateMaterialItem(this)

    @ZenMethod
    fun isFluid(): Boolean = isGas() || isLiquid()

    @ZenMethod
    fun isGas(): Boolean = HiiragiShapes.GAS.canCreateMaterialItem(this) || tempBoil < 298

    @ZenMethod
    fun isLiquid(): Boolean = HiiragiShapes.LIQUID.canCreateMaterialItem(this) || (tempMelt < 298 && tempBoil >= 298)

    fun isValidIndex(): Boolean = index >= 0

    @ZenMethod
    fun isSolid(): Boolean = HiiragiShapes.SOLID.canCreateMaterialItem(this) || tempMelt >= 298

    fun isRegistered(): Boolean = REGISTRY.contains(name)

    //    Function    //

    @ZenMethod
    fun addAlternativeName(vararg name: String) = also {
        name.forEach { REGISTRY.register(it, this) }
    }

    @ZenMethod
    fun setSmelted(smelted: HiiragiMaterial, count: Int = 1) = also {
        HiiragiRegistries.MATERIAL_SMELTED[this] = smelted to count
    }

    @ZenMethod
    fun setBlockMultiplier(multiplier: Int) = also {
        HiiragiShape.setBlockMultiplier(this, multiplier)
    }

    @ZenMethod
    fun setIngotScale(scale: Int) = also {
        HiiragiShape.setIngotScale(this, scale)
    }

    //    Any    //

    @ZenOperator(OperatorType.EQUALS)
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

        root.add("machineProperty", machineProperty?.getJsonElement())
        root.addProperty("molar", molar)

        root.add("shapeType", shapeType.getJsonElement())
        root.addProperty("tempBoil", tempBoil)
        root.addProperty("tempMelt", tempMelt)

        return root

    }

    //    CraftTweaker    //

    @ZenGetter("liquidDefinition")
    fun getILiquidDefinition(): ILiquidDefinition? = getFluid()?.toILiquidDefinition()

    @ZenMethod
    fun getILiquidStack(amount: Int): ILiquidStack? = getFluidStack(amount)?.toILiquidStack()

    @ZenMethod
    fun getIItemStacks(count: Int): List<IItemStack> = getItemStacks(count).toIItemStacks()

    @ZenMethod("getPart")
    fun getPartCT(shape: HiiragiShape): IHiiragiPart = IHiiragiPart.Impl(getPart(shape))

    //    Builder    //

    @ZenClass("${RMReference.MOD_ID}.material.MaterialBuilder")
    @ZenRegister
    class Builder(
        @JvmField @ZenProperty val name: String,
        @JvmField @ZenProperty val index: Int
    ) {

        @JvmField
        @ZenProperty
        var color: Int = 0xFFFFFF

        @JvmField
        @ZenProperty
        var formula: String = ""

        @JvmField
        @ZenProperty
        var hasBucket: Boolean = true

        @JvmField
        @ZenProperty
        var hasFluid: Boolean = true

        @JvmField
        @ZenProperty
        var hasFluidBlock: Boolean = false

        @JvmField
        var iconSet: MaterialIconSet = HiiragiIconSets.DEFAULT

        @JvmField
        var machineProperty: MachineProperty? = null

        @JvmField
        @ZenProperty
        var molar: Double = 0.0

        @JvmField
        var shapeType: HiiragiShapeType = HiiragiShapeTypes.INTERNAL

        @JvmField
        @ZenProperty
        var tempBoil: Int = 0

        @JvmField
        @ZenProperty
        var tempMelt: Int = 0

        @JvmField
        @ZenProperty
        var translationKey: String = "hiiragi_material.$name"

        fun build(): HiiragiMaterial {
            MinecraftForge.EVENT_BUS.post(MaterialBuildEvent(this))
            return HiiragiMaterial(
                name,
                index,
                color,
                formula,
                iconSet,
                machineProperty,
                molar,
                shapeType,
                tempBoil,
                tempMelt,
                translationKey,
            ).also(::register).also(::registerFluid)
        }

        private fun register(material: HiiragiMaterial) {
            REGISTRY.register(name, material)
            if (!material.isValidIndex()) {
                HiiragiLogger.error("Material: $name has invalid index: $index !!")
                HiiragiLogger.debugInfo("Material: $name is registered!")
                return
            }
            REGISTRY.register(index, material)
            HiiragiLogger.debugInfo("Material: $name is registered!")
        }

        private fun registerFluid(material: HiiragiMaterial) {
            val fluid: Fluid = MaterialFluid(material).takeIf { hasFluid } ?: return
            if (FluidRegistry.isFluidRegistered(fluid.name)) return
            FluidRegistry.registerFluid(fluid)
            if (hasBucket) FluidRegistry.addBucketForFluid(fluid)
            if (hasFluidBlock) {
                MaterialFluidBlock(fluid).run {
                    fluid.block = this
                    (this as? HiiragiEntry.BLOCK)?.register()
                    FluidRegistry.registerFluid(fluid)
                }
            }
        }

    }

}