package hiiragi283.material.material

import hiiragi283.material.util.RagiColor
import java.awt.Color

abstract class HiiragiMaterial {

    abstract val translationKey: String

    companion object {
        @JvmField
        val EMPTY = Builder("empty", -1).build()
    }

    fun isEmpty(): Boolean = getName() == "empty"

    abstract fun getColor(): Color

    /**
     * "" means this material doesn't have chemical formula
     */
    abstract fun getFormula(): String
    abstract fun getIndex(): Int

    /**
     * 0.0 means this material doesn't have molar mass
     */
    abstract fun getMolar(): Double
    abstract fun getName(): String

    /**
     * -1 means this material doesn't have boiling point, melting point, or sublimation point
     * @return Not Celsius but Kelvin temperature
     */
    abstract fun getTempBoil(): Int
    abstract fun getTempMelt(): Int
    abstract fun getTempSubl(): Int

    //    General    //

    override fun equals(other: Any?): Boolean =
        if (other !== null && other is HiiragiMaterial) this.getName() == other.getName() else false

    override fun hashCode(): Int = getName().hashCode()

    override fun toString(): String = "Material:${this.getName()}"

    //    Builder    //

    open class Builder(val name: String, val index: Int) {

        var color: Color = RagiColor.WHITE
        var formula: String = ""
        var molar: Double = 0.0
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