package hiiragi283.material.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.oredict.OreDictionary
import java.util.function.Predicate

sealed class HiiragiIngredient(val count: Int = 1) : Predicate<ItemStack> {

    abstract fun getMatchingStacks(): Collection<ItemStack>

    open fun onProcess(inventory: IItemHandlerModifiable, index: Int) {
        inventory.extractItem(index, count, false)
    }

    //    Predicate    //

    override fun test(t: ItemStack): Boolean {
        getMatchingStacks().forEach { stack ->
            if (stack.isSameWithoutCount(t) && t.count >= count) {
                return true
            }
        }
        return false
    }

    //    ItemStack    //

    class Stacks(vararg stacks: ItemStack, count: Int = 1) : HiiragiIngredient(count) {

        private val stacks: List<ItemStack> = stacks.map {
            it.count = count
            return@map it
        }

        override fun getMatchingStacks(): Collection<ItemStack> = stacks

    }

    //    Block    //

    class Blocks(vararg blocks: Block, count: Int = 1) : HiiragiIngredient(count) {

        private val blocks: List<Block> = blocks.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = blocks.map { ItemStack(it, count, 0) }

        override fun test(t: ItemStack): Boolean {
            val blockT: Block = (t.item as? ItemBlock)?.block ?: return false
            blocks.forEach { block: Block ->
                if (blockT == block && t.count >= count) {
                    return true
                }
            }
            return false
        }

    }

    //    Item    //

    class Items(vararg items: Item, count: Int = 1) : HiiragiIngredient(count) {

        private val items: List<Item> = items.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = items.map { ItemStack(it, count, 0) }

    }

    //    Ore Dictionary    //

    class OreDicts(vararg oreDicts: String, count: Int = 1) : HiiragiIngredient(count) {

        private val oreDicts: List<String> = oreDicts.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = oreDicts.flatMap(OreDictionary::getOres)
            .map { stack: ItemStack ->
                stack.count = count
                return@map stack
            }

    }

    //    HiiragiPart    //

    class Parts(private val part: HiiragiPart, count: Int = 1) : HiiragiIngredient(count) {

        constructor(shape: HiiragiShape, material: HiiragiMaterial, count: Int = 1) : this(
            HiiragiPart(shape, material),
            count
        )

        override fun getMatchingStacks(): Collection<ItemStack> = part.getAllItemStack(count)

    }

    //    Custom    //

    class Custom(
        val stacks: () -> Collection<ItemStack>,
        val predicate: (stack: ItemStack) -> Boolean,
        val process: (inventory: IItemHandlerModifiable, index: Int) -> Unit,
        count: Int = 1
    ) : HiiragiIngredient(count) {

        override fun getMatchingStacks(): Collection<ItemStack> = stacks()

        override fun test(t: ItemStack): Boolean = predicate(t)

        override fun onProcess(inventory: IItemHandlerModifiable, index: Int) = process(inventory, index)

    }

    companion object {

        val TOOL_PROCESS: (IItemHandlerModifiable, Int) -> Unit = { inventory: IItemHandlerModifiable, index: Int ->
            val tool: ItemStack = inventory.getStackInSlot(index)
            if (tool.itemDamage >= tool.maxDamage) {
                inventory.setStackInSlot(index, ItemStack.EMPTY)
            } else {
                tool.itemDamage += 1
            }
        }

    }

}