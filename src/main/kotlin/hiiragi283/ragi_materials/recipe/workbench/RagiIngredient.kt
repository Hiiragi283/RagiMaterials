package hiiragi283.ragi_materials.recipe.workbench

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.oredict.OreDictionary

fun getOredictStacks(oredict: String) = OreDictionary.getOres(oredict).toList()

class RagiIngredient(vararg stacks: ItemStack) : Ingredient(*stacks) {

    constructor(oredict: String) : this(*getOredictStacks(oredict).toTypedArray())

}