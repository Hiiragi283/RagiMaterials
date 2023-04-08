package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemResonatingFork : ItemBase(Reference.MOD_ID, "resonating_fork", 0) {

    init {
        setMaxStackSize(1)
    }

    //    Event    //

    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        /*val stack = player.getHeldItem(hand)
        //プレイヤーがスニーク中の場合，座標を取得する
        if (player.isSneaking) {
            val tag = stack.tagCompound ?: NBTTagCompound()
            tag.setInteger("toX", pos.x)
            tag.setInteger("toY", pos.y)
            tag.setInteger("toZ", pos.z)
            stack.tagCompound = tag
            result = EnumActionResult.SUCCESS
        }
        //スニークしていない場合は，アンテナに座標を書き込む
        else {
            val tile = world.getTileEntity(pos)
            if (tile !== null && tile is TileQuartzAntenna) {
                //NBTタグがある場合，転送先の座標を取得
                stack.tagCompound?.let { tag ->
                    val toX = if (tag.hasKey("toX")) tag.getInteger("toX") else 0
                    val toY = if (tag.hasKey("toY")) tag.getInteger("toY") else 0
                    val toZ = if (tag.hasKey("toZ")) tag.getInteger("toZ") else 0
                    tile.posTo = BlockPos(toX, toY, toZ) //転送先の座標を代入
                    result = EnumActionResult.SUCCESS
                }
            }
        }*/
        return EnumActionResult.FAIL
    }

}