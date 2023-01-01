package hiiragi283.ragi_materials.main.materials

import hiiragi283.ragi_lib.main.util.RagiColor
import java.awt.Color


enum class EnumMaterials(val index: Int, val registryName: String, val type: MaterialTypes, val color: Color) {

    //0: FOR DEBUG
    DEBUG(0, "hiiragi_tsubasa", MaterialTypes.METAL, RagiColor.RAGI_RED),
    //1~100: Periodic Table
    HYDROGEN(1, "hydrogen", MaterialTypes.GAS, Color.BLUE),
    HELIUM(2, "helium", MaterialTypes.GAS, Color.YELLOW),
    BERYLLIUM(3, "beryllium", MaterialTypes.METAL, Color.GREEN),
    LITHIUM(4, "lithium", MaterialTypes.METAL, RagiColor.CLEAR),

    //32768: WILDCARD
    WILDCARD(32768, "wildcard", MaterialTypes.WILDCARD, RagiColor.CLEAR)
}