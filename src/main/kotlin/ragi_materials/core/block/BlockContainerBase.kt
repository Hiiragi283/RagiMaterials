package ragi_materials.core.block

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import ragi_materials.core.RagiMaterials
import ragi_materials.core.item.ItemBlockBase
import ragi_materials.core.tile.TileBase

abstract class BlockContainerBase<T : TileBase>(ID: String, material: Material, val tile: Class<T>, maxTips: Int) : BlockBase(ID, material, maxTips), ITileEntityProvider {

    override val itemBlock: ItemBlock? = ItemBlockBase(this)

    init {
        GameRegistry.registerTileEntity(tile, ResourceLocation(RagiMaterials.MOD_ID, "te_$ID"))
    }

    //    General    //

    override fun hasTileEntity(state: IBlockState) = true

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null) removeTile(tile as T, world, pos, state)
        super.breakBlock(world, pos, state)
    }

    open fun removeTile(tile: T, world: World, pos: BlockPos, state: IBlockState) {}

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        var result = false
        if (hand == EnumHand.MAIN_HAND) {
            val tile = world.getTileEntity(pos)
            result = if (tile !== null && tile is TileBase) tile.onTileActivated(world, pos, player, hand, facing) else false
        }
        return result
    }

    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tile = world.getTileEntity(pos)
        if (tile !== null) onTilePlaced(tile as T, world, pos, state, placer, stack)
        super.onBlockPlacedBy(world, pos, state, placer, stack)
    }

    open fun onTilePlaced(tile: T, world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {}

    //    ITileEntityProvider    //

    override fun createNewTileEntity(worldIn: World, meta: Int): T = tile.newInstance()

}