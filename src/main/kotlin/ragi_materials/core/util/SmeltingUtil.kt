@file:JvmName("SmeltingUtil")

package ragi_materials.core.util

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.fml.common.registry.GameRegistry
import ragi_materials.core.util.wrapper.ItemStackWrapper

//かまどレシピを追加するメソッド
fun addSmelting(output: ItemStack, input: ItemStack, xp: Float = 0f) {
    GameRegistry.addSmelting(input, output, xp)
}

//かまどレシピを削除するメソッド
fun removeSmeltingInput(input: ItemStack) {
    val registry = FurnaceRecipes.instance().smeltingList
    val iterator = registry.keys.iterator()
    while (iterator.hasNext()) {
        if (ItemStackWrapper(iterator.next()) == ItemStackWrapper(input)) {
            iterator.remove()
            break
        }
    }
}

fun removeSmeltingOutput(output: ItemStack) {
    val registry = FurnaceRecipes.instance().smeltingList
    val iterator = registry.values.iterator()
    while (iterator.hasNext()) {
        if (ItemStackWrapper(iterator.next()) == ItemStackWrapper(output)) {
            iterator.remove()
            break
        }
    }
}