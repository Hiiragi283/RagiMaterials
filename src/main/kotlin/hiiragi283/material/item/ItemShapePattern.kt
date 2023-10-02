package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.reverse

object ItemShapePattern : HiiragiItem("shape_pattern", 8) {

    val SHAPE_MAP: Map<Int, HiiragiShape> = mapOf(
        0 to HiiragiShapes.BLOCK,
        1 to HiiragiShapes.CASING,
        2 to HiiragiShapes.DUST,
        3 to HiiragiShapes.GEAR,
        4 to HiiragiShapes.GEM,
        5 to HiiragiShapes.INGOT,
        6 to HiiragiShapes.NUGGET,
        7 to HiiragiShapes.PLATE,
        8 to HiiragiShapes.STICK
    )

    init {
        maxStackSize = 1
    }

    fun getItemStack(shape: HiiragiShape) = itemStack(meta = getMetaFromShape(shape))

    fun getMetaFromShape(shape: HiiragiShape): Int = SHAPE_MAP.reverse()[shape] ?: 0

    fun getShapeFromMeta(index: Int): HiiragiShape = SHAPE_MAP[index % SHAPE_MAP.size]!!

}