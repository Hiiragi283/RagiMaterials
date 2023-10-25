package hiiragi283.material.api.shape

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.util.HiiragiJsonSerializable

class HiiragiShapeType private constructor(val shapes: Set<HiiragiShape>) : HiiragiJsonSerializable {

    //    IJsonSerializable    //

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        val shapesJson = JsonArray()
        shapes.map(HiiragiShape::name).forEach(shapesJson::add)
        root.add("shapes", shapesJson)

        return root

    }

    //    Builder    //

    fun copy(init: Builder.() -> Unit = {}) = Builder()
        .apply { shapes.addAll(this@HiiragiShapeType.shapes) }
        .apply(init)
        .build()

    companion object {

        @JvmStatic
        fun build(init: Builder.() -> Unit = {}): HiiragiShapeType = Builder().apply(init).build()

    }

    class Builder {

        val shapes: MutableSet<HiiragiShape> = mutableSetOf()

        fun build() = HiiragiShapeType(shapes)

    }

}