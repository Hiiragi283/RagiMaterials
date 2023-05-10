package hiiragi283.material.material

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.type.MaterialType
import hiiragi283.material.material.type.TypeRegistry
import hiiragi283.material.util.RagiColor
import java.awt.Color

data class RagiElement(
    override val name: String = "empty",
    override val type: MaterialType = TypeRegistry.INTERNAL,
    override val color: Color = RagiColor.WHITE,
    override val formula: String = "",
    override val molar: Float? = null,
) : IMaterialBase<RagiElement> {

    companion object {
        @JvmStatic
        val EMPTY = RagiElement()
    }

    //元素が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY

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