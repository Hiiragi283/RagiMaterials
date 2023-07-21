package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiBlockItem
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.with
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiItemGroup
import hiiragi283.material.common.RagiResourcePack
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

abstract class MaterialBlockItem(materialBlock: MaterialBlock) :
    HiiragiBlockItem(materialBlock, FabricItemSettings().group(RagiItemGroup.MATERIAL_BLOCk)), ItemColorProvider {

    companion object {

        @JvmStatic
        fun of(
            material: HiiragiMaterial,
            shape: HiiragiShape,
            block: MaterialBlock
        ): MaterialBlockItem {

            val part = shape with material

            val blockItem = object : MaterialBlockItem(block) {

                override fun getIdentifier(): Identifier = block.getIdentifier()

                override fun getName(stack: ItemStack): TranslatableText = part.getText()

                override fun getColor(stack: ItemStack, tintIndex: Int): Int =
                    shape.itemColor(material).getColor(stack, tintIndex)

            }

            RagiResourcePack.addItemModel(block.getIdentifier(), shape.model)

            return blockItem
        }

    }

}