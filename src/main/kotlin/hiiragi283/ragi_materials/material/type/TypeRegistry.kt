package hiiragi283.ragi_materials.material.type

object TypeRegistry {

    val map: HashMap<String, MaterialType> = hashMapOf()

    val CRYSTALLINE = MaterialType.Builder("crystalline")
            .addTypes(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.CRYSTAL,
                    EnumMaterialType.DUST,
                    EnumMaterialType.PLATE
            ).build() //宝石類

    val DUST = MaterialType.Builder("dust")
            .addType(EnumMaterialType.DUST)
            .build() //固体全般

    val DUST_RADIO = MaterialType.Builder("dust")
            .addTypes(
                    EnumMaterialType.DUST,
                    EnumMaterialType.RADIOACTIVE
            ).build() //放射性固体

    val GAS = MaterialType.Builder("gas")
            .addType(EnumMaterialType.LIQUID)
            .build() //気体全般

    val INGOT = MaterialType.Builder("ingot")
            .addTypes(
                    EnumMaterialType.DUST,
                    EnumMaterialType.INGOT,
                    EnumMaterialType.PLATE,
                    EnumMaterialType.STICK
            ).build() //インゴット全般

    val INTERNAL = MaterialType.Builder("internal")
            //.addType(EnumMaterialType.INTERNAL)
            .build() //内部データ

    val LIQUID = MaterialType.Builder("liquid")
            .addType(EnumMaterialType.LIQUID)
            .build()//流体全般 (流体ブロックなし)

    val METAL = MaterialType.Builder("metal")
            .addTypes(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.DUST,
                    EnumMaterialType.GEAR,
                    EnumMaterialType.INGOT,
                    EnumMaterialType.INGOT_HOT,
                    EnumMaterialType.LIQUID,
                    EnumMaterialType.NUGGET,
                    EnumMaterialType.PLATE,
                    EnumMaterialType.STICK
            ).build() //金属全般

    val METAL_RADIO = MaterialType.Builder("dust_radio")
            .addTypes(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.DUST,
                    EnumMaterialType.INGOT,
                    EnumMaterialType.INGOT_HOT,
                    EnumMaterialType.LIQUID,
                    EnumMaterialType.NUGGET,
                    EnumMaterialType.RADIOACTIVE
            ).build() //放射性金属用

    val METALLOID = MaterialType.Builder("metalloid")
            .addTypes(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.DUST,
                    EnumMaterialType.INGOT,
                    EnumMaterialType.PLATE
            ).build() //半金属

    val STONE = MaterialType.Builder("stone")
            .addTypes(
                    EnumMaterialType.DUST,
                    EnumMaterialType.GEAR,
                    EnumMaterialType.PLATE,
                    EnumMaterialType.STICK
            ).build() //石材用

    val WILDCARD = MaterialType.Builder("wildcard")
            .addTypes(EnumMaterialType.values().toList())
            .build() //デバッグ用

    val WOOD = MaterialType.Builder("wood")
            .addTypes(
                    EnumMaterialType.DUST,
                    EnumMaterialType.GEAR,
                    EnumMaterialType.PLATE
            ).build() //木材用
}