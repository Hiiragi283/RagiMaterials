package hiiragi283.material.util

import com.cleanroommc.modularui.widgets.slot.ModularSlot
import hiiragi283.api.capability.item.HiiragiItemHandler
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

class ModularSlotOut(itemHandler: HiiragiItemHandler, index: Int) : ModularSlot(itemHandler as IItemHandler, index) {

    override fun isItemValid(stack: ItemStack): Boolean = false

}