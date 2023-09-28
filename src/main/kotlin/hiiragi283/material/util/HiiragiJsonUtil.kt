package hiiragi283.material.util

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import hiiragi283.material.api.fluid.MaterialFluidBlock
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.materialOf
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

object HiiragiJsonUtil {

    fun shapeType(jsonElement: JsonElement): HiiragiShapeType {
        val root: JsonObject = jsonElement.asJsonObject
        val name: String? = root.getAsJsonPrimitive("name")?.asString
        val shapesJson: JsonArray? = root.getAsJsonArray("shapes")
        if (name != null && shapesJson != null) {
            val shapes: List<HiiragiShape> =
                shapesJson.map { it.asString }.mapNotNull(HiiragiRegistries.SHAPE::getValue)
            return HiiragiShapeType(name, shapes)
        }
        return HiiragiShapeTypes.INTERNAL
    }

    fun machineProperty(jsonElement: JsonElement): IMachineProperty {
        val root: JsonObject = jsonElement.asJsonObject
        return IMachineProperty.of(
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

        val jsonName: String = root.getAsJsonPrimitive("name")?.asString ?: return null
        val jsonIndex: Int = root.getAsJsonPrimitive("index")?.asInt ?: return null
        if (jsonName.isEmpty() || jsonIndex < 0) return null

        val material: HiiragiMaterial = materialOf(jsonName, jsonIndex)

        setValue(root, "color", JsonPrimitive::getAsInt) { color -> material.color = color }

        setValue(root, "formula", JsonPrimitive::getAsString) { formula -> material.formula = formula }

        setValue(root, "has_fluid", JsonPrimitive::getAsBoolean) { hasFluid ->
            if (!hasFluid) material.fluidSupplier = { null }
        }

        setValue(root, "has_fluid_block", JsonPrimitive::getAsBoolean) { hasFluidBlock ->
            if (hasFluidBlock) material.fluidBlock = { fluid: Fluid -> MaterialFluidBlock(fluid) }
        }

        setValue(root, "machineProperty", JsonObject::getAsJsonObject) { jsonObject ->
            material.machineProperty = machineProperty(jsonObject)
        }

        setValue(root, "molar", JsonPrimitive::getAsDouble) { molar -> material.molar = molar }

        setValue(root, "oreDictAlt", JsonArray::getAsJsonArray) { oreDictAlt ->
            oreDictAlt.map { it.asString }.forEach(material.oreDictAlt::add)
        }

        setValue(root, "shapeType", JsonObject::getAsJsonObject) { jsonObject ->
            material.shapeType = shapeType(jsonObject)
        }

        setValue(root, "tempBoil", JsonPrimitive::getAsInt) { tempBoil -> material.tempBoil = tempBoil }
        setValue(root, "tempMelt", JsonPrimitive::getAsInt) { tempMelt -> material.tempMelt = tempMelt }

        return material

    }

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

        val shape: HiiragiShape = HiiragiRegistries.SHAPE.getValue(shapeString) ?: return null
        val material: HiiragiMaterial = HiiragiRegistries.MATERIAL.getValue(materialString) ?: return null

        return HiiragiPart(shape, material)

    }

    fun hiiragiIngredient(jsonElement: JsonElement): ItemIngredient? {

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
                val shape: HiiragiShape = root.getAsJsonPrimitive("shape")?.asString
                    ?.let { HiiragiRegistries.SHAPE.getValue(it) } ?: return null
                val material: HiiragiMaterial = root.getAsJsonPrimitive("material")?.asString
                    ?.let { HiiragiRegistries.MATERIAL.getValue(it) } ?: return null
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Parts(shape, material, count)
            }

            root.has("materials") -> {
                val material: HiiragiMaterial = root.getAsJsonPrimitive("material")?.asString
                    ?.let { HiiragiRegistries.MATERIAL.getValue(it) } ?: return null
                val count: Int = root.getAsJsonPrimitive("count")?.asInt ?: 1
                ItemIngredient.Materials(material, count)
            }

            else -> null
        }

    }

}