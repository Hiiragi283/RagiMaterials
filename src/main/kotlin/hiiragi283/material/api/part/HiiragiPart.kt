package hiiragi283.material.api.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import kotlin.math.roundToInt

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) {

    private val weight: Double = (material.molar * shape.scale * 10.0).roundToInt() / 10.0

    companion object {
        @JvmField
        val EMPTY = HiiragiPart(HiiragiShape.EMPTY, HiiragiMaterial.EMPTY)

    }

    fun isEmpty(): Boolean = this == EMPTY

    fun isValid(): Boolean = !isEmpty() && shape.isValid(material)

    fun getDefaultItemStack(): ItemStack =
        GameRegistry.makeItemStack("${RagiMaterials.MODID}:${shape.name}", material.index, 1, null)

    fun getOreDicts(): List<String> = shape.getOreDicts(material)

    fun getTooltip(tooltip: MutableList<String>) {
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

    override fun toString(): String = "${shape.name}:${material.name}"

}