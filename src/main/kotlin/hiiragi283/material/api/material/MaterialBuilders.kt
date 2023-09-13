@file:JvmName("MaterialBuilders")

package hiiragi283.material.api.material

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import hiiragi283.api.fluid.MaterialFluid
import hiiragi283.api.fluid.MaterialFluidSupplier
import hiiragi283.material.util.HiiragiColor
import java.awt.Color
import kotlin.math.roundToInt

//    Material    //

/**
 * Creates new simple material
 * @param init Initialize a property of this material
 * @sample [MaterialElements.HYDROGEN]
 */
fun materialOf(name: String, index: Int, init: HiiragiMaterial.() -> Unit = {}): HiiragiMaterial {
    val material = HiiragiMaterial(name, index)
    material.init()
    return material
}

//    Isotope    //

/**
 * Creates new isotope material
 * @param parent Material from which properties are copied.
 * @param init Initialize a property of this material
 * @sample [MaterialElements.DEUTERIUM]
 */
fun isotopeOf(name: String, index: Int, parent: HiiragiMaterial, init: HiiragiMaterial.() -> Unit): HiiragiMaterial {
    val isotope = HiiragiMaterial(name, index).also {
        it.color = parent.color
        it.crystalType = parent.crystalType
        it.tempBoil = parent.tempBoil
        it.tempMelt = parent.tempMelt
        it.tempSubl = parent.tempSubl
        it.validShapes.addAll(parent.validShapes)
    }
    isotope.init()
    return isotope
}

//    Compound    //

/**
 * Creates new compound material
 * @param components Map of [HiiragiMaterial] and [Int] which represents the composition of this material
 * @param init Initialize a property of this material
 * @sample [MaterialCommon.WATER]
 */
