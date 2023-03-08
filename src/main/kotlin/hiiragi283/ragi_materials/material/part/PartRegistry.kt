package hiiragi283.ragi_materials.material.part

object PartRegistry {

    val map: HashMap<String, MaterialPart> = hashMapOf()

    val BLOCK = MaterialPart("block_material", EnumMaterialPart.BLOCK, 9.0f)

    val CRYSTAL = MaterialPart("crystal", EnumMaterialPart.CRYSTAL, 1.0f)

    val DUST = MaterialPart("dust", EnumMaterialPart.DUST, 1.0f)

    val DUST_TINY = MaterialPart("dust_tiny", EnumMaterialPart.DUST, 0.1f)

    val GEAR = MaterialPart("gear", EnumMaterialPart.METAL, 4.0f)

    val INGOT = MaterialPart("ingot", EnumMaterialPart.METAL, 1.0f)

    val INGOT_HOT = MaterialPart("ingot_hot", EnumMaterialPart.METAL, 1.0f)

    val NUGGET = MaterialPart("nugget", EnumMaterialPart.METAL, 0.1f)

    val ORE = MaterialPart("ore", EnumMaterialPart.ORE, 1.0f)

    val ORE_NETHER = MaterialPart("ore_nether", EnumMaterialPart.ORE, 1.0f)

    val ORE_END = MaterialPart("ore_end", EnumMaterialPart.ORE, 1.0f)

    val PLATE = MaterialPart("plate", EnumMaterialPart.METAL, 1.0f)

    val STICK = MaterialPart("stick", EnumMaterialPart.METAL, 0.5f)

}