package hiiragi283.ragi_materials.material.part

import hiiragi283.ragi_materials.material.type.EnumMaterialType

object PartRegistry {

    val map: HashMap<String, MaterialPart> = hashMapOf()

    val BLOCK = MaterialPart.Builder("block_material").apply {
        scale = 9.0f
        type = EnumMaterialType.BLOCK_MATERIAL
    }.build()

    val CRYSTAL = MaterialPart.Builder("crystal").apply {
        type = EnumMaterialType.CRYSTAL
    }.build()

    val DUST = MaterialPart.Builder("dust").apply {
        type = EnumMaterialType.DUST
    }.build()

    val DUST_TINY = MaterialPart.Builder("dust_tiny").apply {
        scale = 0.1f
        type = EnumMaterialType.DUST
    }.build()

    val GEAR = MaterialPart.Builder("gear").apply {
        scale = 4.0f
        type = EnumMaterialType.GEAR
    }.build()

    val INGOT = MaterialPart.Builder("ingot").apply {
        type = EnumMaterialType.INGOT
    }.build()

    val INGOT_HOT = MaterialPart.Builder("ingot_hot").apply {
        type = EnumMaterialType.INGOT_HOT
    }.build()

    val NUGGET = MaterialPart.Builder("nugget").apply {
        scale = 0.1f
        type = EnumMaterialType.NUGGET
    }.build()

    val ORE = MaterialPart.Builder("ore").build()

    val ORE_NETHER = MaterialPart.Builder("ore_nether").build()

    val ORE_END = MaterialPart.Builder("ore_end").build()

    val PLATE = MaterialPart.Builder("plate").apply {
        type = EnumMaterialType.PLATE
    }.build()

    val STICK = MaterialPart.Builder("stick").apply {
        scale = 0.5f
        type = EnumMaterialType.STICK
    }.build()

}