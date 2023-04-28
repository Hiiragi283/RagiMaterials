package ragi_materials.core.block

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.tile.TileBase
import java.util.*

//素材を保持するブロック用のクラス
abstract class BlockContainerBaseMaterial<T : TileBase>(ID: String, material: Material, tile: Class<T>, maxTips: Int) : BlockContainerBase<T>(ID, material, tile, maxTips) {

    //    General    //

    override fun quantityDropped(random: Random): Int = 0 //デフォルトのドロップはなし

    //    Event    //

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        //デフォルトのドロップ品を生成
        val rand = if (world is World) world.rand else RANDOM
        val item = getItemDropped(state, rand, fortune)
        val meta = damageDropped(state)
        val stack = ItemStack(item, 1, meta)
        //タイルエンティティが存在する場合，そのNBTタグを引き継ぐ
        world.getTileEntity(pos)?.let { if (it is TileBase) stack.tagCompound = it.material.writeToNBT(stack.tagCompound) }
        //ドロップ品をdropsに追加
        drops.add(stack)
    }

    override fun onTilePlaced(tile: T, world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        stack.tagCompound?.let { tile.material = RagiMaterial.readFromNBT(it) }
    }
}