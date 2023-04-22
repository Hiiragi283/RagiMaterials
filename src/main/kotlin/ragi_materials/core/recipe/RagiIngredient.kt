package ragi_materials.core.recipe

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.oredict.OreDictionary

class RagiIngredient(vararg stacks: ItemStack) : Ingredient(*stacks) {

    constructor(oredict: String) : this(*getOredictStacks(oredict).toTypedArray())

    companion object {
        @JvmStatic
        fun getOredictStacks(oredict: String) = OreDictionary.getOres(oredict).toList()
    }

}