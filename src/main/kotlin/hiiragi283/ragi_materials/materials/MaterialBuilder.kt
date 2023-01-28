package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import java.awt.Color

open class MaterialBuilder(
    val index: Int, val name: String, val type: MaterialType
) {
    //private変数の宣言
    private var color = Color(0xFFFFFF)
    private var molar: Float? = null
    private var melting: Int? = null
    private var boiling: Int? = null
    private var subl: Int? = null
    private var formula: String? = null

    //色を取得するメソッド (デフォルトは0xFFFFFF)
    fun getColor(): Color {
        return color
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

    open fun getFormula(): String {
        return getFormula(false)!!
    }
    //モル質量を取得するメソッド (デフォルトは32767.0 g/mol)
    fun getMolarMass(isNull: Boolean): Float? {
        if(molar !== null) return molar!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                return 32767.0F
            }
            //返り値にnullを使う場合
            else return null
        }
    }

    fun getMolarMass(): Float {
        return getMolarMass(false)!!
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.name.snakeToUpperCamelCase()
    }

    //融点を取得するメソッド (デフォルトは32767 ℃)
    fun getTempMelt(isNull: Boolean): Int? {
        if(melting !== null) return melting!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                return 32767
            }
            //返り値にnullを使う場合
            else return null
        }
    }

    fun getTempMelt(): Int {
        return getTempMelt(false)!!
    }

    //沸点を取得するメソッド (デフォルトは32767 ℃)
    fun getTempBoil(isNull: Boolean): Int? {
        if(boiling !== null) return boiling!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                return 32767
            }
            //返り値にnullを使う場合
            else return null
        }
    }

    fun getTempBoil(): Int {
        return getTempBoil(false)!!
    }

    //昇華点を取得するメソッド (デフォルトは32767 ℃)
    fun getTempSubl(isNull: Boolean): Int? {
        if(subl !== null) return subl!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                return 32767
            }
            //返り値にnullを使う場合
            else return null
        }
    }

    fun getTempSubl(): Int {
        return getTempSubl(false)!!
    }

    //色を設定するメソッド (デフォルトは0xFFFFFF)
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
    fun setTempMelt(melting: Int): MaterialBuilder {
        this.melting = melting
        return this
    }

    //沸点を設定するメソッド
    fun setTempBoil(boiling: Int): MaterialBuilder {
        this.boiling = boiling
        return this
    }

    //昇華点を設定するメソッド
    fun setTempSubl(subl: Int): MaterialBuilder {
        this.subl = subl
        return this
    }

    enum class MaterialType(
        val hasDust: Boolean,
        val hasIngot: Boolean,
        val hasFluid: Boolean,
        val hasFluidBlock: Boolean
    ) {
        CARBON(true, true, false, false), //昇華する半金属
        DUST(true, false, false, false), //粉末
        GAS(false, false, true, true), //気体
        INTERNAL(false, false, false, false), //内部データ用
        LIQUID(false, false, true, true), //液体
        METAL(true, true, true, false), //金属
        WILDCARD(true, true, true, true) //すべての部品を追加
    }
}