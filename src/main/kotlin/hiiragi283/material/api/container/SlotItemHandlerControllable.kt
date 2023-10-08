package hiiragi283.material.api.container

import hiiragi283.material.api.capability.item.HiiragiItemHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler

open class SlotItemHandlerControllable(
    private val itemHandler: HiiragiItemHandler,
    index: Int,
    xPosition: Int,
    yPosition: Int
) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {

    override fun isItemValid(stack: ItemStack): Boolean = itemHandler.ioType.canInsert

    override fun canTakeStack(playerIn: EntityPlayer): Boolean = itemHandler.ioType.canExtract

}