package hiiragi283.ragi_materials.material

class IsotopeBuilder(index: Int, name: String, material: MaterialBuilder): MaterialBuilder(index, name, material.type) {

    init {
        setColor(material.getColor())
        setTempMelt(material.getTempMelt())
        setTempBoil(material.getTempBoil())
    }
}