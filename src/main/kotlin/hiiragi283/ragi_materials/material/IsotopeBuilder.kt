package hiiragi283.ragi_materials.material

class IsotopeBuilder(index: Int, name: String, material: MaterialBuilder) :
    MaterialBuilder(index, name, material.type) {

    init {
        color = material.color
        melt = material.melt
        boil = material.melt
    }
}