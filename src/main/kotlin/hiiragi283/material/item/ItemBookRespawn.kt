package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.Level

object ItemBookRespawn :
    ItemBase(FabricItemSettings().group(CreativeModeTab.TAB_MISC).maxCount(1).rarity(Rarity.EPIC)) {

    override fun getRegistryName(): ResourceLocation = ResourceLocation(RagiMaterials.MOD_ID, "book_respawn")

    override fun use(
        level: Level,
        player: Player,
        interactionHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        return super.use(level, player, interactionHand)
    }
}