package ragi_materials.core.event

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumHand
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.enchant.EnchantmentMaterial
import ragi_materials.core.material.drop.DropMaterialRegistry
import ragi_materials.core.util.hasEnchantment

object DropMaterialEvent {

    @SubscribeEvent
    fun harvestDrops(event: BlockEvent.HarvestDropsEvent) {
        //TNTの爆破による破壊など，プレイヤーが関わらない際のクラッシュを回避
        val player: EntityPlayer? = event.harvester
        if (player !== null) {
            val state = event.state
            //道具にEnchantmentMaterialが付与されている場合
            if (hasEnchantment(EnchantmentMaterial, player.getHeldItem(EnumHand.MAIN_HAND))) {
                //blockからドロップ条件を取得
                val function = DropMaterialRegistry.getObject(state.block)
                //ドロップ一覧を更新
                function.getDrops(event.drops, event.world, event.pos, state, event.fortuneLevel, player, event.isSilkTouching)
            }
        }
    }
}