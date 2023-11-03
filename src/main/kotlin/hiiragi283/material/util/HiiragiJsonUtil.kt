package hiiragi283.material.util

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.init.HiiragiShapeTypes
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import java.util.function.Function


fun ItemStack.getJsonElement(): JsonElement {

    val root = JsonObject()

    root.addProperty("item", this.item.registryName.toString())
    root.addProperty("count", this.count)
    root.addProperty("meta", this.metadata)

    return root
}

fun FluidStack.getJsonElement(): JsonElement {

    val root = JsonObject()

    root.addProperty("fluid", this.fluid.name)
    root.addProperty("amount", this.amount)

    return root

}

object HiiragiJsonUtil {

    //    HiiragiShape    //

    fun hiiragiShape(jsonElement: JsonElement): HiiragiShape? {
        val root: JsonObject = jsonElement.asJsonObject
        val name: String = root.getAsJsonPrimitive("name")?.asString ?: return null
        val scale: Int = root.getAsJsonPrimitive("scale")?.asInt ?: 0
        return HiiragiShape.build(name) {
            scaleFunction = Function { scale }
        }
    }

    //    HiiragiMaterial    //

    fun shapeType(jsonElement: JsonElement): HiiragiShapeType {
        val root: JsonObject = jsonElement.asJsonObject
        return root.getAsJsonArray("shapes")?.let { jsonArray ->
            val shapes: List<HiiragiShape> = jsonArray.map { it.asString }
                .mapNotNull(HiiragiShape.REGISTRY::get)
            HiiragiShapeType.build { this.shapes.addAll(shapes) }
        } ?: HiiragiShapeTypes.INTERNAL
    }

    fun machineProperty(jsonElement: JsonElement): MachineProperty {
        val root: JsonObject = jsonElement.asJsonObject
        return MachineProperty.of(
            MachineType.from(root.getAsJsonPrimitive("recipe_type").asString) ?: MachineType.NONE,
            root.getAsJsonPrimitive("process_time").asInt,
            root.getAsJsonPrimitive("energy_rate").asInt,
            root.getAsJsonPrimitive("item_slots").asInt,
            root.getAsJsonPrimitive("fluid_slots").asInt,
            root.getAsJsonArray("machine_traits").map { it.asString }.map(MachineTrait::valueOf).toSet()
        )
    }

    private inline fun <T, reified U : JsonElement> setValue(
        root: JsonObject,
        key: String,
        method: (U) -> T,
        init: (T) -> Unit
    ) {
        (root.get(key) as? U)?.let(method)?.let(init)
    }

    fun hiiragiMaterial(jsonElement: JsonElement): HiiragiMaterial? {

        val root: JsonObject = jsonElement.asJsonObject

        val jsonName: String = root.getAsJsonPrimitive("name").asString
        val jsonIndex: Int = root.getAsJsonPrimitive("index").asInt
        if (jsonName.isEmpty() || jsonIndex < 0) return null

        val builder = HiiragiMaterial.Builder(jsonName, jsonIndex)

        setValue(root, "color", JsonPrimitive::getAsInt) { color -> builder.color = color }

        setValue(root, "formula", JsonPrimitive::getAsString) { formula -> builder.formula = formula }

        setValue(root, "has_fluid", JsonPrimitive::getAsBoolean) { hasFluid -> builder.hasFluid = hasFluid }

        setValue(root, "has_fluid_block", JsonPrimitive::getAsBoolean) { hasFluidBlock ->
            builder.hasFluidBlock = hasFluidBlock
        }

        setValue(root, "machineProperty", JsonObject::getAsJsonObject) { jsonObject ->
            builder.machineProperty = machineProperty(jsonObject)
        }

        setValue(root, "molar", JsonPrimitive::getAsDouble) { molar -> builder.molar = molar }

        setValue(root, "shapeType", JsonObject::getAsJsonObject) { jsonObject ->
            builder.shapeType = shapeType(jsonObject)
        }

        setValue(root, "tempBoil", JsonPrimitive::getAsInt) { tempBoil -> builder.tempBoil = tempBoil }
        setValue(root, "tempMelt", JsonPrimitive::getAsInt) { tempMelt -> builder.tempMelt = tempMelt }

        return builder.build()

    }

    //    IMachineRecipe    //

    fun itemStack(jsonElement: JsonElement): ItemStack? {

        val root: JsonObject = jsonElement.asJsonObject

        val itemLocation: String = root.getAsJsonPrimitive("item")?.asString ?: return null
        val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
        val meta: Int = root.getAsJsonPrimitive("meta")?.asInt ?: 0

        return getEntry<Item>(itemLocation)?.let { ItemStack(it, count, meta) }

    }

