package hiiragi283.material.tile

import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.item.HiiragiItemHandler
import hiiragi283.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.api.tileentity.HiiragiProvider
import hiiragi283.api.tileentity.HiiragiTileEntity
import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.getTile
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class TileEntityTest : HiiragiTileEntity.Tickable(20), HiiragiProvider.Inventory {

    private lateinit var input: HiiragiItemHandler

    override fun createInventory(): HiiragiCapabilityProvider<IItemHandler> {
        input = HiiragiItemHandler(3).setTile(this).setIOType(IOType.GENERAL)
        inventory = HiiragiItemHandlerWrapper(input)
        return HiiragiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory)
    }

    override fun onUpdateServer() {
        val retIn: Boolean = getTile<TileEntity>(world, pos.up())
            ?.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN)
            ?.let { input.transferFrom(0, it) } ?: false
        val retOut: Boolean = getTile<TileEntity>(world, pos.down())
            ?.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)
            ?.let { input.transferTo(0, it) } ?: false
        RagiMaterials.LOGGER.info("TransferFrom result: $retIn")
        RagiMaterials.LOGGER.info("transferTo result: $retOut")
    }

}