package hiiragi283.material.material.element

import hiiragi283.material.material.type.TypeRegistry
import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object ElementRegistry {

    fun load() {}

    private val mapElement: LinkedHashMap<String, RagiElement> = linkedMapOf()

    fun getElement(name: String): RagiElement = mapElement.getOrDefault(name, RagiElement.EMPTY)

    fun getElementAll() = mapElement.values

    fun setElement(material: RagiElement) {
        mapElement.putIfAbsent(material.name, material)
    }

    //    Color    //

    private val colorCarbon = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)
    private val colorSilicon = ColorUtil.mixColor(RagiColor.BLACK to 3, RagiColor.BLUE to 1)

    //    Element    //

    val HYDROGEN = RagiElement("hydrogen", TypeRegistry.GAS, RagiColor.BLUE, "H", 1.0f)
    val DEUTERIUM = RagiElement("deuterium", TypeRegistry.GAS, RagiColor.BLUE, "D", 2.0f)
    val TRITIUM = RagiElement("tritium", TypeRegistry.GAS, RagiColor.BLUE, "T", 3.0f)
    val HELIUM = RagiElement("helium", TypeRegistry.GAS, RagiColor.YELLOW, "He", 4.0f)

    val LITHIUM = RagiElement("lithium", TypeRegistry.METAL, RagiColor.GRAY, "Li", 7.0f)
    val BERYLLIUM = RagiElement("beryllium", TypeRegistry.METAL, RagiColor.DARK_GREEN, "Be", 9.0f)
    val CARBON = RagiElement("carbon", TypeRegistry.INGOT, colorCarbon, "C", 12.0f)
    val NITROGEN = RagiElement("nitrogen", TypeRegistry.GAS, RagiColor.AQUA, "N", 14.0f)
    val OXYGEN = RagiElement("oxygen", TypeRegistry.GAS, RagiColor.WHITE, "O", 16.0f)
    val FLUORINE = RagiElement("fluorine", TypeRegistry.GAS, RagiColor.GREEN, "F", 19.0f)
    val NEON = RagiElement("neon", TypeRegistry.GAS, RagiColor.LIGHT_PURPLE, "Ne", 20.2f)

    val SILICON = RagiElement("silicon", TypeRegistry.INGOT, colorSilicon, "Si", 28.1f)

}