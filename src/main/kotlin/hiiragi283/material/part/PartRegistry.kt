package hiiragi283.material.part

import hiiragi283.material.RagiMaterials

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
            .let { RagiMaterials.LOGGER.warn("The part: $name has already registered!") }
    }

    //    Parts    //

    @JvmField
    val INGOT = HiiragiPart("ingot", 1.0)

    fun init() {
        registerPart(INGOT)
    }

}