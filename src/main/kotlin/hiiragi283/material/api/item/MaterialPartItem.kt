package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.HiiragiItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.common.RagiItemGroup
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

fun createMaterialItem(
    material: HiiragiMaterial,
    shape: HiiragiShape
): MaterialPartItem {

    val identifier: Identifier = shape.getId(material)

    val item = object : MaterialPartItem(), HiiragiEntry.ITEM {

        override fun getIdentifier(): Identifier = identifier

        override fun getName(stack: ItemStack): TranslatableText = shape.getText(material)

        override fun getColor(stack: ItemStack, tintIndex: Int): Int =
            shape.itemColor(material).getColor(stack, tintIndex)

    }

    RagiResourcePack.addItemModel(identifier, shape.model)
    shape.recipes(material).forEach(RagiResourcePack::addRecipe)
    RagiResourcePack.addItemTag(shape.getTag(material), JTag().add(identifier))

    return item
}

abstract class MaterialPartItem(settings: FabricItemSettings = FabricItemSettings().group(RagiItemGroup.MATERIAL_ITEM)) :
    HiiragiItem(settings), ItemColorProvider