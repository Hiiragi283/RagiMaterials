package hiiragi283.material.common.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.common.util.hiiragiId
import hiiragi283.material.common.util.playHypixel
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

object RespawnBookItem : HiiragiItem(
    FabricItemSettings()
        .group(ItemGroup.TOOLS)
        .rarity(Rarity.EPIC)
) {

    override fun hasGlint(stack: ItemStack): Boolean = true

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val property = world.levelProperties
        //userのmotionをリセット
        user.velocity = Vec3d.ZERO
        //userをスポーン地点にテレポート
        user.updatePosition(
            property.spawnX.toDouble() + 0.5,
            property.spawnY.toDouble(),
            property.spawnZ.toDouble() + 0.5
        )
        //userの視線をリセット
        user.yaw = 0.0f
        user.pitch = 0.0f
        //SEを再生
        playHypixel(world, user.pos)
        return TypedActionResult.success(user.getStackInHand(hand))
    }

    //    HiiragiItem    //

    override val identifier: Identifier = hiiragiId("respawn_book")

}