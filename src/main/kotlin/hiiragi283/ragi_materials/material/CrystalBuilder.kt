package hiiragi283.ragi_materials.material

class CrystalBuilder(index: Int, name: String, mapComponents: Map<Any, Int>, var system: String) :
    CompoundBuilder(index, name, MaterialType.CRYSTAL, mapComponents)