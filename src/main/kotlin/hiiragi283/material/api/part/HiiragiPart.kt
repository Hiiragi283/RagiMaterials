package hiiragi283.material.api.part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.getItem
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.json.recipe.JResult
import net.devtech.arrp.json.recipe.JStackedResult
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

class HiiragiPart private constructor(
    val name: String,
    val scale: Double,
    var translationKey: String = "part.$name"
) {

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    private val replacedName: (HiiragiMaterial) -> String = { name.replace("@", it.name) }

    fun getName(material: HiiragiMaterial): Text = TranslatableText(translationKey, material.getTranslatedName())

    fun getId(material: HiiragiMaterial): Identifier = hiiragiId(replacedName(material))

    fun getTag(material: HiiragiMaterial): Identifier = commonId(replacedName(material))

    fun getResult(material: HiiragiMaterial, count: Int = 1): JStackedResult =
        JResult.itemStack(getItem(getId(material)), count)

    fun isEmpty(): Boolean = this.name == "empty"

    class Builder(private val name: String, private val scale: Double) {

        fun build(init: HiiragiPart.() -> Unit = {}): HiiragiPart {
            val part = HiiragiPart(name, scale)
            part.init()
            PartRegistry.registerPart(part)
            return part
        }

    }

}