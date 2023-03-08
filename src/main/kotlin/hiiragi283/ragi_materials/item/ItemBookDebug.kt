package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.common.IRarity

class ItemBookDebug : ItemBase(Reference.MOD_ID, "book_debug", 3) {

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

    //    Event    //

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val stack: ItemStack = player.getHeldItem(hand)
        //デバッグモードの場合
        if (RagiConfig.debugMode.isDebug) {
            //Spawn用
            if (stack.metadata == 0) {
                //各値の取得
                val spawnPoint = world.spawnPoint
                val spawnX = spawnPoint.x + 0.5 //ブロックの中心に来るよう調整
                val spawnY = 128.0 //高度チェックが面倒なのでy=128に固定
                val spawnZ = spawnPoint.z + 0.5 //ブロックの中心に来るよう調整
                //プレイヤーを指定した座標にテレポート
                player.setLocationAndAngles(spawnX, spawnY, spawnZ, 0f, 0f)
                //サーバー側のみで実行
                if (!world.isRemote) {
                    //チャットにテキストを流す
                    player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line"))
                    player.sendMessage(TextComponentTranslation("text.ragi_materials.spawn"))
                    player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line"))
                }
            }
            //Syntax用
            else if (stack.metadata == 1) {
                //サーバー側のみで実行
                if (!world.isRemote) {
                    //コマンドを実行
                    RagiUtil.executeCommand(player, "ct syntax")
                    RagiUtil.executeCommand(player, "ct reload")
                    //チャットにテキストを流す
                    player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line"))
                    player.sendMessage(TextComponentTranslation("text.ragi_materials.syntax"))
                    player.sendMessage(TextComponentTranslation("text.ragi_materials.decoration_line"))
                }
            }
            //落下死防止やコマンド権限のためクリエモードに切り替え
            if (!world.isRemote) RagiUtil.executeCommand(player, "gamemode 1")
        } else if (!world.isRemote) player.sendMessage(TextComponentTranslation("text.ragi_materials.debug_disabled"))
        return ActionResult(EnumActionResult.SUCCESS, stack)
    }

    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        //サーバー側，かつデバッグ状態のみで実行
        if (!world.isRemote && RagiConfig.debugMode.isDebug) {
            //Property用
            when (player.getHeldItem(hand).metadata) {
                2 -> {
                    //各値の取得
                    val state = world.getBlockState(pos)
                    val block = state.block
                    val meta = block.getMetaFromState(state)
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
                    return EnumActionResult.SUCCESS
                }
                //Tile Entity用
                3 -> {
                    val tile = world.getTileEntity(pos)
                    return if (tile !== null) {
                        player.sendMessage(TextComponentString("The Tile entity is here!"))
                        EnumActionResult.SUCCESS
                    } else {
                        player.sendMessage(TextComponentString("The Tile entity is null!"))
                        EnumActionResult.FAIL
                    }
                }
                else -> return EnumActionResult.FAIL
            }
        } else return EnumActionResult.SUCCESS
    }
}