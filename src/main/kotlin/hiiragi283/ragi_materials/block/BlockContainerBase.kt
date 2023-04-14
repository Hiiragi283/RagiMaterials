package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.item.ItemBlockBase
import hiiragi283.ragi_materials.tile.TileBase
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class BlockContainerBase<T : TileEntity>(val ID: String, material: Material, val tile: Class<T>, maxTips: Int) : BlockBase(ID, material, maxTips), ITileEntityProvider {

    init {
        GameRegistry.registerTileEntity(tile, ResourceLocation(RagiMaterials.MOD_ID, "te_$ID"))
    }

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        var result = false
        if (hand == EnumHand.MAIN_HAND) {
            val tile = world.getTileEntity(pos)
            result = if (tile !== null && tile is TileBase) tile.onTileActivated(world, pos, player, hand, facing) else false
        }
        return result
    }

    //    IItemBlock    //

    override fun getItemBlock() = ItemBlockBase(this)

    //    ITileEntityProvider    //

    override fun createNewTileEntity(worldIn: World, meta: Int): T = tile.newInstance()

}