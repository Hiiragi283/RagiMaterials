package hiiragi283.material.material.type

import hiiragi283.material.registry.RagiRegistry

object TypeRegistry {

    private val mapType: LinkedHashMap<String, MaterialType> = linkedMapOf()

    fun getType(name: String): MaterialType = mapType.getOrDefault(name, INTERNAL)

    fun setType(type: MaterialType): MaterialType? = mapType.putIfAbsent(type.name, type)

    val CRYSTAL = MaterialType.Builder("crystal")
        .addParts(
            RagiRegistry.ITEM_PART_BLOCK,
            RagiRegistry.ITEM_PART_CRYSTAL,
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_PLATE
        ).build()

    val DUST = MaterialType.Builder("dust")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY
        ).build()

    val FUEL = MaterialType.Builder("fuel")
        .addParts(
            RagiRegistry.ITEM_PART_BLOCK,
            RagiRegistry.ITEM_PART_CRYSTAL,
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY
        ).build()

    val GAS = MaterialType.Builder("gas").build()

    val INGOT = MaterialType.Builder("ingot")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_INGOT,
            RagiRegistry.ITEM_PART_PLATE,
            RagiRegistry.ITEM_PART_STICK
        ).build()

    val INTERNAL = MaterialType.Builder("internal").build()

    val LIQUID = MaterialType.Builder("liquid").build()

    val METAL = MaterialType.Builder("metal")
        .addParts(
            RagiRegistry.ITEM_PART_BLOCK,
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_GEAR,
            RagiRegistry.ITEM_PART_INGOT,
            RagiRegistry.ITEM_PART_NUGGET,
            RagiRegistry.ITEM_PART_PLATE,
            RagiRegistry.ITEM_PART_STICK
        ).build()

    val STONE = MaterialType.Builder("stone")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_GEAR,
            RagiRegistry.ITEM_PART_PLATE,
            RagiRegistry.ITEM_PART_STICK
        ).build()

    val WILDCARD = MaterialType.Builder("wildcard")
        .addParts(RagiRegistry.getItemMaterials())
        .build()

    val WOOD = MaterialType.Builder("wood")
        .addParts(
            RagiRegistry.ITEM_PART_DUST,
            RagiRegistry.ITEM_PART_DUST_TINY,
            RagiRegistry.ITEM_PART_GEAR,
            RagiRegistry.ITEM_PART_PLATE
        ).build()

}