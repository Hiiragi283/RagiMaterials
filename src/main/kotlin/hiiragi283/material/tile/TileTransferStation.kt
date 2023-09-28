package hiiragi283.material.tile

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.block.BlockTransferPipe
import hiiragi283.material.util.getTile
import net.minecraft.block.Block
import net.minecraft.block.BlockDirectional
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class TileTransferStation : HiiragiTileEntity.Tickable(100) {

    //    Capability    //

    val inventory = HiiragiItemHandler(1, IOControllable.Type.INPUT, this)

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean =
        capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? =
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(
            HiiragiItemHandlerWrapper(inventory)
        ) else super.getCapability(capability, facing)

    //    Tickable    //e

    private fun getFacing(): EnumFacing = getState().getValue(BlockDirectional.FACING)

    private fun getTerminalPos(): BlockPos {
        val posTo: BlockPos = pos.offset(getState().getValue(BlockDirectional.FACING))
        val stateTo: IBlockState = world.getBlockState(posTo)
        return when (val blockTo: Block = stateTo.block) {
            is BlockTransferPipe -> blockTo.getTerminalPos(world, posTo, stateTo)
            else -> posTo
        }
    }

    private fun getTerminalTile(): TileEntity? = getTile(world, getTerminalPos())

    override fun onUpdateServer() {
        //Extraction
        val extractHandler: IItemHandler = getTerminalTile()
            ?.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getFacing().opposite)
            ?: return
        val canTransfer: Boolean = inventory.transferTo(0, extractHandler, true)
        if (canTransfer) {
            inventory.transferTo(0, extractHandler, false)
        }
    }

}