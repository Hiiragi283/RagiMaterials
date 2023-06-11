package hiiragi283.material.material

import hiiragi283.material.util.RagiColor
import rechellatek.snakeToUpperCamelCase
import java.awt.Color

abstract class HiiragiMaterial {

    abstract val translationKey: String

    companion object {
        @JvmField
        val EMPTY = Builder("empty", -1).build()
    }

    fun isEmpty(): Boolean = this == EMPTY

    /**
     * Material color for this material
     */
    abstract fun getColor(): Color

    /**
     * Chemical formula for this material
     * @return "" is regarded as not having chemical formula
     */
    abstract fun getFormula(): String
    fun hasFormula(): Boolean = getFormula().isBlank()

    /**
     * Integer index for this material
     * @return Negative value is regarded as not having the value
     */
    abstract fun getIndex(): Int

    /**
     * Molar mass for this material
     * @return Negative value is regarded as not having the value
     */
    abstract fun getMolar(): Double
    fun hasMolar(): Boolean = getMolar() > 0.0

    /**
     * Name for this material
     * @return Should be unique
     */
    abstract fun getName(): String

    /**
     * Boiling point with Kelvin Temperature for this material
     * @return Negative value is regarded as not having the value
     */
    abstract fun getTempBoil(): Int
    fun hasTempBoil(): Boolean = getTempBoil() >= 0

    /**
     * Melting point with Kelvin Temperature for this material
     * @return Negative value is regarded as not having the value
     */
    abstract fun getTempMelt(): Int
    fun hasTempMelt(): Boolean = getTempBoil() >= 0

    /**
     * Sublimation point with Kelvin Temperature for this material
     * @return Negative value is regarded as not having the value
     */
    abstract fun getTempSubl(): Int
    fun hasTempSubl(): Boolean = getTempSubl() >= 0

    fun getOreDictName() = getName().snakeToUpperCamelCase()

    //物質の標準状態での相を返すメソッド
    fun getState(): StandardState {
        //沸点と融点が有効な場合
        if (hasTempBoil() && hasTempMelt()) {
            //沸点が298 K以下 -> 標準状態で気体
            if (getTempBoil() <= 298) return StandardState.GAS
            //融点が常温以下 -> 標準状態で液体
            else if (getTempMelt() <= 298) return StandardState.LIQUID
        }
        //それ以外は固体として扱う
        return StandardState.SOLID
    }

    //    General    //

    override fun equals(other: Any?): Boolean =
        if (other !== null && other is HiiragiMaterial) this.getName() == other.getName() else false

    override fun hashCode(): Int = getName().hashCode()

    override fun toString(): String = "Material:${this.getName()}"

    //    Builder    //

    open class Builder(val name: String, val index: Int) {

        var color: Color = RagiColor.WHITE
        var formula: String = ""
        var molar: Double = -1.0
        var tempBoil: Int = -1
        var tempMelt: Int = -1
        var tempSubl: Int = -1

        fun setColor(color: Color) = also { this.color = color }
        fun setFormula(formula: String) = also { this.formula = formula }
        fun setMolar(molar: Double) = also { this.molar = molar }
        fun setTempBoil(temp: Int) = also { this.tempBoil = temp }
        fun setTempMelt(temp: Int) = also { this.tempMelt = temp }
        fun setTempSubl(temp: Int) = also { this.tempSubl = temp }

        open fun build(): HiiragiMaterial = object : HiiragiMaterial() {

            override val translationKey = "material.$name"
            override fun getColor(): Color = color
            override fun getFormula(): String = formula
            override fun getIndex(): Int = index
            override fun getMolar(): Double = molar
            override fun getName(): String = name
            override fun getTempBoil(): Int = tempBoil
            override fun getTempMelt(): Int = tempMelt
            override fun getTempSubl(): Int = tempSubl

        }

    }

}