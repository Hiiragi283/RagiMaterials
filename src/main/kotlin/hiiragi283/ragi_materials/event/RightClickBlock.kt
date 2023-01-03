package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class RightClickBlock {
    //ブロックを右クリックすると呼ばれるevent
    @SubscribeEvent
    fun onRightClickBlock(event: PlayerInteractEvent.RightClickBlock) {
        //サーバー側のみで実行
        if (!event.world.isRemote) {
            //各値の取得
            val stack = event.itemStack
            val item = stack.item
            val stackCompare = ItemStack(item, 1, stack.metadata) //比較用にスタック数を1に置き換えたもの
            val player = event.entityPlayer
            val world = event.world
            val pos = event.pos
            val state = world.getBlockState(pos)
            val block = state.block
            val meta = block.getMetaFromState(state)
            //取得した値をログに出力
            /*infoDebug("============")
            infoDebug(stack)
            infoDebug(item)
            infoDebug(stackCompare)
            infoDebug(player)
            infoDebug(world)
            infoDebug(pos)
            infoDebug(state)
            infoDebug(block)
            infoDebug(meta)*/
            //Property Bookの機能
            if (RagiUtils.isSameStack(stack, RagiUtils.getStack("ragi_materials:book_debug", 1, 2))) {
                player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line.name"))
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
                player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line.name"))
            }
        }
    }
}