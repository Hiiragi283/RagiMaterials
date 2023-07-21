package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.HiiragiItem
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiItemGroup
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.RagiResourcePack
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

abstract class MaterialItem(settings: FabricItemSettings = FabricItemSettings().group(RagiItemGroup.MATERIAL_ITEM)) :
    HiiragiItem(settings), ItemColorProvider {

    companion object {

        @JvmStatic
        fun of(part: HiiragiPart): MaterialItem {

            val identifier: Identifier = part.getId()
            val shape = part.shape
            val material = part.material

            val item = object : MaterialItem(), HiiragiEntry.ITEM {

                override fun getIdentifier(): Identifier = identifier

                override fun getName(stack: ItemStack): TranslatableText = part.getText()

                override fun getColor(stack: ItemStack, tintIndex: Int): Int =
                    shape.itemColor(material).getColor(stack, tintIndex)

                override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
                    if (!world.isClient) {
                        user.getStackInHand(hand).streamTags().forEach { RagiMaterials.LOGGER.info(it) }
                    }
                    return super.use(world, user, hand)
                }

            }

            RagiResourcePack.addItemModel(identifier, shape.model)
            shape.recipes(material).forEach(RagiResourcePack::addRecipe)
            RagiResourcePack.addItemTag(part.getTadId(), JTag().add(identifier))

            return item
        }

    }

}