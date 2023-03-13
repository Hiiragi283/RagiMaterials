package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.tile.TileIndustrialLabo
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.InventoryHelper
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockIndustrialLabo : BlockContainerBase("industrial_labo", Material.IRON, 2) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 3)
        soundType = SoundType.METAL
    }

    //    General    //
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileIndustrialLabo) InventoryHelper.dropInventoryItems(world, pos, tile.inventory)
        super.breakBlock(world, pos, state)
    }

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (!world.isRemote) {
            player.openGui(RagiMaterials, 0, world, pos.x, pos.y, pos.z)
            RagiLogger.infoDebug("GUI opened!")
        }
        return true
    }

    //    Tile Entity    //

    override fun createNewTileEntity(world: World, meta: Int): TileEntity = TileIndustrialLabo()

}