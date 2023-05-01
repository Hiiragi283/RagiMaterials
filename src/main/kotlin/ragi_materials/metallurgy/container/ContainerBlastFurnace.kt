package ragi_materials.metallurgy.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler
import ragi_materials.core.container.ContainerBase
import ragi_materials.core.container.SlotOutItemHandler
import ragi_materials.metallurgy.tile.TileBlastFurnaceInterface

class ContainerBlastFurnace(player: EntityPlayer, tile: TileBlastFurnaceInterface) : ContainerBase<TileBlastFurnaceInterface>(player, tile) {

    val inputOre = tile.inputOre
    val inputFuel = tile.inputFuel
    val inputFlux = tile.inputFlux
    val inventory = tile.inventory
    val output = tile.output
    val tank = tile.tank

    init {
        addSlotToContainer(SlotItemHandler(inputOre, 0, 8 + 1 * 18, 20))
        addSlotToContainer(SlotItemHandler(inputFuel, 0, 8 + 2 * 18, 20))
        addSlotToContainer(SlotItemHandler(inputFlux, 0, 8 + 3 * 18, 20))
        addSlotToContainer(SlotOutItemHandler(output, 0, 8 + 6 * 18, 20))
        initSlotsPlayer(51)
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot = slot.stack
            stack = stackSlot.copy()
            when (index) {
                //Ore, Fuel, Flux, Output -> Inventory, Hotbar
                in 0..3 -> if (!mergeItemStack(stackSlot, inventory.slots, inventorySlots.size, true)) return ItemStack.EMPTY
                //Inventory, HotBar -> Ore, Fuel, Flux
                else -> if (!mergeItemStack(stackSlot, 0, 3, false)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}