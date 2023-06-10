package hiiragi283.material.material

import hiiragi283.material.util.RagiColor
import java.awt.Color

interface IMaterialBase<T : IMaterialBase<T>> {

    fun getName(): String
    fun getIndex(): Int

    fun getColor(): Color = RagiColor.WHITE

    /**
     * "" means this material doesn't have chemical formula
     */
    fun getFormula(): String = ""

    /**
     * 0.0 means this material doesn't have molar mass
     */
    fun getMolar(): Double = 0.0

    /**
     * The values of the temperatures are not Celsius but Kelvin temperature
     */
    fun getTempMelt(): Int = 0
    fun getTempBoil(): Int = 0
    fun getTempSubl(): Int = 0

    fun isEmpty(): Boolean = getName() == "empty"

}