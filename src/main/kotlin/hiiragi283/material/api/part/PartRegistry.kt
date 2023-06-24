package hiiragi283.material.api.part

import hiiragi283.material.common.RagiMaterials

object PartRegistry {

    private val REGISTRY: HashMap<String, HiiragiPart> = hashMapOf()

    @JvmStatic
    fun getParts(): Collection<HiiragiPart> = REGISTRY.values

    @JvmStatic
    fun getPart(name: String) = REGISTRY.getOrDefault(name, HiiragiPart.EMPTY)

    @JvmStatic
    fun registerPart(part: HiiragiPart) {
        //EMPTYを渡された場合はパス
        if (part == HiiragiPart.EMPTY) return
        val name = part.name
        //名前が空の場合はパス
        if (name.isEmpty()) {
            RagiMaterials.LOGGER.warn("The name of $part is empty!")
            return
        }
        val resultName = REGISTRY[name]
        //同じ名前で登録されていた場合，登録せずに警告を表示する
        if (resultName !== null) {
            RagiMaterials.LOGGER.warn("The name of $part was already registered by $resultName!")
            return
        }
        //重複しなかった場合のみ登録を行う
        REGISTRY[name] = part
    }

    //    Parts - Block   //

    @JvmField
    val BLOCK = HiiragiPart.Builder("@_blocks", 9.0).build()

    @JvmField
    val ORE = HiiragiPart.Builder("@_ores", 1.0).build()

    @JvmField
    val ORE_BLOCK = HiiragiPart.Builder("raw_@_blocks", 9.0).build()

    //    Parts - Item   //

    @JvmField
    val GEM = HiiragiPart.Builder("@_gems", 1.0).build()

    @JvmField
    val DUST = HiiragiPart.Builder("@_dusts", 1.0).build()

    @JvmField
    val DUST_TINY = HiiragiPart.Builder("@_tiny_dusts", 0.1).build()

    @JvmField
    val GEAR = HiiragiPart.Builder("@_gears", 4.0).build()

    @JvmField
    val INGOT = HiiragiPart.Builder("@_ingots", 1.0).build()

    @JvmField
    val NUGGET = HiiragiPart.Builder("@_nuggets", 0.1).build()

    @JvmField
    val PLATE = HiiragiPart.Builder("@_plates", 1.0).build()

    @JvmField
    val RAW_ORE = HiiragiPart.Builder("raw_@_ores", 1.0).build()

    @JvmField
    val ROD = HiiragiPart.Builder("@_rods", 0.5).build()

}