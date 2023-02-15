package hiiragi283.ragi_materials.material

class CrystalBuilder(index: Int, name: String, mapComponents: Map<Any, Int>, var gemType: String) :
    CompoundBuilder(index, name, MaterialType.GEM, mapComponents)