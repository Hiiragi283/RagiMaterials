package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.element.ElementBuilder

class SimpleBuilder(val material: ElementBuilder, amount: Int) : CompoundBuilder(material.index, material.name, material.type, mapOf(material to amount)) {

    init {
        setTemp()
    }

    private fun setTemp() {
        for (key in components.keys) {
            if (key is ElementBuilder) {
                key.tempMelt?.let { this.tempMelt = it }
                key.tempBoil?.let { this.tempBoil = it }
                key.tempSubl?.let { this.tempSubl = it }
            }
        }
    }
}