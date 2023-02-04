package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import java.awt.Color

open class MaterialBuilder(
    open val index: Int, open val name: String, open val type: MaterialType
) {
    //private変数の宣言
    private var color = Color(0xFFFFFF)
    private var molar: Float? = null
    private var melting: Int? = null
    private var boiling: Int? = null
    private var subl: Int? = null
    private var formula: String? = null
    private var mapComponents: Map<Any, Int> = mapOf(MaterialRegistry.WILDCARD to 1)

    //色を取得するメソッド (デフォルトは0xFFFFFF)
    open fun getColor(): Color {
        return color
    }

    //組成を取得するメソッド
    fun getComponents(): Map<Any, Int> {
        return mapComponents
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
    open fun getMolarMass(isNull: Boolean): Float? {
        return if(molar !== null) molar!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                32767.0F
            }
            //返り値にnullを使う場合
            else null
        }
    }

    open fun getMolarMass(): Float {
        return getMolarMass(false)!!
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.name.snakeToUpperCamelCase()
    }

    //融点を取得するメソッド (デフォルトは32767 ℃)
    open fun getTempMelt(isNull: Boolean): Int? {
        return if(melting !== null) melting!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                32767
            }
            //返り値にnullを使う場合
            else null
        }
    }

    fun getTempMelt(): Int {
        return getTempMelt(false)!!
    }

    //沸点を取得するメソッド (デフォルトは32767 ℃)
    open fun getTempBoil(isNull: Boolean): Int? {
        return if(boiling !== null) boiling!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                32767
            }
            //返り値にnullを使う場合
            else null
        }
    }

    fun getTempBoil(): Int {
        return getTempBoil(false)!!
    }

    //昇華点を取得するメソッド (デフォルトは32767 ℃)
    open fun getTempSubl(isNull: Boolean): Int? {
        return if(subl !== null) subl!! else {
            //返り値にnullを使わない場合
            if (!isNull) {
                RagiLogger.warn("The material <material:${this.name}> does not have the parameter of Molar Mass!")
                32767
            }
            //返り値にnullを使う場合
            else null
        }
    }

    fun getTempSubl(): Int {
        return getTempSubl(false)!!
    }

    //色を設定するメソッド
    fun setColor(color: Color): MaterialBuilder {
        this.color = color
        return this
    }

    //組成を設定するメソッド
    fun setComponents(mapComponents: Map<Any, Int>): MaterialBuilder {
        this.mapComponents = mapComponents
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