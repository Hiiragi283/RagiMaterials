package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.HiiragiItem
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RMItemGroup
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.RagiMaterials
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

abstract class MaterialItem(settings: FabricItemSettings = FabricItemSettings().group(RMItemGroup.MATERIAL_ITEM)) :
    HiiragiItem(settings), RMItemColorProvider {

    companion object {

        @JvmStatic
        fun of(part: HiiragiPart): MaterialItem {

            val identifier: Identifier = part.getId()

            val item = object : MaterialItem(), HiiragiEntry.ITEM {

                override fun getIdentifier(): Identifier = identifier

                override fun getName(stack: ItemStack): TranslatableText = part.getText()

                override fun getColor(stack: ItemStack, tintIndex: Int): Int =
                    part.getItemColor().getColor(stack, tintIndex)

                override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
                    if (!world.isClient) {
                        user.getStackInHand(hand).streamTags().forEach { RagiMaterials.LOGGER.info(it) }
                    }
                    return super.use(world, user, hand)
                }

            }

            RMResourcePack.addItemModel(identifier, part.shape.getModel())
            part.getRecipe().forEach(RMResourcePack::addRecipe)
            RMResourcePack.addItemTag(part.getTadId(), JTag().add(identifier))

            return item
        }

    }

}