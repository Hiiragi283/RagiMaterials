package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.RagiColor
import java.awt.Color


enum class EnumMaterials(val index: Int, val registryName: String, val type: MaterialTypes, val color: Color) {

    //0: FOR DEBUG
    DEBUG(0, "hiiragi_tsubasa", MaterialTypes.METAL, RagiColor.RAGI_RED),

    //1~100: Periodic Table
    HYDROGEN(1, "hydrogen", MaterialTypes.GAS, Color.BLUE),
    HELIUM(2, "helium", MaterialTypes.GAS, Color.YELLOW),
    LITHIUM(3, "lithium", MaterialTypes.METAL, RagiColor.CLEAR),
    BERYLLIUM(4, "beryllium", MaterialTypes.METAL, Color.GREEN),
    BORON(5, "boron", MaterialTypes.DUST, Color.GRAY),
    CARBON(6, "carbon", MaterialTypes.METAL, Color.DARK_GRAY),
    NITROGEN(7, "nitrogen", MaterialTypes.GAS, Color.CYAN),
    OXYGEN(8, "oxygen", MaterialTypes.GAS, Color.CYAN),
    FLUORINE(9, "fluorine", MaterialTypes.GAS, Color.GREEN),
    NEON(10, "neon", MaterialTypes.GAS, Color.MAGENTA),
    SODIUM(11, "sodium", MaterialTypes.METAL, RagiColor.CLEAR),
    MAGNESIUM(12, "magnesium", MaterialTypes.METAL, RagiColor.CLEAR),
    ALUMINIUM(13, "aluminium", MaterialTypes.METAL, RagiColor.CLEAR),
    SILICON(14, "silicon", MaterialTypes.METAL, Color.GRAY),
    PHOSPHORUS(15, "phosphorus", MaterialTypes.DUST, Color.RED),
    SULFUR(16, "sulfur", MaterialTypes.DUST, Color.YELLOW),
    CHLORINE(17, "chlorine", MaterialTypes.GAS, Color.YELLOW),
    ARGON(18, "argon", MaterialTypes.GAS, Color.MAGENTA),


    //32768: WILDCARD
    WILDCARD(32768, "wildcard", MaterialTypes.WILDCARD, RagiColor.CLEAR)
}