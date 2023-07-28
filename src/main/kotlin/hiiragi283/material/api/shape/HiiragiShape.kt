package hiiragi283.material.api.shape

import hiiragi283.material.api.item.MaterialItemConvertible
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.util.ModelUtil
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.util.Identifier
import pers.solid.brrp.v1.model.ModelJsonBuilder

fun shapeOf(
    name: String,
    scale: Double,
    prefixId: String,
    prefixTag: String = prefixId,
    blockSettings: AbstractBlock.Settings = AbstractBlock.Settings.copy(Blocks.AIR),
    model: ModelJsonBuilder = ModelUtil.createSimple("item/iron_ingot"),
    recipes: (MaterialItemConvertible) -> Map<Identifier, CraftingRecipeJsonBuilder> = { mapOf() },
    state: Identifier = hiiragiId("block/block_metal"),
    type: ShapeType = ShapeType.ITEM
): HiiragiShape = object : HiiragiShape(name, scale, prefixId, prefixTag) {

    override fun getBlockSettings(): AbstractBlock.Settings = blockSettings

    override fun getModel(): ModelJsonBuilder = model

    override fun getRecipe(output: MaterialItemConvertible): Map<Identifier, CraftingRecipeJsonBuilder> =
        recipes(output)

    override fun getState(): Identifier = state

    override fun getType(): ShapeType = type

}

abstract class HiiragiShape internal constructor(
    val name: String,
    val scale: Double,
    val prefixId: String,
    val prefixTag: String = prefixId
) {

    abstract fun getBlockSettings(): AbstractBlock.Settings

    abstract fun getModel(): ModelJsonBuilder

    abstract fun getRecipe(output: MaterialItemConvertible): Map<Identifier, CraftingRecipeJsonBuilder>

    abstract fun getState(): Identifier

    abstract fun getType(): ShapeType

    companion object {
        @JvmField
        val EMPTY = shapeOf("empty", 0.0, "")

        @JvmField
        val WILDCARD = shapeOf("wildcard", 0.0, "")
    }

    override fun toString(): String = "Part:$name"

    fun getTranslationKey(): String = "shape.$name"

    fun hasScale(): Boolean = scale > 0.0

    fun isEmpty(): Boolean = this.name == "empty"

    fun isValid(material: HiiragiMaterial): Boolean = name in material.validShapes

}