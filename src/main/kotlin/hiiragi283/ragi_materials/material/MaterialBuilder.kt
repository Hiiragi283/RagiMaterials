package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import java.awt.Color

open class MaterialBuilder(open val index: Int, open val name: String, open val type: MaterialType) {

    //private変数の宣言
    open var burnTime = -1
    open var color: Color? = null
    open var decayed: MaterialBuilder? = null
    open var formula: String? = null
    open var hasBracket = false
    open var molar: Float? = null
    open var oredictAlt: String? = null
    open var tempBoil: Int? = null
    open var tempMelt: Int? = null
    open var tempSubl: Int? = null
    open var hasOre = false

    //化学式に()をつけるメソッド
    fun addBracket(): MaterialBuilder = also { it.hasBracket = true }

    //EnumMaterialもしくはindexから液体を取得するメソッド
    fun getFluid(): Fluid? {
        val fluid = FluidRegistry.getFluid(this.name)
        return if (fluid !== null) fluid else null
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.name.snakeToUpperCamelCase()
    }

    //燃焼時間を設定するメソッド
    fun setBurnTime(time: Int): MaterialBuilder = also { it.burnTime = time }

    //色を設定するメソッド
    fun setColor(color: Color?): MaterialBuilder = also { it.color = color }

    //崩壊後の素材を設定するメソッド
    fun setDecayed(material: MaterialBuilder): MaterialBuilder = also { it.decayed = material }

    //化学式を設定するメソッド
    fun setFormula(formula: String?): MaterialBuilder = also { it.formula = formula }

    //モル質量を設定するメソッド
    fun setMolarMass(molar: Float?): MaterialBuilder = also { it.molar = molar }

    //鉱石を追加するメソッド
    fun setOre(): MaterialBuilder = also { it.hasOre = true }

    //モル質量を設定するメソッド
    fun setOreDictAlt(oredict: String?): MaterialBuilder = also { it.oredictAlt = oredict }

    //融点を設定するメソッド
    fun setTempMelt(melt: Int?): MaterialBuilder = also { it.tempMelt = melt }

    //沸点を設定するメソッド
    fun setTempBoil(boil: Int?): MaterialBuilder = also { it.tempBoil = boil }

    //昇華点を設定するメソッド
    fun setTempSubl(subl: Int?): MaterialBuilder = also { it.tempSubl = subl }

    //素材を登録するメソッド
    fun register(): MaterialBuilder = also {
        if(it.index >= 0) MaterialRegistry.mapIndex[it.index] = it
        MaterialRegistry.mapName[it.name] = it
    }
}