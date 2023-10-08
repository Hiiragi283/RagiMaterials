package hiiragi283.material.api.block

import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.getTile
import hiiragi283.material.util.itemStack
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World


open class HiiragiBlockContainer<T : HiiragiTileEntity>(
    material: Material,
    id: String,
    private val tileSupplier: () -> T
) : HiiragiBlock(material, id), ITileEntityProvider {

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        getTile<HiiragiTileEntity>(world, pos)?.onTileRemoved(world, pos, state)
        super.breakBlock(world, pos, state)
    }

    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        if (hand == EnumHand.MAIN_HAND) {
            return getTile<HiiragiTileEntity>(world, pos)?.onTileActivated(
                world,
                pos,
                player,
                hand,
                facing
            ) ?: false
        }
        return false
    }

    override fun onBlockPlacedBy(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
        getTile<HiiragiTileEntity>(world, pos)?.onTilePlaced(world, pos, state, placer, stack)
        super.onBlockPlacedBy(world, pos, state, placer, stack)
    }

    //    ITileEntityProvider    //

    override fun createNewTileEntity(worldIn: World, meta: Int): T = tileSupplier()

    //    Holdable    //

    open class Holdable<T : HiiragiTileEntity>(material: Material, id: String, tileSupplier: () -> T) :
        HiiragiBlockContainer<T>(material, id, tileSupplier) {

        //    General    //

        override fun getDrops(
            drops: NonNullList<ItemStack>,
            world: IBlockAccess,
            pos: BlockPos,
            state: IBlockState,
            fortune: Int
        ) {
            drops.add(getStackWithTileNBT(world, pos))
        }

        override fun getPickBlock(
            state: IBlockState,
            target: RayTraceResult,
            world: World,
            pos: BlockPos,
            player: EntityPlayer
        ): ItemStack {
            return getStackWithTileNBT(world, pos)
        }

        open fun getStackWithTileNBT(
            world: IBlockAccess?,
            pos: BlockPos?,
            stack: ItemStack = itemStack()
        ): ItemStack {
            getTile<HiiragiTileEntity>(world, pos)?.let { tile ->
                stack.getOrCreateSubCompound(HiiragiNBTUtil.BLOCK_ENTITY_TAG).merge(getTileNBT(tile))
            }
            return stack
        }

        open fun <T : HiiragiTileEntity> getTileNBT(tile: T): NBTTagCompound = tile.getUpdateTag()

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
            return if (willHarvest) true else super.removedByPlayer(state, world, pos, player, willHarvest)
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

}