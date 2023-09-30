package hiiragi283.material.tile

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.api.tile.ITransferStation
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.transferTo
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class TileTransferStationItem : HiiragiTileEntity.Tickable(100), ITransferStation {

    override fun getDisplayName() = TextComponentTranslation("tile.ragi_materials.station_item.name")

    //    NBT    //

    override fun readFromNBT(compound: NBTTagCompound) {
        inventory.deserializeNBT(compound.getCompoundTag(HiiragiNBTUtil.INVENTORY))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag(HiiragiNBTUtil.INVENTORY, inventory.serializeNBT())
        return super.writeToNBT(compound)
    }

    //    Capability    //

    val inventory = HiiragiItemHandler(1, IOControllable.Type.INPUT, this)

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean =
        capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? =
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(
            HiiragiItemHandlerWrapper(inventory)
        ) else super.getCapability(capability, facing)

    //    Tickable    //

    override fun onUpdateServer() {
        val iItemHandler: IItemHandler = getTerminalTile(world, pos)
            ?.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getFacing(getState()).opposite)
            ?: return
        val canTransfer: Boolean = inventory.transferTo(0, iItemHandler, true)
        if (canTransfer) {
            inventory.transferTo(0, iItemHandler, false)
        }
    }

}