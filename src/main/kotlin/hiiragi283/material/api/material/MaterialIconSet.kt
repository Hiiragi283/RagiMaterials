package hiiragi283.material.api.material

import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.util.ResourceLocation

class MaterialIconSet private constructor(val iconSet: Map<HiiragiShape, ResourceLocation>) :
    Map<HiiragiShape, ResourceLocation> by iconSet {

    //    Builder    //

    fun copy(init: Builder.() -> Unit = {}): MaterialIconSet = Builder()
        .apply { iconSet.putAll(this@MaterialIconSet.iconSet) }
        .apply(init)
        .build()

    companion object {

        @JvmStatic
        fun build(init: Builder.() -> Unit = {}): MaterialIconSet = Builder().apply(init).build()

    }

    class Builder {

        val iconSet: MutableMap<HiiragiShape, ResourceLocation> = mutableMapOf(
            addShape(HiiragiShapes.BLOCK),
            addShape(HiiragiShapes.CASING),
            addShape(HiiragiShapes.BOTTLE),
            addShape(HiiragiShapes.DUST),
            addShape(HiiragiShapes.GEAR),
            addShape(HiiragiShapes.GEM),
            addShape(HiiragiShapes.INGOT),
            addShape(HiiragiShapes.NUGGET),
            addShape(HiiragiShapes.PLATE),
            addShape(HiiragiShapes.STICK)
        )

        fun addShape(shape: HiiragiShape): Pair<HiiragiShape, ResourceLocation> = shape to hiiragiLocation(shape.name)

        fun build() = MaterialIconSet(iconSet)

    }


}