package hiiragi283.material.util

import com.cleanroommc.modularui.widgets.slot.ModularSlot
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

class ModularSlotOut(itemHandler: IItemHandler, index: Int, isPhantom: Boolean = false) : ModularSlot(itemHandler, index, isPhantom) {

    override fun isItemValid(stack: ItemStack): Boolean = false

}