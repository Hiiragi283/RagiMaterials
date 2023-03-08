package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.TypeRegistry

class CrystalBuilder(index: Int, name: String, components: Map<MaterialBuilder, Int>, var system: String) : CompoundBuilder(index, name, TypeRegistry.CRYSTALLINE, components)