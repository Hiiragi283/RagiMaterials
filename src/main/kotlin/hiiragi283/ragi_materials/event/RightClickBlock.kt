package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class RightClickBlock {

    @SubscribeEvent
    fun onRightClickBlock(event: PlayerInteractEvent.RightClickBlock) {
        //サーバー側&&デバッグ状態のみで実行
        if (!event.world.isRemote && RagiConfig.debugMode.isDebug) {
            //各値の取得
            val stack = event.itemStack
            val item = stack.item
            val player = event.entityPlayer
            val world = event.world
            val pos = event.pos
            val state = world.getBlockState(pos)
            val block = state.block
            val meta = block.getMetaFromState(state)
            //Property Bookの機能
            if (RagiUtils.isSameStack(stack, RagiUtils.getStack("ragi_materials:book_debug", 1, 2))) {
                player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line"))
                //ブロックの翻訳名をチャットに表示
                player.sendMessage(TextComponentString("  §eName:§r§b " + ItemStack(block, 1, meta).displayName))
                //ブロックのIDをチャットに表示
                player.sendMessage(TextComponentString("  §eID:§r§b " + block.registryName))
                //ブロックのBlockstateをチャットに表示
                player.sendMessage(TextComponentString("  §eBlockstate:§r§b $state"))
                //ブロックのHardnessをチャットに表示
                player.sendMessage(TextComponentString("  §eHardness:§r§b " + state.getBlockHardness(world, pos)))
                //ブロックのResistanceをチャットに表示
                player.sendMessage(TextComponentString("  §eResistance:§r§b " + block.getExplosionResistance(player)))
                //適正ツールをチャットに表示
                player.sendMessage(TextComponentString("  §eHarvest Tool:§r§b " + block.getHarvestTool(state)))
                //適正レベルをチャットに表示
                player.sendMessage(TextComponentString("  §eHarvest Level:§r§b " + block.getHarvestLevel(state)))
                player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line"))
            }
        }
    }
}