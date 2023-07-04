package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialPart
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World

class MaterialPartItem(
    val material: HiiragiMaterial,
    val part: HiiragiPart
) : HiiragiItem(), MaterialPart<ItemStack> {

    override val identifier: Identifier = part.getId(material)
    private val tag: Identifier = part.getTag(material)

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        material.appendTooltip(tooltip, part)
    }

    override fun getName(stack: ItemStack): Text = part.getName(material)

    //    HiiragiItem    //

    override fun registerModel() {
        RagiResourcePack.addItemModel(identifier, part.model)
    }

    override fun registerRecipe(): Unit =
        part.recipes(material).forEach(RagiResourcePack::addRecipe)

    override fun registerTag() {
        RagiResourcePack.addItemTag(tag, JTag().add(identifier))
    }

    //    MaterialPart    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = getMaterial(stack).color

    override fun getMaterial(obj: ItemStack): HiiragiMaterial = material

    override fun getPart(obj: ItemStack): HiiragiPart = part

}