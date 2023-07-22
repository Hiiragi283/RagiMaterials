package hiiragi283.material.api.shape

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JRecipe
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.util.Identifier

fun shapeOf(
    name: String,
    prefix: String,
    scale: Double,
    blockColor: (HiiragiMaterial) -> BlockColorProvider = { BlockColorProvider { _, _, _, _ -> it.color } },
    itemColor: (HiiragiMaterial) -> ItemColorProvider = { ItemColorProvider { _, _ -> it.color } },
    model: JModel = itemModelLayered { layer0("minecraft:item/iron_ingot") },
    recipes: (HiiragiPart) -> Map<Identifier, JRecipe> = { mapOf() },
    state: JState = JState(),
    type: ShapeType = ShapeType.ITEM
): HiiragiShape = object : HiiragiShape(name, prefix, scale) {

    override fun getBlockColor(material: HiiragiMaterial): BlockColorProvider = blockColor(material)

    override fun getItemColor(material: HiiragiMaterial): ItemColorProvider = itemColor(material)

    override fun getModel(): JModel = model

    override fun getRecipe(part: HiiragiPart): Map<Identifier, JRecipe> = recipes(part)

    override fun getState(): JState = state

    override fun getType(): ShapeType = type

}

abstract class HiiragiShape internal constructor(
    val name: String,
    val prefix: String,
    val scale: Double
) {

    abstract fun getBlockColor(material: HiiragiMaterial): BlockColorProvider

    abstract fun getItemColor(material: HiiragiMaterial): ItemColorProvider

    abstract fun getModel(): JModel

    abstract fun getRecipe(part: HiiragiPart): Map<Identifier, JRecipe>

    abstract fun getState(): JState

    abstract fun getType(): ShapeType

    companion object {
        @JvmField
        val EMPTY = shapeOf("empty", "", 0.0)
    }

    override fun toString(): String = "Part:$name"

    fun getTranslationKey(): String = "shape.$name"

    fun hasScale(): Boolean = scale > 0.0

    fun isEmpty(): Boolean = this.name == "empty"

}