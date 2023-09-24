package hiiragi283.material.util

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

inline fun <reified T> IBlockState.getBlockImplemented(): T? = this.block as? T

inline fun <reified T> ItemStack.getItemImplemented(): T? = this.item as? T

inline fun <reified T> IBlockState.isBlockImplemented(): Boolean = this.block is T

inline fun <reified T> ItemStack.isItemImplemented(): Boolean = this.item is T

inline fun <reified T : TileEntity> getTile(world: IBlockAccess?, pos: BlockPos?): T? =
    pos?.let { world?.getTileEntity(it) } as? T