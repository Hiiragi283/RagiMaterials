package hiiragi283.material.item

import hiiragi283.material.base.ItemBase
import hiiragi283.material.util.executeCommand
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.common.IRarity

object ItemBookRespawn : ItemBase("book_respawn", 0) {

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

    //    Event    //

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        //落下死防止やコマンド権限のためクリエモードに切り替え
        if (!world.isRemote) executeCommand(player, "gamemode 1")
        if (world.isRemote) {
            val spawnPoint = world.spawnPoint
            val spawnX = spawnPoint.x + 0.5 //ブロックの中心に来るよう調整
            val spawnY = 128.0 //高度チェックが面倒なのでy=128に固定
            val spawnZ = spawnPoint.z + 0.5 //ブロックの中心に来るよう調整
            player.motionX = 0.0
            player.motionY = 0.0
            player.motionZ = 0.0 //運動ベクトルをリセット
            //プレイヤーを指定した座標にテレポート
            player.setLocationAndAngles(spawnX, spawnY, spawnZ, 0.0f, 0.0f)
        }
        return super.onItemRightClick(world, player, hand)
    }

}