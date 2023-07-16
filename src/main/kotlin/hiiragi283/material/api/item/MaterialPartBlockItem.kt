package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.IHiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiResourcePack
import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

fun createMaterialBlockItem(
    material: HiiragiMaterial,
    shape: HiiragiShape,
    block: MaterialPartBlock
): MaterialPartBlockItem {

    val blockItem = object : MaterialPartBlockItem(block) {

        override fun getName(stack: ItemStack): TranslatableText = shape.getText(material)

        override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
            materialBlock.blockColor(state, view, pos, tintIndex)

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = materialBlock.itemColor(stack, tintIndex)

    }

    RagiResourcePack.addItemModel(block.identifier, shape.model)

    return blockItem
}

abstract class MaterialPartBlockItem internal constructor(
    val materialBlock: MaterialPartBlock,
    override val identifier: Identifier = materialBlock.identifier
) : HiiragiBlockItem(materialBlock), IHiiragiPart.ITEM