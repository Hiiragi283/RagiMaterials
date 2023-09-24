package hiiragi283.material.util

import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntList
import net.minecraft.client.util.RecipeItemHelper
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraftforge.oredict.OreDictionary

class OreDictIngredient(private val oreDict: String) : Ingredient(0) {

    override fun getMatchingStacks(): Array<ItemStack> {
        val nonNullList: NonNullList<ItemStack> = NonNullList.create()
        OreDictionary.getOres(oreDict).map(ItemStack::copy).forEach { stack: ItemStack ->
            if (stack.metadata == OreDictionary.WILDCARD_VALUE) {
                stack.item.getSubItems(CreativeTabs.SEARCH, nonNullList)
            } else {
                nonNullList.add(stack)
            }
        }
        return nonNullList.toTypedArray()
    }

    override fun getValidItemStacksPacked(): IntList {
        val intList = IntArrayList()
        OreDictionary.getOres(oreDict).map(ItemStack::copy).forEach { stack: ItemStack ->
            if (stack.metadata == OreDictionary.WILDCARD_VALUE) {
                val nonNullList: NonNullList<ItemStack> = NonNullList.create()
                stack.item.getSubItems(CreativeTabs.SEARCH, nonNullList)
                nonNullList.map(RecipeItemHelper::pack).forEach(intList::add)
            } else {
                intList.add(RecipeItemHelper.pack(stack))
            }
        }
        return intList
    }

    override fun apply(input: ItemStack?): Boolean {
        if (input == null) return false
        OreDictionary.getOres(oreDict).map(ItemStack::copy).forEach { stack: ItemStack ->
            if (OreDictionary.itemMatches(stack, input, false)) {
                return true
            }
        }
        return false
    }

    override fun isSimple(): Boolean = true

}