package hiiragi283.ragi_materials.material

object MaterialType {

    val CRYSTALLINE = listOf(
        EnumMaterialType.BLOCK_CRYSTAL,
        EnumMaterialType.CRYSTAL,
        EnumMaterialType.DUST,
        EnumMaterialType.PLATE
    ) //宝石類

    val DUST = listOf(EnumMaterialType.DUST) //個体全般

    val GAS = listOf(EnumMaterialType.LIQUID) //気体全般

    val INGOT = listOf(
        EnumMaterialType.DUST,
        EnumMaterialType.INGOT,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    ) //インゴット全般

    val INTERNAL = listOf(EnumMaterialType.INTERNAL) //内部データ

    val LIQUID = listOf(EnumMaterialType.LIQUID) //流体全般 (流体ブロックなし)

    val METAL = listOf(
        EnumMaterialType.BLOCK_METAL,
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.INGOT,
        EnumMaterialType.INGOT_HOT,
        EnumMaterialType.LIQUID,
        EnumMaterialType.NUGGET,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    ) //金属全般

    val METALLOID = listOf(
        EnumMaterialType.BLOCK_METAL,
        EnumMaterialType.DUST,
        EnumMaterialType.INGOT,
        EnumMaterialType.PLATE
    ) //半金属

    val STONE = listOf(
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    ) //石材用

    val WILDCARD = listOf(
        EnumMaterialType.BLOCK_CRYSTAL,
        EnumMaterialType.BLOCK_METAL,
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.INGOT,
        EnumMaterialType.LIQUID,
        EnumMaterialType.NUGGET,
        EnumMaterialType.PLATE,
        EnumMaterialType.STICK
    ) //デバッグ用

    val WOOD = listOf(
        EnumMaterialType.DUST,
        EnumMaterialType.GEAR,
        EnumMaterialType.PLATE
    ) //木材専用

    val map = hashMapOf(
        "crystalline" to CRYSTALLINE,
        "dust" to DUST,
        "gas" to GAS,
        "ingot" to INGOT,
        "internal" to INTERNAL,
        "liquid" to LIQUID,
        "metal" to METAL,
        "metalloid" to METALLOID,
        "wildcard" to WILDCARD,
        "wood" to WOOD
    )

    enum class EnumMaterialType {
        BLOCK_CRYSTAL,
        BLOCK_METAL,
        CRYSTAL,
        DUST,
        GEAR,
        INGOT,
        INGOT_HOT,
        INTERNAL,
        LIQUID,
        NUGGET,
        PLATE,
        STICK;
    }
}