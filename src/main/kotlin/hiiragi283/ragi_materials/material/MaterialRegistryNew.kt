package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.part.MaterialPart

object MaterialRegistryNew {

    val list: MutableList<RagiMaterial> = mutableListOf()
    val mapIndex: LinkedHashMap<Int, RagiMaterial> = linkedMapOf()
    val mapName: LinkedHashMap<String, RagiMaterial> = linkedMapOf()

    val validPair: MutableList<Pair<MaterialPart, RagiMaterial>> = mutableListOf()

}