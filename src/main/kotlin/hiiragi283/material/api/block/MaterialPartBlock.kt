package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.appendBefore
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Material
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.BlockView

data class MaterialPartBlock(
    val materialH: HiiragiMaterial,
    val part: HiiragiPart
) : HiiragiBlock(FabricBlockSettings.of(Material.METAL)) {

    val identifier: Identifier = part.getId(materialH)
    private val tag: Identifier = part.getTag(materialH)

    override fun appendTooltip(
        stack: ItemStack,
        world: BlockView?,
        tooltip: MutableList<Text>,
        options: TooltipContext?
    ) {
        materialH.appendTooltip(tooltip, part)
    }

    //    HiiragiBlock    //

    fun register() = register(identifier.path)

    override fun registerModel() {
        RagiMaterials.RESOURCE_PACK.addBlockState(part.state, identifier)
    }

    override fun registerRecipe(): Unit =
        part.recipes(materialH).forEach(RagiMaterials.RESOURCE_PACK::addRecipe)


    override fun registerTag() {
        RagiMaterials.RESOURCE_PACK.addTag(tag.appendBefore("blocks/"), JTag().add(identifier))
        RagiMaterials.RESOURCE_PACK.addTag(tag.appendBefore("items/"), JTag().add(identifier))
    }

}