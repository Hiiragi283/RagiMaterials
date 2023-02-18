package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.common.IPlantable

open class ItemSeedPeat(ID: String): ItemBase(Reference.MOD_ID, ID, 0), IPlantable {

    //植物を取得するメソッド
    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState {
        return RagiInit.BlockCropPeat.defaultState
    }

    //植物の種類を取得するメソッド
    override fun getPlantType(world: IBlockAccess, pos: BlockPos): EnumPlantType {
        return EnumPlantType.Crop //作物
    }

    //アイテムをブロックに対して右クリックすると呼ばれるメソッド
    override fun onItemUse(
        player: EntityPlayer,
        world: World,
        pos: BlockPos,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): EnumActionResult {
        //変数の宣言
        val stack = player.getHeldItem(hand)
        val state = world.getBlockState(pos)
        //クリックしたブロックが植物を維持可能，かつそのブロックの上が空気の場合
        return if (state.block.canSustainPlant(state, world, pos, EnumFacing.UP, this) && world.isAirBlock(pos.up())
        ) {
            world.setBlockState(pos.up(), getPlant(world, pos)) //植える
            stack.shrink(1) //アイテムを1つ減らす
            EnumActionResult.SUCCESS //実験は成功だ!
        } else {
            EnumActionResult.FAIL //何が間違っていたのだろうか
        }
    }
}