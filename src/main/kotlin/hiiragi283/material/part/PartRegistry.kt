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
            ?.let { RagiMaterials.LOGGER.warn("The part: $name has already registered!") }
    }

    //    Parts    //

    @JvmField
    val DUST = HiiragiPart("dust", 1.0)

    @JvmField
    val DUST_TINY = HiiragiPart("dust_tiny", 0.1)

    @JvmField
    val INGOT = HiiragiPart("ingot", 1.0)

    @JvmField
    val PLATE = HiiragiPart("plate", 1.0)

    @JvmField
    val STICK = HiiragiPart("stick", 0.5)

    fun init() {
        registerPart(DUST)
        registerPart(DUST_TINY)
        registerPart(INGOT)
        registerPart(PLATE)
        registerPart(STICK)
    }

}