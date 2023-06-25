package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.appendBefore
import net.devtech.arrp.json.tags.JTag
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World

class MaterialPartItem(
    val material: HiiragiMaterial,
    val part: HiiragiPart
) : HiiragiItem() {

    private val identifier: Identifier = part.getId(material)
    private val tag: Identifier = part.getTag(material)

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        material.appendTooltip(tooltip, part)
    }

    override fun getName(): Text = part.getName(material)

    //    HiiragiItem    //

    fun register() = register(identifier.path)

    override fun registerModel() {
        RagiMaterials.RESOURCE_PACK.addModel(part.model, identifier.appendBefore("item/"))
    }

    override fun registerRecipe(): Unit =
        part.recipes(material).forEach(RagiMaterials.RESOURCE_PACK::addRecipe)

    override fun registerTag() {
        RagiMaterials.RESOURCE_PACK.addTag(tag.appendBefore("items/"), JTag().add(identifier))
    }

}