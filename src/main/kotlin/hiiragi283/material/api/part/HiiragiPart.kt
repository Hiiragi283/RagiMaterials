package hiiragi283.material.api.part

import hiiragi283.material.api.item.MaterialItemConvertible
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.client.resource.language.I18n
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.entity.Entity
import net.minecraft.tag.TagKey
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import kotlin.math.roundToInt

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) : PartConvertible {

    private val replacedId: String = shape.prefixId.replace("@", material.name)
    private val replacedTag: String = shape.prefixTag.replace("@", material.name)
    private val weight: Double =
        if (material.hasMolar() && shape.hasScale()) (material.molar * shape.scale * 10.0).roundToInt() / 10.0 else 0.0

    companion object {

        @JvmField
        val EMPTY = HiiragiPart(HiiragiShape.EMPTY, HiiragiMaterial.EMPTY)

    }

    override fun toString(): String = "${shape.name}:${material.name}"

    fun appendTooltip(tooltip: MutableList<Text>) {
        if (isEmpty()) return
        tooltip.add(LiteralText("§e=== Property ==="))
        tooltip.add(TranslatableText("tips.ragi_materials.property.name", "§b${getName()}"))
        if (material.hasFormula())
            tooltip.add(TranslatableText("tips.ragi_materials.property.formula", "§b${material.formula}"))
        if (weight > 0.0)
            tooltip.add(TranslatableText("tips.ragi_materials.property.mol", "§b${weight}"))
        if (material.hasTempMelt())
            tooltip.add(TranslatableText("tips.ragi_materials.property.melt", "§b${material.tempMelt}"))
        if (material.hasTempBoil())
            tooltip.add(TranslatableText("tips.ragi_materials.property.boil", "§b${material.tempBoil}"))
        if (material.hasTempSubl())
            tooltip.add(TranslatableText("tips.ragi_materials.property.subl", "§b${material.tempSubl}"))
    }

    fun doExplosion(world: World, entity: Entity) {
        if (!material.isActiveToWater) return
        world.createExplosion(
            null,
            entity.x,
            entity.y,
            entity.z,
            shape.scale.toFloat(),
            Explosion.DestructionType.DESTROY
        )
    }

    fun isEmpty(): Boolean = this == EMPTY

    fun isValid(): Boolean = shape.isValid(material)

    fun getId(): Identifier = hiiragiId(replacedId)

    fun getName(): String = I18n.translate(shape.getTranslationKey(), material.getTranslatedName())

    fun getRecipe(output: MaterialItemConvertible): Map<Identifier, CraftingRecipeJsonBuilder> = shape.getRecipe(output)

    fun getCommonId(): Identifier = commonId(replacedTag)

    fun <T : Any> getTagKey(registry: RegistryKey<Registry<T>>): TagKey<T> = TagKey.of(registry, commonId(replacedTag))

    fun getText(): TranslatableText = TranslatableText(shape.getTranslationKey(), material.getTranslatedName())

    fun setMaterial(material: HiiragiMaterial): HiiragiPart = HiiragiPart(shape, material)

    fun setShape(shape: HiiragiShape): HiiragiPart = HiiragiPart(shape, material)

    //    PartConvertible    //

    override fun asPart(): HiiragiPart = this

}