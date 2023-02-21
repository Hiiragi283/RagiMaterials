package hiiragi283.ragi_materials.material

class MixtureBuilder(index: Int, name: String, type: MaterialType, list: List<Any>): CompoundBuilder(index, name, type, list.associateBy(
    { it } , { 1 })) {

    init {
        color = null
        molar = null
    }
}