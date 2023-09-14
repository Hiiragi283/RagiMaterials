@file:JvmName("MaterialBuilders")

package hiiragi283.material.api.material

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.util.HiiragiColor
import java.awt.Color
import kotlin.math.roundToInt

//    Material    //

fun materialOf(name: String, index: Int, init: HiiragiMaterial.() -> Unit = {}): HiiragiMaterial {
    val material = HiiragiMaterial(name, index)
    material.init()
    return material
}

//    Isotope    //

fun isotopeOf(
    name: String,
    index: Int,
    parent: HiiragiMaterial,
    init: HiiragiMaterial.() -> Unit
): HiiragiMaterial {
    val isotope: HiiragiMaterial = HiiragiMaterial(name, index).also { material ->
        material.color = parent.color
        material.tempBoil = parent.tempBoil
        material.tempMelt = parent.tempMelt
        material.shapeType = parent.shapeType
    }
    isotope.init()
    return isotope
}

//    Compound    //

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

private fun initCompound(compound: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    initColor(compound, components)
    initFormula(compound, components)
    initMolar(compound, components)
}

//色を自動で生成
private fun initColor(compound: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    compound.color = HiiragiColor.mixColor(components.map { Color(it.key.color) to it.value }).rgb
}

//化学式を自動で生成
private fun initFormula(compound: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    val builder = StringBuilder()
    for ((material: HiiragiMaterial, weight: Int) in components) {
        //化学式を持たない場合はパス
        if (!material.hasFormula()) continue
        builder.append(material.formula)
        //値が1の場合はパス
        if (weight == 1) continue
        //化学式の下付き数字の桁数調整
        val subscript1: Char = '\u2080' + (weight % 10)
        val subscript10: Char = '\u2080' + (weight / 10)
        //2桁目が0でない場合，下付き数字を2桁にする
        builder.append(
            StringBuilder().also { builderInner: StringBuilder ->
                if (subscript10 != '\u2080') builderInner.append(subscript10)
                builderInner.append(subscript1)
            }
        )
    }
    compound.formula = builder.toString()
}

//分子量を自動で生成
private fun initMolar(compound: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var molar = 0.0
    components
        .filter { it.key.hasMolar() }
        .forEach { molar += it.key.molar * it.value }
    molar = (molar * 10.0).roundToInt() / 10.0 //小数点1桁まで
    compound.molar = molar
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

private fun initAlloy(alloy: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    initTempBoil(alloy, components)
    initTempMelt(alloy, components)
}


//沸点を自動で生成
private fun initTempBoil(alloy: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var boil = 0
    var sum = 0
    components
        .filter { it.key.hasTempBoil() }
        .forEach { (material: HiiragiMaterial, weight: Int) ->
            boil += material.tempBoil * weight
            sum += weight
        }
    alloy.tempBoil = if (sum == 0) -1 else boil / sum
}

//融点を自動で生成
private fun initTempMelt(alloy: HiiragiMaterial, components: Map<HiiragiMaterial, Int>) {
    var melt = 0
    var sum = 0
    components
        .filter { it.key.hasTempMelt() }
        .forEach { (material: HiiragiMaterial, weight: Int) ->
            melt += material.tempMelt * weight
            sum += weight
        }
    alloy.tempMelt = if (sum == 0) -1 else melt / sum
}

//    Mixture    //

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

private fun initMixture(mixture: HiiragiMaterial, components: List<HiiragiMaterial>) {
    initFormula(mixture, components)
    mixture.molar = -1.0 //Invalidate molar
}

private fun initFormula(mixture: HiiragiMaterial, components: List<HiiragiMaterial>) {
    val builder = StringBuilder("(")
    components
        .map { "${it.formula}, " }
        .forEach { builder.append(it) }
    builder.setLength(builder.length - 2) //末尾の", "を取り除く
    builder.append(")")
    mixture.formula = builder.toString()
}

//    Formula String    //

fun formulaOf(formula: String): HiiragiMaterial = HiiragiMaterial("", -1, formula = formula)

//    Hydrate    //

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

fun initHydrate(hydrate: HiiragiMaterial, parent: HiiragiMaterial, amountWater: Int) {
    initFormula(hydrate, parent, amountWater)
    hydrate.molar = parent.molar + amountWater * MaterialCommon.WATER.molar
}

fun initFormula(hydrate: HiiragiMaterial, parent: HiiragiMaterial, amountWater: Int) {
    hydrate.formula = StringBuilder(parent.formula).also { builder: StringBuilder ->
        builder.append("・")
        builder.append(amountWater)
        builder.append(MaterialCommon.WATER.formula)
    }.toString()
}

//    Polymer    //

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

fun jsonMaterialOf(json: String): HiiragiMaterial? {

    val jsonObject: JsonObject = Gson().fromJson(json, JsonObject::class.java)

    val jsonName: String? = jsonObject.getAsJsonPrimitive("name")?.asString
    val jsonIndex: Int? = jsonObject.getAsJsonPrimitive("index")?.asInt
    if (jsonName == null || jsonIndex == null) return null

    val material: HiiragiMaterial = materialOf(jsonName, jsonIndex)

    fun <T> setValue(key: String, method: (JsonElement) -> T, init: (T) -> Unit) {
        jsonObject.getAsJsonPrimitive(key)?.let(method)?.let(init)
    }

    /*setValue("color", JsonElement::getAsInt) { color: Int -> material.color = color }
    setValue("formula", JsonElement::getAsString) { formula: String -> material.formula = formula }
    setValue("molar", JsonElement::getAsDouble) { molar: Double -> material.molar = molar }
    setValue("tempBoil", JsonElement::getAsInt) { tempBoil: Int -> material.tempBoil = tempBoil }
    setValue("tempMelt", JsonElement::getAsInt) { tempMelt: Int -> material.tempMelt = tempMelt }
    setValue("shapeType", JsonElement::getAsString) { name: String ->
        material.shapeType = HiiragiRegistries.SHAPE_TYPE.getValueOrElse(name, HiiragiShapeTypes.INTERNAL)
    }
    setValue("translationKey", JsonElement::getAsString) { translationKey: String ->
        material.translationKey = translationKey
    }*/

    jsonObject.getAsJsonArray("oreDictAlt")?.let { array ->
        array.filterIsInstance<JsonPrimitive>().map { it.asString }.forEach { material.oreDictAlt.add(it) }
    }

    return material

}