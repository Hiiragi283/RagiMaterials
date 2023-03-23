package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.api.item.IItemStack
import net.minecraft.item.ItemStack

object RussellUtil {

    //IItemStack -> ItemStack
    fun getStack(iStack: IItemStack): ItemStack {
        val stack = iStack.internal
        return if (stack is ItemStack) stack else ItemStack.EMPTY
    }

}