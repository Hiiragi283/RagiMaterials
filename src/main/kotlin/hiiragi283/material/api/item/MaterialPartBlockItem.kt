package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiBlockItem
import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiItemGroup
import hiiragi283.material.common.RagiResourcePack
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

fun createMaterialBlockItem(
    material: HiiragiMaterial,
    shape: HiiragiShape,
    block: MaterialPartBlock
): MaterialPartBlockItem {

    val blockItem = object : MaterialPartBlockItem(block) {

        override fun getIdentifier(): Identifier = block.getIdentifier()

        override fun getName(stack: ItemStack): TranslatableText = shape.getText(material)

        override fun getColor(stack: ItemStack, tintIndex: Int): Int =
            shape.itemColor(material).getColor(stack, tintIndex)

    }

    RagiResourcePack.addItemModel(block.getIdentifier(), shape.model)

    return blockItem
}

abstract class MaterialPartBlockItem(val materialBlock: MaterialPartBlock) :
    HiiragiBlockItem(materialBlock, FabricItemSettings().group(RagiItemGroup.MATERIAL_BLOCk)), ItemColorProvider