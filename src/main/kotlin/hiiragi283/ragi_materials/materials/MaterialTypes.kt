package hiiragi283.ragi_materials.materials

enum class MaterialTypes(val hasDust: Boolean, val hasIngot: Boolean, val hasFluid: Boolean, val hasFluidBlock: Boolean) {
    DUST(true, false, false, false),
    GAS(false, false, true, true),
    LIQUID(false, false, true, true),
    METAL(true, true, true, false),
    WILDCARD(true, true, true, true)
}