fun compoundOf(
    name: String,
    index: Int,
    components: Map<HiiragiMaterial, Int>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial {
    val compound = HiiragiMaterial(name, index)
    initCompound(compound, components)
    compound.init()
    return compound
}

private fun initCompound(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    initColor(material, components)
    initFormula(material, components)
    initMolar(material, components)
}

//色を自動で生成
private fun initColor(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    material.color = HiiragiColor.mixColor(components.map { Color(it.key.color) to it.value }).rgb
}

private fun initCrystalType(material: HiiragiMaterial) {
    //固相を持たない場合は強制的にNONE
    if (!material.isSolid()) material.crystalType = CrystalType.NONE
}

//化学式を自動で生成
private fun initFormula(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    val builder = StringBuilder()
    for ((material1, weight) in components) {
        //化学式を持たない場合はパス
        if (!material1.hasFormula()) continue
        builder.append(material1.formula)
        //値が1の場合はパス
        if (weight == 1) continue
        //化学式の下付き数字の桁数調整
        val subscript1 = '\u2080' + (weight % 10)
        val subscript10 = '\u2080' + (weight / 10)
        //2桁目が0でない場合，下付き数字を2桁にする
        builder.append(
            StringBuilder().also {
                if (subscript10 != '\u2080') it.append(subscript10)
                it.append(subscript1)
            }
        )
    }
    material.formula = builder.toString()
}

//分子量を自動で生成
private fun initMolar(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var molar = 0.0
    components
        .filter { it.key.hasMolar() }
        .forEach { molar += it.key.molar * it.value }
    molar = (molar * 10.0).roundToInt() / 10.0 //小数点1桁まで
    material.molar = molar
}

//    Alloy    //

fun alloyOf(
    name: String,
    index: Int,
    components: Map<HiiragiMaterial, Int>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial {
    val alloy = HiiragiMaterial(name, index)
    initCompound(alloy, components)
    initAlloy(alloy, components)
    alloy.init()
    return alloy
}

private fun initAlloy(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    initTempBoil(material, components)
    initTempMelt(material, components)
    initTempSubl(material, components)
}


//沸点を自動で生成
private fun initTempBoil(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var boil = 0
    var weight = 0
    components
        .filter { it.key.hasTempBoil() }
        .forEach {
            boil += it.key.tempBoil * it.value
            weight += it.value
        }
    material.tempBoil = if (weight == 0) -1 else boil / weight
}

//融点を自動で生成
private fun initTempMelt(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var melt = 0
    var weight = 0
    components
        .filter { it.key.hasTempMelt() }
        .forEach {
            melt += it.key.tempMelt * it.value
            weight += it.value
        }
    material.tempMelt = if (weight == 0) -1 else melt / weight
}

//昇華点を自動で生成
private fun initTempSubl(material: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var subl = 0
    var weight = 0
    components
        .filter { it.key.hasTempSubl() }
        .forEach {
            subl += it.key.tempSubl * it.value
            weight += it.value
        }
    material.tempSubl = if (weight == 0) -1 else subl / weight
}

//    Mixture    //

/**
 * Creates new mixture material
 * @param components List of [HiiragiMaterial] which represents the composition of this material
 * @param init Initialize a property of this material
 * @sample [MaterialCommon.WOOD]
 */
fun mixtureOf(
    name: String,
    index: Int,
    components: List<HiiragiMaterial>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial {
    val mixture = HiiragiMaterial(name, index)
    initMixture(mixture, components)
    mixture.init()
    return mixture
}

private fun initMixture(material: HiiragiMaterial, components: List<HiiragiMaterial>) {
    initFormula(material, components)
    material.molar = -1.0 //Invalidate molar
}

private fun initFormula(material: HiiragiMaterial, components: List<HiiragiMaterial>) {
    val builder = StringBuilder("(")
    components
        .map { "${it.formula}, " }
        .forEach { builder.append(it) }
    builder.setLength(builder.length - 2) //末尾の", "を取り除く
    builder.append(")")
    material.formula = builder.toString()
}

//    Formula String    //

/**
 * Creates new material only contains chemical formula
 * @sample [HiiragiMaterial.UNKNOWN]
 */
fun formulaOf(formula: String): HiiragiMaterial = HiiragiMaterial.EMPTY.copy(formula = formula)

//    Hydrate    //

/**
 * Creates new mixture material
 * @param parent Dehydrated material
 * @param init Initialize a property of this material
 * @sample [MaterialCommon.BAUXITE]
 */
fun hydrateOf(
    name: String,
    index: Int,
    parent: HiiragiMaterial,
    amountWater: Int,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial {
    val hydrate = HiiragiMaterial(name, index)
    initHydrate(hydrate, parent, amountWater)
    hydrate.init()
    return hydrate
}

fun initHydrate(material: HiiragiMaterial, parent: HiiragiMaterial, amountWater: Int) {
    initFormula(material, parent, amountWater)
    material.molar = parent.molar + amountWater * MaterialCommon.WATER.molar
}

fun initFormula(material: HiiragiMaterial, parent: HiiragiMaterial, amountWater: Int) {
    val builder = StringBuilder(parent.formula)
    builder.append("・")
    builder.append(amountWater)
    builder.append(MaterialCommon.WATER.formula)
    material.formula = builder.toString()
}

//    Polymer    //

/**
 * Creates new polymer material
 * @param monomar Map of [HiiragiMaterial] and [Int] which represents the monomar of this material
 * @param init Initialize a property of this material
 * @sample [MaterialCommon.PLASTIC]
 */
fun polymerOf(
    name: String,
    index: Int,
    monomar: Map<HiiragiMaterial, Int>,
    init: HiiragiMaterial.() -> Unit = {}
): HiiragiMaterial {
    val polymer = HiiragiMaterial(name, index)
    initCompound(polymer, monomar)
    polymer.formula = "(${polymer.formula})n"
    polymer.molar = -1.0 //Invalidate molar
    polymer.init()
    return polymer
}

//    Json    //

fun jsonMaterialOf(json: String): HiiragiMaterial {

    val jsonObject: JsonObject = Gson().fromJson(json, JsonObject::class.java)

    val jsonName: String? = jsonObject.getAsJsonPrimitive("name")?.asString
    val jsonIndex: Int? = jsonObject.getAsJsonPrimitive("index")?.asInt
    if (jsonName == null || jsonIndex == null) return HiiragiMaterial.EMPTY

    val material = materialOf(jsonName, jsonIndex)

    fun <T> setValue(key: String, method: (JsonElement) -> T, init: (T) -> Unit) {
        jsonObject.getAsJsonPrimitive(key)?.let(method)?.let { init(it) }
    }

    setValue("color", JsonElement::getAsInt) { material.color = it }
    setValue("crystalType", JsonElement::getAsString) { material.crystalType = CrystalType.fromString(it) }
    setValue("formula", JsonElement::getAsString) { material.formula = it }
    setValue("hardness", JsonElement::getAsString) { material.setHardness(MaterialHardness.fromString(it)) }
    setValue("molar", JsonElement::getAsDouble) { material.molar = it }
    setValue("tempBoil", JsonElement::getAsInt) { material.tempBoil = it }
    setValue("tempMelt", JsonElement::getAsInt) { material.tempMelt = it }
    setValue("tempSubl", JsonElement::getAsInt) { material.tempSubl = it }
    setValue("translationKey", JsonElement::getAsString) { material.translationKey = it }

    val fluidSupplierJson: JsonObject? = jsonObject.getAsJsonObject("fluidSupplier")
    if (fluidSupplierJson !== null) {
        val fluidJson: JsonPrimitive? = fluidSupplierJson.getAsJsonPrimitive("fluid")
        val blockJson: JsonPrimitive? = fluidSupplierJson.getAsJsonPrimitive("fluidBlock")
        if (fluidJson !== null) {
            material.fluidSupplier = MaterialFluidSupplier(fluidJson.asString, blockJson?.asString ?: "")
        } else {
            material.fluidSupplier =
                MaterialFluidSupplier({ MaterialFluid(material) }, blockJson?.asString ?: "")
        }
    }

    jsonObject.getAsJsonArray("oreDictAlt")?.let { array ->
        array.filterIsInstance<JsonPrimitive>().map { it.asString }.forEach { material.oreDictAlt.add(it) }
    }

    jsonObject.getAsJsonArray("validShapes")?.let { array ->
        array.filterIsInstance<JsonPrimitive>().map { it.asString }.forEach { material.validShapes.add(it) }
    }

    return material

}