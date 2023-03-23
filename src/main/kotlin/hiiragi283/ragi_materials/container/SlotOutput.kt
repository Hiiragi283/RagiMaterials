package hiiragi283.ragi_materials.container

import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

class SlotOutput(inventory: IInventory, index: Int, x: Int, y: Int): Slot(inventory, index, x, y) {

    //搬出専用なので搬入不可能
    override fun isItemValid(stack: ItemStack) = false

}