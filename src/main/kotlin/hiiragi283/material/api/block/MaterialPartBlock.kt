package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialPart
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import net.minecraft.world.BlockView

class MaterialPartBlock(
    val materialH: HiiragiMaterial,
    val part: HiiragiPart
) : HiiragiBlock(FabricBlockSettings.of(Material.METAL)), MaterialPart<BlockState> {

    override val identifier: Identifier = part.getId(materialH)
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

    override fun registerModel() = RagiResourcePack.addBlockState(identifier, part.state)

    override fun registerRecipe(): Unit =
        part.recipes(materialH).forEach(RagiResourcePack::addRecipe)


    override fun registerTag() {
        RagiResourcePack.addBlockTag(tag, JTag().add(identifier))
        RagiResourcePack.addItemTag(tag, JTag().add(identifier))
    }

    //    MaterialPart    //

    override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
        getMaterial(state).color

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = materialH.color

    override fun getMaterial(obj: BlockState): HiiragiMaterial = materialH

    override fun getPart(obj: BlockState): HiiragiPart = part

}