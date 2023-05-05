package ragi_materials.core.material.type

object TypeRegistry {

    val map: HashMap<String, MaterialType> = hashMapOf()

    fun getType(name: String): MaterialType = map[name] ?: INTERNAL

    val CRYSTAL = MaterialType.Builder("crystal")
            .addType(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.CRYSTAL,
                    EnumMaterialType.DUST,
                    EnumMaterialType.PLATE
            ).build() //宝石類

    val DUST = MaterialType.Builder("dust")
            .addType(EnumMaterialType.DUST)
            .build() //固体全般

    val FUEL = MaterialType.Builder("fuel")
            .addType(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.CRYSTAL,
                    EnumMaterialType.DUST
            ).build() //化石燃料

    val GAS = MaterialType.Builder("gas")
            .addType(EnumMaterialType.LIQUID)
            .build() //気体全般

    val INGOT = MaterialType.Builder("ingot")
            .addType(
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
            .addType(
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

    val METALLOID = MaterialType.Builder("metalloid")
            .addType(
                    EnumMaterialType.BLOCK_MATERIAL,
                    EnumMaterialType.DUST,
                    EnumMaterialType.INGOT,
                    EnumMaterialType.PLATE
            ).build() //半金属

    val STONE = MaterialType.Builder("stone")
            .addType(
                    EnumMaterialType.DUST,
                    EnumMaterialType.GEAR,
                    EnumMaterialType.PLATE,
                    EnumMaterialType.STICK
            ).build() //石材用

    val WILDCARD = MaterialType.Builder("wildcard")
            .addType(EnumMaterialType.values().toList())
            .build() //デバッグ用

    val WOOD = MaterialType.Builder("wood")
            .addType(
                    EnumMaterialType.DUST,
                    EnumMaterialType.GEAR,
                    EnumMaterialType.PLATE
            ).build() //木材用
}