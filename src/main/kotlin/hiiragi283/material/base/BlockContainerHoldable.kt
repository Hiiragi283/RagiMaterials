package hiiragi283.material.base

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

abstract class BlockContainerHoldable<T : TileBase>(ID: String, material: Material, tile: Class<T>, maxTips: Int) :
    BlockContainerBase<T>(ID, material, tile, maxTips) {

    //    General    //

    override fun getDrops(
        drops: NonNullList<ItemStack>,
        world: IBlockAccess,
        pos: BlockPos,
        state: IBlockState,
        fortune: Int
    ) {
        val stack = ItemStack(this)
        world.getTileEntity(pos)?.let {
            stack.getOrCreateSubCompound("BlockEntityTag").merge(it.updateTag)
        }
        drops.add(stack)
    }

    /**
     * Reference: net.minecraft.block.BlockFlowerPot
     */

    override fun removedByPlayer(
        state: IBlockState,
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        willHarvest: Boolean
    ): Boolean {
        //破壊のタイミングを遅らせる
        return if (willHarvest) true else super.removedByPlayer(state, world, pos, player, false)
    }

    override fun harvestBlock(
        world: World,
        player: EntityPlayer,
        pos: BlockPos,
        state: IBlockState,
        te: TileEntity?,
        tool: ItemStack
    ) {
        super.harvestBlock(world, player, pos, state, te, tool)
        world.setBlockToAir(pos)
    }

}