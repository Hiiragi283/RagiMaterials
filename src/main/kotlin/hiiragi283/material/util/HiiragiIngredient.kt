package hiiragi283.material.util

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.oredict.OreDictionary

private fun getOredictStacks(oredict: String): Array<ItemStack> = OreDictionary.getOres(oredict).toTypedArray()

class HiiragiIngredient(vararg stacks: ItemStack) : Ingredient(*stacks) {

    constructor(oredict: String) : this(*getOredictStacks(oredict))

}