package hiiragi283.ragi_materials.material.part

import hiiragi283.ragi_materials.material.type.EnumMaterialType

object PartRegistry {

    val map: HashMap<String, MaterialPart> = hashMapOf()

    val BLOCK = MaterialPart("block_material", EnumMaterialType.BLOCK_MATERIAL, 9.0f)

    val CRYSTAL = MaterialPart("crystal", EnumMaterialType.CRYSTAL, 1.0f)

    val DUST = MaterialPart("dust", EnumMaterialType.DUST, 1.0f)

    val DUST_TINY = MaterialPart("dust_tiny", EnumMaterialType.DUST, 0.1f)

    val GEAR = MaterialPart("gear", EnumMaterialType.GEAR, 4.0f)

    val INGOT = MaterialPart("ingot", EnumMaterialType.INGOT, 1.0f)

    val INGOT_HOT = MaterialPart("ingot_hot", EnumMaterialType.INGOT_HOT, 1.0f)

    val NUGGET = MaterialPart("nugget", EnumMaterialType.NUGGET, 0.1f)

    val ORE = MaterialPart("ore", EnumMaterialType.DUMMY, 1.0f)

    val ORE_NETHER = MaterialPart("ore_nether", EnumMaterialType.DUMMY, 1.0f)

    val ORE_END = MaterialPart("ore_end", EnumMaterialType.DUMMY, 1.0f)

    val PLATE = MaterialPart("plate", EnumMaterialType.PLATE, 1.0f)

    val STICK = MaterialPart("stick", EnumMaterialType.STICK, 0.5f)

}