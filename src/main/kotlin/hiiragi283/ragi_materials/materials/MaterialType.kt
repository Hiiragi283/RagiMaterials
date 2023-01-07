package hiiragi283.ragi_materials.materials

enum class MaterialType(val hasDust: Boolean, val hasIngot: Boolean, val hasFluid: Boolean, val hasFluidBlock: Boolean) {
    DUST(true, false, false, false), //粉末
    GAS(false, false, true, true), //気体
    INTERNAL(false, false, false, false), //内部データ用
    LIQUID(false, false, true, true), //液体
    METAL(true, true, true, false), //金属
    WILDCARD(true, true, true, true) //すべての部品を追加
}