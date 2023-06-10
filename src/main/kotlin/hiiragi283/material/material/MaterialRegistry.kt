package hiiragi283.material.material

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.RagiColor

object MaterialRegistry {

    private val REGISTRY: HashMap<String, HiiragiMaterial> = hashMapOf()

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterialFromName(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {
        val name = material.getName()
        //同じ名前で登録されていた場合，登録せずに警告を表示する
        REGISTRY.putIfAbsent(name, material)
            ?.let { RagiMaterials.LOGGER.warn("The material: $name has already registered!") }
    }

    //    Materials    //

    // ELEMENTS //
    // 1st Period
    @JvmField
    val HYDROGEN = HiiragiMaterial.Builder("hydrogen", 1)
        .setColor(RagiColor.BLUE)
        .setFormula("H")
        .setMolar(1.0)
        .setTempBoil(20)
        .setTempMelt(14)
        .build()

    @JvmField
    val HELIUM = HiiragiMaterial.Builder("helium", 2)
        .setColor(RagiColor.YELLOW)
        .setFormula("He")
        .setMolar(4.0)
        .build()

    // 2nd Period
    @JvmField
    val LITHIUM = HiiragiMaterial.Builder("lithium", 3)
        .setColor(RagiColor.GRAY)
        .setFormula("Li")
        .setMolar(6.9)
        .build()

    @JvmField
    val BERYLLIUM = HiiragiMaterial.Builder("beryllium", 4)
        .setColor(RagiColor.DARK_GREEN)
        .setFormula("Be")
        .setMolar(9.0)
        .build()

    @JvmField
    val BORON = HiiragiMaterial.Builder("boron", 5)
        .setColor(RagiColor.GRAY)
        .setFormula("B")
        .setMolar(10.8)
        .build()

    @JvmField
    val CARBON = HiiragiMaterial.Builder("carbon", 6)
        .setColor(RagiColor.BLACK)
        .setFormula("C")
        .setMolar(12.0)
        .build()

    @JvmField
    val NITROGEN = HiiragiMaterial.Builder("nitrogen", 7)
        .setColor(RagiColor.DARK_AQUA)
        .setFormula("N")
        .setMolar(14.0)
        .build()

    @JvmField
    val OXYGEN = HiiragiMaterial.Builder("oxygen", 8)
        .setColor(RagiColor.AQUA)
        .setFormula("O")
        .setMolar(16.0)
        .build()

    @JvmField
    val FLUORINE = HiiragiMaterial.Builder("fluorine", 9)
        .setColor(RagiColor.BLUE)
        .setFormula("F")
        .setMolar(19.0)
        .build()

    @JvmField
    val NEON = HiiragiMaterial.Builder("neon", 10)
        .setColor(RagiColor.GREEN)
        .setFormula("Ne")
        .setMolar(20.2)
        .build()

    // 3rd Period
    @JvmField
    val SODIUM = HiiragiMaterial.Builder("sodium", 11)
        .setColor(RagiColor.BLUE)
        .setFormula("Na")
        .setMolar(23.0)
        .build()

    @JvmField
    val MAGNESIUM = HiiragiMaterial.Builder("magnesium", 12)
        .setColor(RagiColor.GRAY)
        .setFormula("Mg")
        .setMolar(24.3)
        .build()

    @JvmField
    val ALUMINIUM = HiiragiMaterial.Builder("aluminium", 13)
        .setColor(RagiColor.AQUA)
        .setFormula("Al")
        .setMolar(27.0)
        .build()

    @JvmField
    val SILICON = HiiragiMaterial.Builder("silicon", 14)
        .setColor(RagiColor.DARK_GRAY)
        .setFormula("Si")
        .setMolar(28.1)
        .build()

    @JvmField
    val PHOSPHORUS = HiiragiMaterial.Builder("phosphorus", 15)
        .setColor(RagiColor.YELLOW)
        .setFormula("P")
        .setMolar(31.0)
        .build()

    @JvmField
    val SULFUR = HiiragiMaterial.Builder("sulfur", 16)
        .setColor(RagiColor.YELLOW)
        .setFormula("S")
        .setMolar(32.1)
        .build()

    @JvmField
    val CHLORINE = HiiragiMaterial.Builder("chlorine", 17)
        .setColor(RagiColor.YELLOW)
        .setFormula("Cl")
        .setMolar(35.5)
        .build()

    @JvmField
    val ARGON = HiiragiMaterial.Builder("argon", 18)
        .setColor(RagiColor.GREEN)
        .setFormula("Ar")
        .setMolar(40.0)
        .build()


    fun init() {
        // ELEMENTS //
        // 1st Period
        registerMaterial(HYDROGEN)
        registerMaterial(HELIUM)
        // 2nd Period
        registerMaterial(LITHIUM)
        registerMaterial(BERYLLIUM)
        registerMaterial(BORON)
        registerMaterial(CARBON)
        registerMaterial(NITROGEN)
        registerMaterial(OXYGEN)
        registerMaterial(FLUORINE)
        registerMaterial(NEON)
        // 3rd Period
        registerMaterial(SODIUM)
        registerMaterial(MAGNESIUM)
        registerMaterial(ALUMINIUM)
        registerMaterial(SILICON)
        registerMaterial(PHOSPHORUS)
        registerMaterial(SULFUR)
        registerMaterial(CHLORINE)
        registerMaterial(ARGON)
    }

}