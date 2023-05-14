package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

object ItemBookRespawn : ItemBase(FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.EPIC)) {

    override fun getIdentifier(): Identifier = Identifier(RagiMaterials.MOD_ID, "book_respawn")

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (!world.isClient) {
            val posSpawn = world
        }
        return super.use(world, user, hand)
    }
}