    fun fluidStack(jsonElement: JsonElement): FluidStack? {

        val root: JsonObject = jsonElement.asJsonObject

        val fluid: String = root.getAsJsonPrimitive("fluid")?.asString ?: return null
        val amount: Int = root.getAsJsonPrimitive("amount")?.asInt ?: 0

        return FluidRegistry.getFluidStack(fluid, amount)

    }

    fun hiiragiPart(jsonElement: JsonElement): HiiragiPart? {

        val root: JsonObject = jsonElement.asJsonObject

        val shapeString: String = root.getAsJsonPrimitive("shape")?.asString ?: return null
        val materialString: String = root.getAsJsonPrimitive("material")?.asString ?: return null

        val shape: HiiragiShape = HiiragiShape.REGISTRY[shapeString] ?: return null
        val material: HiiragiMaterial = HiiragiMaterial.REGISTRY[materialString] ?: return null

        return HiiragiPart(shape, material)

    }

    fun itemIngredient(jsonElement: JsonElement): ItemIngredient? {

        val root: JsonObject = jsonElement.asJsonObject

        return when {
            root.has("stacks") -> {
                val stacks: List<ItemStack> = root.getAsJsonArray("stacks").mapNotNull(::itemStack)
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Stacks(*stacks.toTypedArray(), count = count)
            }

            root.has("blocks") -> {
                val blocks: List<Block> = root.getAsJsonArray("blocks")
                    .map { it.asString }
                    .mapNotNull { getEntry<Block>(it) }
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Blocks(*blocks.toTypedArray(), count = count)
            }

            root.has("items") -> {
                val items: List<Item> = root.getAsJsonArray("items")
                    .map { it.asString }
                    .mapNotNull { getEntry<Item>(it) }
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Items(*items.toTypedArray(), count = count)
            }

            root.has("ore_dicts") -> {
                val oreDicts: List<String> = root.getAsJsonArray("ore_dicts").map { it.asString }
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.OreDicts(*oreDicts.toTypedArray(), count = count)
            }

            root.has("parts") -> {
                val part: HiiragiPart = hiiragiPart(root.get("parts")) ?: return null
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Parts(part, count)
            }

            root.has("materials") -> {
                val material: HiiragiMaterial = root.getAsJsonPrimitive("materials")?.asString
                    ?.let(HiiragiMaterial.REGISTRY::get) ?: return null
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Materials(material, count)
            }

            root.has("shapes") -> {
                val shape: HiiragiShape = root.getAsJsonPrimitive("shapes")?.asString
                    ?.let(HiiragiShape.REGISTRY::get) ?: return null
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Shapes(shape, count)
            }

            else -> null
        }

    }

    fun fluidIngredient(jsonElement: JsonElement): FluidIngredient? {

        val root: JsonObject = jsonElement.asJsonObject

        val names: List<String> = root.getAsJsonArray("fluids")
            .map(JsonElement::getAsJsonPrimitive)
            .map(JsonPrimitive::getAsString)
        val amount: Int = root.getAsJsonPrimitive("amount")?.asInt ?: 0
        return FluidIngredient(names, amount)

    }

    fun machineRecipe(pair: Pair<JsonElement, String>) {

        val root: JsonObject = pair.first.asJsonObject

        val type: MachineType = root.getAsJsonPrimitive("type")?.asString?.let(MachineType.Companion::from) ?: return
        val traits: Set<MachineTrait> = root.getAsJsonArray("traits")
            .map { it.asJsonPrimitive.asString }
            .mapNotNull(MachineTrait.Companion::from)
            .toSet()
        val inputItems: List<ItemIngredient> = root.getAsJsonArray("input_items")
            .mapNotNull(::itemIngredient)
        val inputFluids: List<FluidIngredient> = root.getAsJsonArray("input_fluids")
            .mapNotNull(::fluidIngredient)
        val outputItems: List<ItemStack> = root.getAsJsonArray("output_items")
            .mapNotNull(::itemStack)
        val outputFluids: List<FluidStack> = root.getAsJsonArray("output_fluids")
            .mapNotNull(::fluidStack)

        IMachineRecipe.register(hiiragiLocation(pair.second), object : IMachineRecipe {
            override fun getInputItems(): List<ItemIngredient> = inputItems
            override fun getInputFluids(): List<FluidIngredient> = inputFluids
            override fun getRequiredTraits(): Set<MachineTrait> = traits
            override fun getRequiredType(): MachineType = type
            override fun getOutputItems(): List<ItemStack> = outputItems
            override fun getOutputFluids(): List<FluidStack> = outputFluids
        })

    }

}