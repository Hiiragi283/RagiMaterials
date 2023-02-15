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
    private var color = Color(0xFFFFFF)
    private var molar: Float = 0.0f
    private var melting: Int = 0
    private var boiling: Int = 0
    private var subl: Int = 0
    private var formula: String? = ""
    private var mapComponents: Map<Any, Int> = mapOf(MaterialRegistry.WILDCARD to 1)

    //色を取得するメソッド (デフォルトは0xFFFFFF)
    open fun getColor(): Color {
        return color
    }

    //組成を取得するメソッド
    fun getComponents(): Map<Any, Int> {
        return mapComponents
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

    open fun getFormula(): String {
        return getFormula(false)!!
    }

    //モル質量を取得するメソッド
    open fun getMolarMass(): Float {
        return molar
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.name.snakeToUpperCamelCase()
    }

    //融点を取得するメソッド
    open fun getTempMelt(): Int {
        return melting
    }

    //沸点を取得するメソッド
    open fun getTempBoil(): Int {
        return boiling
    }

    //昇華点を取得するメソッド
    open fun getTempSubl(): Int {
        return subl
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

    //素材を登録するメソッド
    fun register(): MaterialBuilder {
        MaterialRegistry.list.add(this)
        return this
    }
}