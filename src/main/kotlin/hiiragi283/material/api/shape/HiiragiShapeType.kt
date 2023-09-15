package hiiragi283.material.api.shape

data class HiiragiShapeType(val name: String) {

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

}