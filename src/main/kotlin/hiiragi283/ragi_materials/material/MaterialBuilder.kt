package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import java.awt.Color

open class MaterialBuilder(
    open val index: Int, open val name: String, open val type: MaterialType
) {

    //private変数の宣言
    open var burnTime = -1
    open var color: Color? = null
    open var decayed: MaterialBuilder? = null
    open var formula: String? = null
    open var hasBracket = false
    open var molar: Float? = null
    open var oredictAlias: String? = null
    open var tempBoil: Int? = null
    open var tempMelt: Int? = null
    open var tempSubl: Int? = null
    open var hasOre = false

    //化学式に()をつけるメソッド
    fun addBracket(): MaterialBuilder {
        hasBracket = true
        return this
    }

    //EnumMaterialもしくはindexから液体を取得するメソッド
    fun getFluid(): Fluid? {
        val fluid = FluidRegistry.getFluid(this.name)
        //fluidが存在しない場合は水を返す
        return if (fluid !== null) fluid else null
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.name.snakeToUpperCamelCase()
    }

    //燃焼時間を設定するメソッド
    fun setBurnTime(time: Int): MaterialBuilder {
        this.burnTime = time
        return this
    }

    //色を設定するメソッド
    fun setColor(color: Color?): MaterialBuilder {
        this.color = color
        return this
    }

    //崩壊後の素材を設定するメソッド
    fun setDecayed(decayed: MaterialBuilder): MaterialBuilder {
        this.decayed = decayed
        return this
    }

    //化学式を設定するメソッド
    fun setFormula(formula: String?): MaterialBuilder {
        this.formula = formula
        return this
    }

    //モル質量を設定するメソッド
    fun setMolarMass(molar: Float?): MaterialBuilder {
        this.molar = molar
        return this
    }

    //鉱石を追加するメソッド
    fun setOre(): MaterialBuilder {
        this.hasOre = true
        return this
    }

    //モル質量を設定するメソッド
    fun setOreDictAlias(name: String?): MaterialBuilder {
        this.oredictAlias = name
        return this
    }

    //融点を設定するメソッド
    fun setTempMelt(melt: Int?): MaterialBuilder {
        this.tempMelt = melt
        return this
    }

    //沸点を設定するメソッド
    fun setTempBoil(boil: Int?): MaterialBuilder {
        this.tempBoil = boil
        return this
    }

    //昇華点を設定するメソッド
    fun setTempSubl(subl: Int?): MaterialBuilder {
        this.tempSubl = subl
        return this
    }

    //素材を登録するメソッド
    fun register(): MaterialBuilder {
        MaterialRegistry.mapIndex[this.index] = this
        MaterialRegistry.mapName[this.name] = this
        return this
    }
}