package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.element.ElementBuilder

class SimpleBuilder(val material: ElementBuilder, amount: Int) : CompoundBuilder(material.index, material.name, material.type, mapOf(material to amount)) {

    init {
        setTemp()
    }

    private fun setTemp() {
        for (key in components.keys) {
            if (key is ElementBuilder) {
                if (key.tempMelt !== null) this.tempMelt = key.tempMelt!!
                if (key.tempBoil !== null) this.tempBoil = key.tempBoil!!
                if (key.tempSubl !== null) this.tempSubl = key.tempSubl!!
            }
        }
    }
}