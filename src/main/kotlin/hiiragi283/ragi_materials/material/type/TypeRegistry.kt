package hiiragi283.ragi_materials.material.type

object TypeRegistry {

    val map: HashMap<String, MaterialType> = hashMapOf()

    val CRYSTALLINE = MaterialType("crystalline", listOf(
        EnumMaterialType.BLOCK_CRYSTAL,
        EnumMaterialType.CRYSTAL,
        EnumMaterialType.DUST,
        EnumMaterialType.PLATE
    )) //宝石類

    val DUST = MaterialType("dust", listOf(EnumMaterialType.DUST)) //個体全般

    val GAS = MaterialType("gas", listOf(EnumMaterialType.LIQUID)) //気体全般

    val INGOT = MaterialType("ingot", listOf(
        EnumMaterialType.DUST,
        EnumMaterialType.INGOT,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    )) //インゴット全般

    val INTERNAL = MaterialType("internal", listOf(EnumMaterialType.INTERNAL)) //内部データ

    val LIQUID = MaterialType("liquid", listOf(EnumMaterialType.LIQUID)) //流体全般 (流体ブロックなし)

    val METAL = MaterialType("metal", listOf(
        EnumMaterialType.BLOCK_METAL,
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.INGOT,
        EnumMaterialType.INGOT_HOT,
        EnumMaterialType.LIQUID,
        EnumMaterialType.NUGGET,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    )) //金属全般

    val METALLOID = MaterialType("metalloid", listOf(
        EnumMaterialType.BLOCK_METAL,
        EnumMaterialType.DUST,
        EnumMaterialType.INGOT,
        EnumMaterialType.PLATE
    )) //半金属

    val STONE = MaterialType("stone", listOf(
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    )) //石材用

    val WILDCARD = MaterialType("wildcard", EnumMaterialType.values().toList()) //デバッグ用

    val WOOD = MaterialType("wood", listOf(
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.PLATE
    )) //木材用
}