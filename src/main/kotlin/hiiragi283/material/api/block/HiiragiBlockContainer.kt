package hiiragi283.material.api.block

import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.getTile
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class HiiragiBlockContainer<T : HiiragiTileEntity>(
    material: Material,
    id: String,
    val tile: Class<T>
) : HiiragiBlock(material, id), ITileEntityProvider {

    fun registerTileEntity() {
        GameRegistry.registerTileEntity(tile, hiiragiLocation("te_${registryName?.path}"))
    }

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

    override fun createNewTileEntity(worldIn: World, meta: Int): T = tile.newInstance()

    //    Holdable    //

    abstract class Holdable<T : HiiragiTileEntity>(material: Material, id: String, tile: Class<T>) :
        HiiragiBlockContainer<T>(material, id, tile) {

        //    General    //

        override fun getDrops(
            drops: NonNullList<ItemStack>,
            world: IBlockAccess,
            pos: BlockPos,
            state: IBlockState,
            fortune: Int
        ) {
            val stack = ItemStack(this)
            getTile<HiiragiTileEntity>(world, pos)?.let {
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