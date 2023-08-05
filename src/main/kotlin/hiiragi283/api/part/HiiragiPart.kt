package hiiragi283.api.part

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.shape.HiiragiShape
import hiiragi283.material.RMReference
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import kotlin.math.roundToInt

/**
 * Pair of [HiiragiShape] and [HiiragiMaterial]
 */
data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) {

    private val weight: Double = (material.molar * shape.scale * 10.0).roundToInt() / 10.0

    companion object {
        @JvmField
        val EMPTY = HiiragiPart(HiiragiShape.EMPTY, HiiragiMaterial.EMPTY)

    }

    /**
     * Adds tips for [tooltip]
     */
    fun addTooltip(tooltip: MutableList<String>) {
        if (isEmpty()) return
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", shape.getTranslatedName(material)))
        if (material.hasFormula())
            tooltip.add(I18n.format("tips.ragi_materials.property.formula", material.formula))
        if (material.hasMolar() && shape.hasScale())
            tooltip.add(I18n.format("tips.ragi_materials.property.mol", weight))
        if (material.hasTempMelt())
            tooltip.add(I18n.format("tips.ragi_materials.property.melt", material.tempMelt))
        if (material.hasTempBoil())
            tooltip.add(I18n.format("tips.ragi_materials.property.boil", material.tempBoil))
        if (material.hasTempSubl())
            tooltip.add(I18n.format("tips.ragi_materials.property.subl", material.tempSubl))
    }

    /**
     * Returns true if this object equals [EMPTY]
     */
    fun isEmpty(): Boolean = this == EMPTY

    /**
     * Returns ItemStack with given shape and material.
     *
     * Will return [ItemStack.EMPTY] if item with [HiiragiShape.name] is not registered
     */
    fun getDefaultItemStack(amount: Int = 1): ItemStack =
        GameRegistry.makeItemStack("${RMReference.MOD_ID}:${shape.name}", material.index, amount, null)

    fun getOreDict(): String = shape.getOreDict(material)

    /**
     * Returns list of All Ore Dictionaries from [shape] and [material]
     */
    fun getOreDicts(): List<String> = shape.getOreDicts(material)

    override fun toString(): String = "${shape.name}:${material.name}"

}