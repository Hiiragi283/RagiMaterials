package hiiragi283.material.material.element

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.IMaterialBase
import hiiragi283.material.util.RagiColor
import java.awt.Color

data class RagiElement(
    override val name: String,
    override val color: Color,
    override val formula: String,
    override val molar: Float
) : IMaterialBase<RagiElement> {

    init {
        ElementRegistry.setElement(this)
    }

    companion object {
        @JvmStatic
        val EMPTY = RagiElement("empty", RagiColor.WHITE, "", 0.0f)
    }

    //元素が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY

    //    IMaterialBase    //

    override fun register(): RagiElement {
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