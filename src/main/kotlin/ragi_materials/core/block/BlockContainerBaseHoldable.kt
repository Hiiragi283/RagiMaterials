package ragi_materials.core.block

import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.dropItem
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

//NBTタグを保持するブロック用のクラス
abstract class BlockContainerBaseHoldable<T : TileBase>(ID: String, material: Material, tile: Class<T>, maxTips: Int) : BlockContainerBase<T>(ID, material, tile, maxTips) {

    //    General    //

    override fun quantityDropped(random: Random): Int = 0 //デフォルトのドロップはなし

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null) moveToStack(tile, world, pos, state)
        super.breakBlock(world, pos, state)
    }

    private fun moveToStack(tile: TileEntity, world: World, pos: BlockPos, state: IBlockState) {
        val metadata = this.damageDropped(state)
        val stack = ItemStack(this, 1, metadata)
        stack.tagCompound = tile.updateTag //NBTタグを引き継ぐ
        dropItem(world, pos, stack, 0.0, 0.25, 0.0)
    }

    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tile = world.getTileEntity(pos)
        if (tile !== null) moveFromStack(tile, stack)
        super.onBlockPlacedBy(world, pos, state, placer, stack)
    }

    private fun moveFromStack(tile: TileEntity, stack: ItemStack) {
        if (stack.tagCompound !== null) {
            val tag = stack.tagCompound!!.also {
                it.setInteger("x", tile.pos.x)
                it.setInteger("y", tile.pos.y)
                it.setInteger("z", tile.pos.z)
            }
            tile.readFromNBT(tag) //NBTタグから読み込む
        }
    }
}