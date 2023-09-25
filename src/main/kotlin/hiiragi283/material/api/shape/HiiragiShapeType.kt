package hiiragi283.material.api.shape

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.util.IJsonSerializable

data class HiiragiShapeType(val name: String) : IJsonSerializable {

    val shapes: Set<HiiragiShape>
        get() = shapesInternal

    private val shapesInternal: MutableSet<HiiragiShape> = mutableSetOf()

    constructor(name: String, shapes: Collection<HiiragiShape>) : this(name) {
        this.shapesInternal.addAll(shapes)
    }

    fun child(name: String, shapes: Collection<HiiragiShape> = listOf()): HiiragiShapeType {
        return HiiragiShapeType(name, this.shapes)
            .also { shapeType: HiiragiShapeType -> shapeType.shapesInternal.addAll(shapes) }
    }

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        !is HiiragiShapeType -> false
        else -> other.name == this.name
    }

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = "ShapeType:$name"

    //    IJsonSerializable    //

    override fun getSerializableElement(): JsonElement {

        val root = JsonObject()

        root.addProperty("name", name)

        val shapesJson = JsonArray()
        shapes.map(HiiragiShape::name).forEach(shapesJson::add)
        root.add("shapes", shapesJson)

        return root

    }

    override fun fromJson(json: JsonElement) {

    }

}