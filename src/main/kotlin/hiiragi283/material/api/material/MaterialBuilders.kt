@file:JvmName("MaterialBuilders")

package hiiragi283.material.api.material

//    Material    //

fun materialOf(name: String, index: Int, init: HiiragiMaterial.() -> Unit = {}): HiiragiMaterial =
    HiiragiMaterial.of(name, index, MaterialType.ELEMENT, mapOf(), init)

//    Isotope    //

fun isotopeOf(
    name: String,
    index: Int,
    parent: HiiragiMaterial,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.ISOTOPE, mapOf(parent to 1), init)

//    Compound    //

fun compoundOf(
    name: String,
    index: Int,
    components: Map<HiiragiMaterial, Int>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.COMPOUND, components, init)

//    Allotrope    //

fun allotropeOf(
    name: String,
    index: Int,
    parent: HiiragiMaterial,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.ALLOTROPE, mapOf(parent to 1), init)

//    Alloy    //

fun alloyOf(
    name: String,
    index: Int,
    components: Map<HiiragiMaterial, Int>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.ALLOY, components, init)

//    Mixture    //

fun mixtureOf(
    name: String,
    index: Int,
    components: List<HiiragiMaterial>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.MIXTURE, components.associateWith { 1 }, init)

//    Formula String    //

fun formulaOf(formula: String): HiiragiMaterial = HiiragiMaterial.of(
    formula,
    -1,
    MaterialType.FORMULA,
    mapOf()
) { this.formula = formula }

//    Hydrate    //

fun hydrateOf(
    name: String,
    index: Int,
    parent: HiiragiMaterial,
    amountWater: Int,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.HYDRATE, mapOf(parent to amountWater), init)

//    Polymer    //

fun polymerOf(
    name: String,
    index: Int,
    monomar: Map<HiiragiMaterial, Int>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial = HiiragiMaterial.of(name, index, MaterialType.POLYMER, monomar, init)