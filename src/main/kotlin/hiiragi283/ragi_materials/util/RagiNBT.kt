package hiiragi283.ragi_materials.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

object RagiNBT {

    //stackに名前を設定するメソッド
    fun setName(stack: ItemStack, name: String?): ItemStack {
        val nbtBase = NBTTagCompound()
        val nbtName = NBTTagCompound()
        nbtName.setString("Name", name.toString())
        nbtBase.setTag("display", nbtName)
        stack.tagCompound = nbtBase
        return stack
    }

    //stackに翻訳した名前を設定するメソッド
    fun setLocName(stack: ItemStack, name: String?): ItemStack {
        val nbtBase = NBTTagCompound()
        val nbtName = NBTTagCompound()
        nbtName.setString("LocName", name.toString())
        nbtBase.setTag("display", nbtName)
        stack.tagCompound = nbtBase
        return stack
    }
}