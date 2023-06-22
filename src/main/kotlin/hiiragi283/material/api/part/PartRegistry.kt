package hiiragi283.material.api.part

import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.addTag
import hiiragi283.material.common.util.get3x3
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JRecipe

object PartRegistry {

    private val REGISTRY: HashMap<String, HiiragiPart> = hashMapOf()

    @JvmStatic
    fun getParts(): Collection<HiiragiPart> = REGISTRY.values

    @JvmStatic
    fun getPart(name: String) = REGISTRY.getOrDefault(name, HiiragiPart.EMPTY)

    @JvmStatic
    fun registerPart(part: HiiragiPart) {
        val name = part.name
        //同じ名前で登録されていた場合，登録せずに警告を表示する
        REGISTRY.putIfAbsent(name, part)
            ?.let { RagiMaterials.LOGGER.warn("The part: $name has already registered!") }
    }

    //    Parts    //

    @JvmField
    val BLOCK = HiiragiPart.Builder("block", 9.0).build {
        recipes = {
            val materialPart = MaterialPart(it, this)
            mapOf(
                materialPart.getId() to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys()
                        .addTag("A", "c:iron_ingots"),
                    materialPart.getResult()
                )
            )
        }
    }

    @JvmField
    val BOTTLE = HiiragiPart.Builder("bottle", 1.0).build()

    @JvmField
    val GEM = HiiragiPart.Builder("gem", 1.0).build()

    @JvmField
    val DUST = HiiragiPart.Builder("dust", 1.0).build()

    @JvmField
    val DUST_TINY = HiiragiPart.Builder("dust_tiny", 0.1).build()

    @JvmField
    val GEAR = HiiragiPart.Builder("gear", 4.0).build()

    @JvmField
    val INGOT = HiiragiPart.Builder("ingot", 1.0).build()

    @JvmField
    val NUGGET = HiiragiPart.Builder("nugget", 0.1).build()

    @JvmField
    val PLATE = HiiragiPart.Builder("plate", 1.0).build()

    @JvmField
    val STICK = HiiragiPart.Builder("stick", 0.5).build()

}