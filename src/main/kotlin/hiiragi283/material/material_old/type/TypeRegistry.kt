package hiiragi283.material.material_old.type

import hiiragi283.material.registry.RagiRegistry

object TypeRegistry {

    private val mapType: HashMap<String, MaterialType> = hashMapOf()

    fun getType(name: String): MaterialType = mapType.getOrDefault(name, INTERNAL)

    fun setType(type: MaterialType): MaterialType? = mapType.putIfAbsent(type.name, type)

    val CRYSTAL = MaterialType("crystal")
        .addParts(
            RagiRegistry.ITEM_PART_BLOCK,
            RagiRegistry.ITEM_PART_CRYSTAL,
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_PLATE
        )

    val DUST = MaterialType("dust")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY
        )

    val FUEL = MaterialType("fuel")
        .addParts(
            RagiRegistry.ITEM_PART_BLOCK,
            RagiRegistry.ITEM_PART_CRYSTAL,
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY
        )

    val GAS = MaterialType("gas")

    val INGOT = MaterialType("ingot")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_INGOT,
            RagiRegistry.ITEM_PART_PLATE,
            RagiRegistry.ITEM_PART_STICK
        )

    val INTERNAL = MaterialType("internal")

    val LIQUID = MaterialType("liquid")

    val METAL = MaterialType("metal")
        .addParts(
            RagiRegistry.ITEM_PART_BLOCK,
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_GEAR,
            RagiRegistry.ITEM_PART_INGOT,
            RagiRegistry.ITEM_PART_NUGGET,
            RagiRegistry.ITEM_PART_PLATE,
            RagiRegistry.ITEM_PART_STICK
        )

    val STONE = MaterialType("stone")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_GEAR,
            RagiRegistry.ITEM_PART_PLATE,
            RagiRegistry.ITEM_PART_STICK
        )

    val WILDCARD = MaterialType("wildcard")
        .addParts(RagiRegistry.getItemMaterials())

    val WOOD = MaterialType("wood")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_GEAR,
            RagiRegistry.ITEM_PART_PLATE
        )

}