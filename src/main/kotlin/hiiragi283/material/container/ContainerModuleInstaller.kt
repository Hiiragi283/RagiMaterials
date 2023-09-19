package hiiragi283.material.container

import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.container.SlotOutputItemHandler
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.api.module.IRecipeModuleItem
import hiiragi283.material.item.MaterialItemBlockCasing
import hiiragi283.material.util.dropInventoryItems
import hiiragi283.material.util.isItemImplemented
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler

class ContainerModuleInstaller(player: EntityPlayer) : HiiragiContainer(player) {

    val itemHandler = HiiragiItemHandler(4)

    init {
        addSlotToContainer(object : SlotItemHandler(itemHandler, 0, getSlotPosX(2), getSlotPosY(1)) {
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<MaterialItemBlockCasing>()
        })
        addSlotToContainer(object : SlotItemHandler(itemHandler, 1, getSlotPosX(3), getSlotPosY(1)) {
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<IRecipeModuleItem>()
        })
        addSlotToContainer(object : SlotItemHandler(itemHandler, 2, getSlotPosX(4), getSlotPosY(1)) {
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<IModuleItem>()
        })
        addSlotToContainer(SlotOutputItemHandler(itemHandler, 3, getSlotPosX(6), getSlotPosY(1)))
        initSlotsPlayer(84)
    }

    override fun transferStackInSlot(player: EntityPlayer, index: Int): ItemStack {
        var stack: ItemStack = ItemStack.EMPTY
        val slot: Slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot: ItemStack = slot.stack
            stack = stackSlot.copy()
            if (index in (0..3)) {
                if (!mergeItemStack(stackSlot, 4, inventorySlots.size, true)) return ItemStack.EMPTY
            } else {
                if (!mergeItemStack(stackSlot, 0, 3, true)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) {
                slot.putStack(ItemStack.EMPTY)
            } else slot.onSlotChanged()
        }
        return stack
    }

    override fun onContainerClosed(player: EntityPlayer) {
        super.onContainerClosed(player)
        dropInventoryItems(player.world, player.position, itemHandler)
    }

}