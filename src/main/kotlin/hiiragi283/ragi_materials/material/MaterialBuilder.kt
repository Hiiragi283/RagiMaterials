package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import java.awt.Color

open class MaterialBuilder(
    open val index: Int, open val name: String, open val type: MaterialType
) {
    //コンストラクタの初期化
    init {
        register()
    }

    //private変数の宣言
    var color = Color(0xFFFFFF)
    var molar: Float = 0.0f
    var melt: Int = 0
    var boil: Int = 0
    var subl: Int = 0
    var formula: String? = ""
    var hasBracket = false

    //化学式に()をつけるメソッド
    fun addBracket(): MaterialBuilder {
        hasBracket = true
        return this
    }

    //EnumMaterialもしくはindexから液体を取得するメソッド
    fun getFluid(): Fluid {
        val fluid = FluidRegistry.getFluid(this.name)
        //fluidが存在しない場合は水を返す
        return if (fluid !== null) fluid else FluidRegistry.getFluid("water")
    }

    //化学式を取得するメソッド（デフォルトはH.T.）
    open fun getFormula(isNull: Boolean): String? {
        return if(formula !== null) formula!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the Chemical Formula!")
                "H.T."
            }
            //返り値にnullを使う場合
            else null
        }
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.name.snakeToUpperCamelCase()
    }

    //色を設定するメソッド
    fun setColor(color: Color): MaterialBuilder {
        this.color = color
        return this
    }

    //化学式を設定するメソッド
    fun setFormula(formula: String): MaterialBuilder {
        this.formula = formula
        return this
    }

    //モル質量を設定するメソッド
    fun setMolarMass(molar: Float): MaterialBuilder {
        this.molar = molar
        return this
    }

    //融点を設定するメソッド
    fun setTempMelt(melt: Int): MaterialBuilder {
        this.melt = melt
        return this
    }

    //沸点を設定するメソッド
    fun setTempBoil(boil: Int): MaterialBuilder {
        this.boil = boil
        return this
    }

    //昇華点を設定するメソッド
    fun setTempSubl(subl: Int): MaterialBuilder {
        this.subl = subl
        return this
    }

    //素材を登録するメソッド
    fun register(): MaterialBuilder {
        MaterialRegistry.list.add(this)
        return this
    }
}