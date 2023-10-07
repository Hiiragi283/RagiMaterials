package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.*
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

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

    //    HiiragiEntry    //

    override fun registerRecipe() {
        SHAPE_MAP.mapNotNull { (index: Int, shape: HiiragiShape) ->
            HiiragiRegistries.MATERIAL_ITEM.getValue(shape)?.let { index to it.item() }
        }.forEach { (index: Int, item: Item) ->
            CraftingBuilder(this.itemStack(meta = index))
                .addIngredient(item)
                .addIngredient(HiiragiShapes.PLATE.getOreDict(MaterialCommon.STEEL))
                .build()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        SHAPE_MAP.forEach { (meta: Int, shape: HiiragiShape) ->
            ModelLoader.setCustomModelResourceLocation(
                this,
                meta,
                hiiragiLocation("shape/${shape.name}").toModelLocation()
            )
        }
    }

}