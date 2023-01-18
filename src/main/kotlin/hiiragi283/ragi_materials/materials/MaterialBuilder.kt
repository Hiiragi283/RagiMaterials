package hiiragi283.ragi_materials.materials

import java.awt.Color

class MaterialBuilder(
    val index: Int,
    val registryName: String,
    val type: MaterialType,
    val color: Color,
    val melting: Int?,
    val boiling: Int,
    val mol: Float
) {
    enum class MaterialType(val hasDust: Boolean, val hasIngot: Boolean, val hasFluid: Boolean, val hasFluidBlock: Boolean) {
        CARBON(true, true, false, false), //昇華する半金属
        DUST(true, false, false, false), //粉末
        GAS(false, false, true, true), //気体
        INTERNAL(false, false, false, false), //内部データ用
        LIQUID(false, false, true, true), //液体
        METAL(true, true, true, false), //金属
        WILDCARD(true, true, true, true) //すべての部品を追加
    }
}