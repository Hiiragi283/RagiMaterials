package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiBlockItem
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RMItemGroup
import hiiragi283.material.common.RMResourcePack
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

abstract class MaterialBlockItem(materialBlock: MaterialBlock) :
    HiiragiBlockItem(materialBlock, FabricItemSettings().group(RMItemGroup.MATERIAL_BLOCk)), ItemColorProvider {

    companion object {

        @JvmStatic
        fun of(
            part: HiiragiPart,
            block: MaterialBlock
        ): MaterialBlockItem {

            val blockItem = object : MaterialBlockItem(block) {

                override fun getIdentifier(): Identifier = block.getIdentifier()

                override fun getName(stack: ItemStack): TranslatableText = part.getText()

                override fun getColor(stack: ItemStack, tintIndex: Int): Int =
                    part.getItemColor().getColor(stack, tintIndex)

            }

            RMResourcePack.addItemModel(block.getIdentifier(), part.shape.getModel())

            return blockItem
        }

    }

}