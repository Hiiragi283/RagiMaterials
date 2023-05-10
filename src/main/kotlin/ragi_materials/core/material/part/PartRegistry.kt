package ragi_materials.core.material.part

import ragi_materials.core.material.type.EnumMaterialType

object PartRegistry {

    val list: MutableList<MaterialPart> = mutableListOf()
    val map: HashMap<String, MaterialPart> = hashMapOf()

    val BLOCK = MaterialPart.Builder("block_material").apply {
        prefixOre = "block"
        scale = 9.0f
        type = EnumMaterialType.BLOCK_MATERIAL
    }.build()

    val CRUSHED = MaterialPart.Builder("crushed").apply {
        prefixOre = "crushed"
        type = EnumMaterialType.ORE
    }.build()
    
    val CRYSTAL = MaterialPart.Builder("crystal").apply {
        prefixOre = "gem"
        type = EnumMaterialType.CRYSTAL
    }.build()

    val DUST = MaterialPart.Builder("dust").apply {
        prefixOre = "dust"
        type = EnumMaterialType.DUST
    }.build()

    val DUST_TINY = MaterialPart.Builder("dust_tiny").apply {
        prefixOre = "dustTiny"
        scale = 0.1f
        type = EnumMaterialType.DUST
    }.build()

    val GEAR = MaterialPart.Builder("gear").apply {
        prefixOre = "gear"
        scale = 4.0f
        type = EnumMaterialType.GEAR
    }.build()

    val INGOT = MaterialPart.Builder("ingot").apply {
        prefixOre = "ingot"
        type = EnumMaterialType.INGOT
    }.build()

    /*val INGOT_HOT = MaterialPart.Builder("ingot_hot").apply {
        prefixOre = "ingotHot"
        type = EnumMaterialType.INGOT_HOT
    }.build()*/

    val NUGGET = MaterialPart.Builder("nugget").apply {
        prefixOre = "nugget"
        scale = 0.1f
        type = EnumMaterialType.NUGGET
    }.build()

    val ORE = MaterialPart.Builder("ore").apply {
        prefixOre = "ore"
        scale = 2.0f
        type = EnumMaterialType.ORE
    }.build()

    val PLATE = MaterialPart.Builder("plate").apply {
        prefixOre = "plate"
        type = EnumMaterialType.PLATE
    }.build()

    val PURIFIED = MaterialPart.Builder("purified").apply {
        prefixOre = "crushedPurified"
        type = EnumMaterialType.ORE
    }.build()

    val STICK = MaterialPart.Builder("stick").apply {
        prefixOre = "stick"
        scale = 0.5f
        type = EnumMaterialType.STICK
    }.build()

}