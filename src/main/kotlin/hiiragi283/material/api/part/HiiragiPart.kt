package hiiragi283.material.api.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import net.devtech.arrp.json.recipe.JRecipe
import net.minecraft.util.Identifier

class HiiragiPart private constructor(val name: String, val scale: Double) {

    lateinit var recipes: (HiiragiMaterial) -> Map<Identifier, JRecipe>

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

    fun getTranslationKey(key: () -> String = { "item.${RagiMaterials.MODID}.$name.name" }): String = key()

    class Builder(private val name: String, private val scale: Double) {

        fun build(init: HiiragiPart.() -> Unit = {}): HiiragiPart {
            val part = HiiragiPart(name, scale)
            part.init()
            PartRegistry.registerPart(part)
            return part
        }

    }

}