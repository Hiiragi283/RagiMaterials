package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.IHiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class MaterialPartItem(
    val material: HiiragiMaterial,
    val shape: HiiragiShape
) : HiiragiItem(), IHiiragiPart.ITEM {

    override val identifier: Identifier = shape.getId(material)
    private val tag: Identifier = shape.getTag(material)

    override fun getName(stack: ItemStack): Text = shape.getName(material)

    //    HiiragiItem    //

    override fun registerModel() {
        RagiResourcePack.addItemModel(identifier, shape.model)
    }

    override fun registerRecipe(): Unit =
        shape.recipes(material).forEach(RagiResourcePack::addRecipe)

    override fun registerTag() {
        RagiResourcePack.addItemTag(tag, JTag().add(identifier))
    }

    //    IHiiragiPart    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = getMaterial(stack).color

    override fun getMaterial(obj: ItemStack): HiiragiMaterial = material

    override fun getShape(obj: ItemStack): HiiragiShape = shape

}