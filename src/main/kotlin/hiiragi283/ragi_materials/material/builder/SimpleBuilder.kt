package hiiragi283.ragi_materials.material.builder

class SimpleBuilder(val material: ElementBuilder, amount: Int) : CompoundBuilder(material.index, material.name, material.type, mapOf(material to amount)) {

    init {
        initTemp()
    }

    private fun initTemp() {
        val pair = components.toList()[0]
        val material = pair.first
        if (material is ElementBuilder) {
            material.tempMelt?.let { this.tempMelt = it }
            material.tempBoil?.let { this.tempBoil = it }
            material.tempSubl?.let { this.tempSubl = it }
        }
    }
}