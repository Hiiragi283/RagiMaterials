package hiiragi283.ragi_materials.main.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

object RagiNBT {
    //Safety Mimicを生成するメソッド
    fun chestMimic(): ItemStack {
        val chest = RagiUtils.getStack("minecraft:chest", 1, 0)
        val nbtLoot = NBTTagCompound()
        nbtLoot.setString("LootTable", "artifacts:mimic_underground")
        val nbtName = NBTTagCompound()
        nbtName.setString("LocName", "item.safety_mimic.name")
        val nbt = NBTTagCompound()
        nbt.setTag("BlockEntityTag", nbtLoot)
        nbt.setTag("display", nbtName)
        chest.tagCompound = nbt
        return chest
    }

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