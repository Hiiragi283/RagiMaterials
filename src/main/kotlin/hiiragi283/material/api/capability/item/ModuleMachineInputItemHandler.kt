package hiiragi283.material.api.capability.item

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.tile.TileEntityModuleMachine
import net.minecraft.item.ItemStack

class ModuleMachineInputItemHandler(tile: TileEntityModuleMachine) :
    HiiragiItemHandler(6, IOControllable.Type.INPUT, tile) {

    var maxSlots: Int = 1

    //    IItemHandler    //

    override fun isItemValid(slot: Int, stack: ItemStack): Boolean = slot <= maxSlots - 1

    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack =
        if (slot <= maxSlots) super.insertItem(slot, stack, simulate) else stack

}