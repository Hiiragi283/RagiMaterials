package ragi_materials.core.block

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.dropItem
import java.util.*

//素材を保持するブロック用のクラス
abstract class BlockContainerBaseMaterial<T : TileBase>(ID: String, material: Material, tile: Class<T>, maxTips: Int) : BlockContainerBase<T>(ID, material, tile, maxTips) {

    //    General    //

    override fun quantityDropped(random: Random): Int = 0 //デフォルトのドロップはなし

    //    Event    //

    override fun removeTile(tile: T, world: World, pos: BlockPos, state: IBlockState) {
        val metadata = this.damageDropped(state)
        val stack = ItemStack(this, 1, metadata)
        stack.tagCompound = tile.material.writeToNBT(stack.tagCompound) //NBTタグを引き継ぐ
        dropItem(world, pos, stack, 0.0, 0.25, 0.0)
    }

    override fun onTilePlaced(tile: T, world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        stack.tagCompound?.let { tile.material = RagiMaterial.readFromNBT(it) }
    }
}