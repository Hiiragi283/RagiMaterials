package ragi_materials.core.material

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IMaterialDropBlock {

    fun getDrops(drops: MutableList<ItemStack>, world: World, pos: BlockPos, state: IBlockState, fortune: Int, player: EntityPlayer, isSilk: Boolean)

}