package hiiragi283.api.capability.item

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants

open class HiiragiItemStackItemHandler(
    private val container: ItemStack,
    size: Int,
    stackDefault: ItemStack
) : HiiragiItemHandler(size, stackDefault) {

    override fun getStackInSlot(slot: Int): ItemStack {
        validateSlotIndex(slot)
        val tagItem: NBTTagCompound = getItemList().get(slot) as NBTTagCompound
        return if (tagItem.isEmpty) ItemStack.EMPTY else ItemStack(tagItem)
    }

    override fun setStackInSlot(slot: Int, stack: ItemStack) {
        validateSlotIndex(slot)
        getItemList().set(slot, if (stack.isEmpty) NBTTagCompound() else stack.serializeNBT())
    }

    open fun getItemList(): NBTTagList {
        val tag: NBTTagCompound = container.tagCompound ?: NBTTagCompound()
        if (!tag.hasKey("Items")) {
            val tagList = NBTTagList()
            (0 until slots).forEach { tagList.appendTag(stackDefault.serializeNBT()) }
            tag.setTag("Items", tagList)
        }
        container.tagCompound = tag
        return tag.getTagList("Items", Constants.NBT.TAG_COMPOUND)

    }

}