package hiiragi283.material.common.item

import hiiragi283.material.api.item.HiiragiItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

object RespawnCompassItem : HiiragiItem(FabricItemSettings().group(ItemGroup.TOOLS)) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val property = world.levelProperties
        //userをスポーン地点にテレポート
        user.teleport(property.spawnX.toDouble(), property.spawnY.toDouble(), property.spawnZ.toDouble())
        return TypedActionResult.success(user.getStackInHand(hand))
    }

}