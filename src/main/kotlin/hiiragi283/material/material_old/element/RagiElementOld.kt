package hiiragi283.material.material_old.element

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.IMaterialBase
import hiiragi283.material.util.RagiColor
import java.awt.Color

data class RagiElementOld(
    @JvmField val name: String,
    @JvmField val color: Color,
    @JvmField val formula: String,
    @JvmField val molar: Float
) : IMaterialBase<RagiElementOld> {

    init {
        ElementRegistry.setElement(this)
    }

    companion object {
        @JvmStatic
        val EMPTY = RagiElementOld("empty", RagiColor.WHITE, "", 0.0f)
    }

    //元素が空か判定するメソッド
    override fun isEmpty(): Boolean = this == EMPTY

    //    IMaterialBaseOld    //

    override fun getColor(): Color = color

    override fun getFormula(): String = formula

    override fun getMolar(): Double = molar.toDouble()

    override fun getName(): String = name

    override fun getIndex(): Int = -1

    fun register(): RagiElementOld {
        val element = ElementRegistry.getElement(name)
        //同じ名前の素材がない場合
        if (!element.isEmpty()) {
            ElementRegistry.setElement(this)
        } else {
            RagiMaterials.LOGGER.warn("The material $name is duplicated with ${element.name}!")
        }
        return this
    }
}