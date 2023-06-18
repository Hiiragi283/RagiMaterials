package hiiragi283.material.api.part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.commonId
import hiiragi283.material.util.getItem
import hiiragi283.material.util.materialPartId
import net.devtech.arrp.json.recipe.JResult
import net.devtech.arrp.json.recipe.JStackedResult
import net.minecraft.item.Item
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

data class MaterialPart(val material: HiiragiMaterial, val part: HiiragiPart) {

    fun getName(): Text = TranslatableText(part.getTranslationKey(), material.getTranslatedName())

    fun getId() = materialPartId(material, part)

    fun getTag() = commonId("${material.name}_${part.name}")

    fun getResult(count: Int = 1): JStackedResult = JResult.itemStack(toItem(), count)

    fun toItem(): Item = getItem(getId())

}