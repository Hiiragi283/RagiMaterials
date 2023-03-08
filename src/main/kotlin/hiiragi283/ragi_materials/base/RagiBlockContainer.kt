package hiiragi283.ragi_materials.base

import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

//NBTタグを保持するブロック用のクラス
abstract class RagiBlockContainer<T : TileEntity>(id: String, material: Material, tips: Int) : BlockContainerBase(id, material, tips) {

    //    General    //
    override fun quantityDropped(random: Random): Int = 0 //デフォルトのドロップはなし

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null) moveToStack(tile as T, world, pos, state)
        super.breakBlock(world, pos, state)
    }

    private fun moveToStack(tile: T, world: World, pos: BlockPos, state: IBlockState) {
        val metadata = this.damageDropped(state)
        val stack = ItemStack(this, 1, metadata)
        stack.tagCompound = tile.updateTag //NBTタグを引き継ぐ
        RagiUtil.dropItem(world, pos, stack)
    }

    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tile = world.getTileEntity(pos)
        if (tile !== null) moveFromStack(tile as T, stack)
        super.onBlockPlacedBy(world, pos, state, placer, stack)
    }

    private fun moveFromStack(tile: T, stack: ItemStack) {
        if (stack.tagCompound !== null) tile.readFromNBT(stack.tagCompound!!) //NBTタグから読み込む
    }
}