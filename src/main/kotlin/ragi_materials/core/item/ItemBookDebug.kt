package ragi_materials.core.item

import net.minecraft.creativetab.CreativeTabs
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
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.util.RagiColor
import ragi_materials.core.util.executeCommand

class ItemBookDebug : ItemBase(RagiMaterials.MOD_ID, "book_debug", 0), IMaterialItem {

    init {
        creativeTab = CreativeTabs.MISC
    }

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

    //    Event    //

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val stack: ItemStack = player.getHeldItem(hand)
        //落下死防止やコマンド権限のためクリエモードに切り替え
        if (!world.isRemote) executeCommand(player, "gamemode 1")
        if (world.isRemote && !player.isSneaking) {
            //各値の取得
            val spawnPoint = world.spawnPoint
            val spawnX = spawnPoint.x + 0.5 //ブロックの中心に来るよう調整
            val spawnY = 128.0 //高度チェックが面倒なのでy=128に固定
            val spawnZ = spawnPoint.z + 0.5 //ブロックの中心に来るよう調整
            player.motionX = 0.0
            player.motionY = 0.0
            player.motionZ = 0.0 //運動ベクトルをリセット
            //プレイヤーを指定した座標にテレポート
            player.setLocationAndAngles(spawnX, spawnY, spawnZ, 0f, 0f)
        }
        return ActionResult(EnumActionResult.SUCCESS, stack)
    }

    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        if (!world.isRemote && player.isSneaking) {
            //各値の取得
            val state = world.getBlockState(pos)
            val block = state.block
            val meta = block.getMetaFromState(state)
            player.sendMessage(TextComponentTranslation("§e=== Block Property ==="))
            //ブロックの翻訳名をチャットに表示
            player.sendMessage(TextComponentString("§eName:§r§b" + ItemStack(block, 1, meta).displayName))
            //ブロックのIDをチャットに表示
            player.sendMessage(TextComponentString("§eID:§r§b" + block.registryName))
            //ブロックのBlockstateをチャットに表示
            player.sendMessage(TextComponentString("§eBlockstate:§r§b $state"))
            //ブロックのHardnessをチャットに表示
            player.sendMessage(TextComponentString("§eHardness:§r§b" + block.blockHardness))
            //ブロックのResistanceをチャットに表示
            player.sendMessage(TextComponentString("§eResistance:§r§b" + block.blockResistance))
            //適正ツールをチャットに表示
            player.sendMessage(TextComponentString("§eHarvest Tool:§r§b" + block.getHarvestTool(state)))
            //適正レベルをチャットに表示
            player.sendMessage(TextComponentString("§eHarvest Level:§r§b" + block.getHarvestLevel(state)))
            player.sendMessage(TextComponentTranslation("§e=== Block Property ==="))
            return EnumActionResult.SUCCESS
        } else return EnumActionResult.FAIL
    }

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack, tintIndex: Int) = if (tintIndex == 1) RagiMaterials.COLOR else RagiColor.WHITE

}