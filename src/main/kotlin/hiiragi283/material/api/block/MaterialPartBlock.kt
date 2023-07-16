package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.IHiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

fun createMaterialBlock(
    material: HiiragiMaterial,
    shape: HiiragiShape
): MaterialPartBlock {

    val identifier: Identifier = shape.getId(material)
    val tag: Identifier = shape.getTag(material)

    val block = MaterialPartBlock(identifier)
    block.blockColor = { _, _, _, _ -> material.color }
    block.itemColor = { _, _ -> material.color }

    RagiResourcePack.addBlockState(identifier, shape.state)
    shape.recipes(material).forEach(RagiResourcePack::addRecipe)
    RagiResourcePack.addBlockTag(tag, JTag().add(identifier))
    RagiResourcePack.addItemTag(tag, JTag().add(identifier))

    return block
}

class MaterialPartBlock internal constructor(
    override val identifier: Identifier
) : HiiragiBlock(FabricBlockSettings.of(Material.METAL)), IHiiragiPart.BLOCK {

    var blockColor: (BlockState, BlockRenderView?, BlockPos?, Int) -> Int = { state, view, pos, tintIndex -> -1 }
    var itemColor: (ItemStack, Int) -> Int = { stack, tintIndex -> -1 }

    //    IHiiragiPart    //

    override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
        blockColor(state, view, pos, tintIndex)

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = itemColor(stack, tintIndex)

}