package ragi_materials.core.material.drop

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IMaterialDrop {

    fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean)

    fun addDrops(drops: MutableList<ItemStack>, stack: ItemStack) {
        drops.add(stack)
    }

    fun addWeightedDrop(drops: MutableList<ItemStack>, world: World, stack: ItemStack, weight: Int) {
        if (world.rand.nextInt(0.coerceAtLeast(weight)) == 0) drops.add(stack)
    }
}