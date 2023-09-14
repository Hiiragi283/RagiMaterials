package hiiragi283.material.util

import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntComparators
import it.unimi.dsi.fastutil.ints.IntList
import net.minecraft.client.util.RecipeItemHelper
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.NonNullList
import net.minecraftforge.oredict.OreDictionary

class HiiragiIngredient(vararg stacks: ItemStack) : Ingredient() {

    private val stacks: Array<ItemStack>

    init {
        this.stacks = stacks.toList().toTypedArray()
    }

    constructor(vararg oreDicts: String) : this(*oreDicts.flatMap(OreDictionary::getOres).toTypedArray())

    override fun getMatchingStacks(): Array<ItemStack> = stacks

    override fun getValidItemStacksPacked(): IntList {
        val list: IntList = IntArrayList(stacks.size)
        stacks.forEach { stack ->
            if (stack.metadata == OreDictionary.WILDCARD_VALUE) {
                val nonNullList: NonNullList<ItemStack> = NonNullList.create()
                stack.item.getSubItems(CreativeTabs.SEARCH, nonNullList)
                nonNullList.map(RecipeItemHelper::pack).forEach(list::add)
            } else {
                list.add(RecipeItemHelper.pack(stack))
            }
        }
        list.sortWith(IntComparators.NATURAL_COMPARATOR)
        return list
    }

    override fun apply(stack: ItemStack?): Boolean {
        if (stack == null) return false
        stacks.forEach { stackIn: ItemStack ->
            if (OreDictionary.itemMatches(stack, stackIn, false)) {
                return true
            }
        }
        return false
    }

    override fun invalidate() {

    }

    override fun isSimple(): Boolean = true

    companion object {

        fun ofItemStack(stacks: Collection<ItemStack>): HiiragiIngredient  = HiiragiIngredient(*stacks.toTypedArray())

        fun ofOreDict(oreDicts: Collection<String>): HiiragiIngredient =  HiiragiIngredient(*oreDicts.toTypedArray())

    }

}