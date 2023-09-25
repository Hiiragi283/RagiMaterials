package hiiragi283.material.util

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import hiiragi283.material.api.fluid.MaterialFluidBlock
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.materialOf
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import net.minecraftforge.fluids.Fluid

fun deserializeShapeType(jsonElement: JsonElement): HiiragiShapeType {
    val root: JsonObject = jsonElement.asJsonObject
    val name: String? = root.getAsJsonPrimitive("name")?.asString
    val shapesJson: JsonArray? = root.getAsJsonArray("shapes")
    if (name != null && shapesJson != null) {
        val shapes: List<HiiragiShape> = shapesJson.map { it.asString }.mapNotNull(HiiragiRegistries.SHAPE::getValue)
        return HiiragiShapeType(name, shapes)
    }
    return HiiragiShapeTypes.INTERNAL
}

fun deserializeMachineProperty(jsonElement: JsonElement): IMachineProperty {
    val root: JsonObject = jsonElement.asJsonObject
    return IMachineProperty.of(
        IMachineRecipe.Type.valueOf(root.getAsJsonPrimitive("recipe_type").asString),
        root.getAsJsonPrimitive("process_time").asInt,
        root.getAsJsonPrimitive("energy_rate").asInt,
        root.getAsJsonPrimitive("item_slots").asInt,
        root.getAsJsonPrimitive("fluid_slots").asInt,
        root.getAsJsonArray("machine_traits").map { it.asString }.map(MachineTrait::valueOf).toSet()
    )
}

private inline fun <T, reified U : JsonElement> setValue(root: JsonObject, key: String, method: (U) -> T, init: (T) -> Unit) {
    (root.get(key) as? U)?.let(method)?.let(init)
}

fun deserializeMaterial(jsonElement: JsonElement): HiiragiMaterial? {

    val root: JsonObject = jsonElement.asJsonObject

    val jsonName: String = root.getAsJsonPrimitive("name")?.asString ?: return null
    val jsonIndex: Int = root.getAsJsonPrimitive("index")?.asInt ?: return null
    if (jsonName.isNotEmpty() || jsonIndex < 0) return null

    val material: HiiragiMaterial = materialOf(jsonName, jsonIndex)

    setValue(root, "color", JsonPrimitive::getAsInt) { color -> material.color = color }

    setValue(root, "formula", JsonPrimitive::getAsString) { formula -> material.formula = formula }

    setValue(root, "has_fluid", JsonPrimitive::getAsBoolean) { hasFluid ->
        if (!hasFluid) material.fluidSupplier = { null }
    }

    setValue(root, "has_fluid_block", JsonPrimitive::getAsBoolean) { hasFluidBlock ->
        if (hasFluidBlock) material.fluidBlock = { fluid: Fluid -> MaterialFluidBlock(fluid) }
    }

    setValue(root, "machineProperty", JsonElement::getAsJsonObject) { jsonObject ->
        material.machineProperty = deserializeMachineProperty(jsonObject)
    }

    setValue(root, "molar", JsonPrimitive::getAsDouble) { molar -> material.molar = molar }

    setValue(root, "oreDictAlt", JsonElement::getAsJsonArray) { oreDictAlt ->
        oreDictAlt.filterIsInstance<JsonPrimitive>()
            .map { it.asString }
            .forEach(material.oreDictAlt::add)
    }

    setValue(root, "shapeType", JsonPrimitive::getAsJsonObject) { jsonObject ->
        material.shapeType = deserializeShapeType(jsonObject)
    }

    setValue(root, "tempBoil", JsonPrimitive::getAsInt) { tempBoil -> material.tempBoil = tempBoil }
    setValue(root, "tempMelt", JsonPrimitive::getAsInt) { tempMelt -> material.tempMelt = tempMelt }

    return material

}