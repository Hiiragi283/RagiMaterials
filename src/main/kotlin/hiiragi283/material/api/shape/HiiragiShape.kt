package hiiragi283.material.api.shape

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JRecipe
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks
import net.minecraft.util.Identifier

fun shapeOf(
    name: String,
    scale: Double,
    prefixId: String,
    prefixTag: String = prefixId,
    blockSettings: AbstractBlock.Settings = AbstractBlock.Settings.copy(Blocks.AIR),
    model: JModel = itemModelLayered { layer0("minecraft:item/iron_ingot") },
    recipes: (HiiragiPart) -> Map<Identifier, JRecipe> = { mapOf() },
    state: JState = JState(),
    type: ShapeType = ShapeType.ITEM
): HiiragiShape = object : HiiragiShape(name, scale, prefixId, prefixTag) {

    override fun getBlockSettings(): AbstractBlock.Settings = blockSettings

    override fun getModel(): JModel = model

    override fun getRecipe(part: HiiragiPart): Map<Identifier, JRecipe> = recipes(part)

    override fun getState(): JState = state

    override fun getType(): ShapeType = type

}

abstract class HiiragiShape internal constructor(
    val name: String,
    val scale: Double,
    val prefixId: String,
    val prefixTag: String = prefixId
) {

    abstract fun getBlockSettings(): AbstractBlock.Settings

    abstract fun getModel(): JModel

    abstract fun getRecipe(part: HiiragiPart): Map<Identifier, JRecipe>

    abstract fun getState(): JState

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