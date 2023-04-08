package hiiragi283.ragi_materials.container

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

class SlotOutItemHandler(itemHandler: IItemHandler, index: Int, x: Int, y: Int): SlotItemHandler(itemHandler, index, x, y) {

    //搬出専用なので搬入不可能
    override fun isItemValid(stack: ItemStack) = false

}