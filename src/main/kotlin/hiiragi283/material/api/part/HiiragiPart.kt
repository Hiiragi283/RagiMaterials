package hiiragi283.material.api.part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.RagiResourcePack
import hiiragi283.material.common.util.LangType
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.hiiragiId
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.devtech.arrp.json.recipe.JStackedResult
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

class HiiragiPart private constructor(
    val name: String,
    val scale: Double,
    var model: JModel = itemModelLayered { layer0("minecraft:item/iron_ingot") },
    var predicate: (HiiragiMaterial) -> Boolean = { true },
    var recipes: (HiiragiMaterial) -> Map<Identifier, JRecipe> = { mapOf() },
    var state: JState = JState(),
    var translationKey: String = "part.$name",
    var type: PartType = PartType.ITEM
) {

    private val replacedName: (HiiragiMaterial) -> String = { name.replace("@", it.name) }

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    override fun toString(): String = "Part:$name"

    fun getName(material: HiiragiMaterial): Text = TranslatableText(translationKey, material.getTranslatedName())

    fun getId(material: HiiragiMaterial): Identifier = hiiragiId(replacedName(material))

    fun getTag(material: HiiragiMaterial): Identifier = commonId(replacedName(material))

    fun getResult(material: HiiragiMaterial, count: Int = 1): JStackedResult =
        JResult.stackedResult(getId(material).toString(), count)

    fun isEmpty(): Boolean = this.name == "empty"

    class Builder(private val name: String, private val scale: Double) {

        //    Translation    //

        private val translation: MutableMap<LangType, String> = mutableMapOf()

        fun addTranslation(lang: LangType, name: String) = also {
            translation[lang] = name
        }

        fun build(init: HiiragiPart.() -> Unit = {}): HiiragiPart {
            val part = HiiragiPart(name, scale)
            part.init()
            translation.forEach {
                RagiResourcePack.addTranslation(it.key) {
                    this.entry(part.translationKey, it.value)
                }
            }
            return part
        }

    }

}