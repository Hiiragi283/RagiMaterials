package hiiragi283.ragi_materials.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentString

class RagiInventory(val title: String, val slots: Int) : IInventory {

    val inventory: NonNullList<ItemStack> = NonNullList.withSize(slots, ItemStack.EMPTY)

    override fun getName(): String {
        return this.title
    }

    override fun hasCustomName(): Boolean {
        return false
    }

    override fun getDisplayName(): ITextComponent {
        return TextComponentString(this.name)
    }

    override fun getSizeInventory(): Int {
        return this.slots
    }

    override fun isEmpty(): Boolean {
        for (stack in this.inventory) {
            if (!stack.isEmpty) return false
        }
        return true
    }

    override fun getStackInSlot(index: Int): ItemStack {
        //indexが0以上inventory.size未満の場合，inventoryから取り出す
        return if (index in 0 until this.inventory.size) this.inventory[index] else ItemStack.EMPTY
    }

    override fun decrStackSize(index: Int, count: Int): ItemStack {
        val stack = ItemStackHelper.getAndSplit(this.inventory, index, count)
        if (!stack.isEmpty) markDirty()
        return stack
    }

    override fun removeStackFromSlot(index: Int): ItemStack {
        val stack = this.inventory[index]
        return if (stack.isEmpty) ItemStack.EMPTY else {
            this.inventory[index] = ItemStack.EMPTY
            stack
        }
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        this.inventory[index] = stack
        if (!stack.isEmpty && stack.count > this.inventoryStackLimit) stack.count = this.inventoryStackLimit
        markDirty()
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun markDirty() {}

    override fun isUsableByPlayer(player: EntityPlayer): Boolean {
        return true
    }

    override fun openInventory(player: EntityPlayer) {}

    override fun closeInventory(player: EntityPlayer) {}

    override fun isItemValidForSlot(index: Int, stack: ItemStack): Boolean {
        return true
    }

    override fun getField(id: Int): Int {
        return 0
    }

    override fun setField(id: Int, value: Int) {}

    override fun getFieldCount(): Int {
        return 0
    }

    override fun clear() {
        this.inventory.clear()
    }
}