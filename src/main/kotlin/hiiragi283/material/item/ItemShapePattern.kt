package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.recipe.MaterialCastingRecipe
import hiiragi283.material.util.*
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemShapePattern : HiiragiItem("shape_pattern", 7) {

    @JvmField
    val SHAPE_MAP: Map<Int, HiiragiShape> = mapOf(
        0 to HiiragiShapes.BLOCK,
        1 to HiiragiShapes.CASING,
        2 to HiiragiShapes.GEAR,
        3 to HiiragiShapes.GEM,
        4 to HiiragiShapes.INGOT,
        5 to HiiragiShapes.NUGGET,
        6 to HiiragiShapes.PLATE,
        7 to HiiragiShapes.STICK
    )

    init {
        maxStackSize = 1
    }

    @JvmStatic
    fun getItemStack(shape: HiiragiShape) = itemStack(meta = getMetaFromShape(shape))

    @JvmStatic
    fun getMetaFromShape(shape: HiiragiShape): Int = SHAPE_MAP.reverse()[shape] ?: 0

    @JvmStatic
    fun getShapeFromMeta(index: Int): HiiragiShape = SHAPE_MAP[index % SHAPE_MAP.size]!!

    //    HiiragiEntry    //

    override fun onInit() {
        // 1x Shape + 1x Steel Plate -> 1x Shape Pattern
        SHAPE_MAP.mapNotNull { (index: Int, shape: HiiragiShape) ->
            shape.getItem()?.let { index to it.item() }
        }.forEach { (index: Int, item: Item) ->
            CraftingBuilder(this.itemStack(meta = index))
                .addIngredient(item)
                .addIngredient(HiiragiShapes.PLATE.getOreDict(MaterialCommons.STEEL))
                .build()
        }
        // Metal Casting Recipe
        SHAPE_MAP.values.forEach { shape: HiiragiShape ->
            HiiragiRegistries.MATERIAL_INDEX.getValues()
                .filter(shape::isValid)
                .filter(HiiragiMaterial::isSolid)
                .filter(HiiragiMaterial::hasFluid)
                .map(shape::getPart)
                .forEach { part: HiiragiPart ->
                    IMachineRecipe.register(
                        hiiragiLocation(part.toString()),
                        MaterialCastingRecipe(part)
                    )
                }
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