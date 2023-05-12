package hiiragi283.material.util.wrapper

import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

/**
 * ItemStackとOreDictionaryをたばねたオブジェクト
 */
data class OreItemStack(var oredict: String = "") {

    private val stacks: MutableSet<ItemStackWrapper> = mutableSetOf()

    init {
        toStackWrappers(oredict)
    }

    private fun toStackWrappers(oredict: String) {
        for (stack in OreDictionary.getOres(oredict)) {
            stacks.add(ItemStackWrapper(stack))
        }
    }

    fun addStack(stack: ItemStackWrapper): Boolean = stacks.add(stack)

    fun addStack(stack: ItemStack): Boolean = addStack(ItemStackWrapper(stack))

    fun match(stack: ItemStackWrapper): Boolean = stack in stacks

    fun match(stack: ItemStack): Boolean = ItemStackWrapper(stack) in stacks

    fun match(oredict: String): Boolean = this.oredict == oredict

}