package hiiragi283.material.container

import hiiragi283.material.api.tile.TileEntityModuleMachine
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

class SlotModuleMachine(
    private val tile: TileEntityModuleMachine,
    itemHandler: IItemHandler,
    index: Int,
    xPosition: Int,
    yPosition: Int
) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {
    override fun isItemValid(stack: ItemStack): Boolean = tile.machineProperty.getItemSlots() >= slotIndex + 1

}