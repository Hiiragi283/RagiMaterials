package hiiragi283.ragi_materials.material

class CrystalBuilder(index: Int, name: String, components: Map<Any, Int>, var system: String) :
    CompoundBuilder(index, name, MaterialType.CRYSTALLINE, components)