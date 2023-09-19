package hiiragi283.material.api.capability.item

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.util.getIntegerOrNull
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class ModuleMachineInputItemHandler(tile: TileEntityModuleMachine) :
    HiiragiItemHandler(6, IOControllable.Type.INPUT, tile) {

    var maxSlots: Int = 1

    //    IItemHandler    //

    override fun isItemValid(slot: Int, stack: ItemStack): Boolean = slot <= maxSlots - 1

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack =
        if (slot < maxSlots) super.insertItem(slot, stack, simulate) else stack

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound =
        super.serializeNBT().also { tag: NBTTagCompound -> tag.setInteger("MaxSlots", maxSlots) }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        super.deserializeNBT(nbt)
        maxSlots = nbt.getIntegerOrNull("MaxSlots") ?: 1
    }

}