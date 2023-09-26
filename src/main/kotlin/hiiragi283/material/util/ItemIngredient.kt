package hiiragi283.material.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.getParts
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.oredict.OreDictionary
import java.util.function.Predicate

sealed class ItemIngredient(val count: Int = 1) : Predicate<ItemStack> {

    abstract fun getMatchingStacks(): Collection<ItemStack>

    open fun onProcess(inventory: IItemHandlerModifiable, index: Int) {
        inventory.extractItem(index, count, false)
    }

    //    Empty    //

    object EMPTY : ItemIngredient() {

        override fun getMatchingStacks(): Collection<ItemStack> = listOf()

        override fun test(t: ItemStack): Boolean = t.isEmpty

    }

    //    ItemStack    //

    class Stacks(vararg stacks: ItemStack, count: Int = 1) : ItemIngredient(count) {

        private val stacks: List<ItemStack> = stacks.map {
            it.count = count
            return@map it
        }

        override fun getMatchingStacks(): Collection<ItemStack> = stacks

        override fun test(t: ItemStack): Boolean =
            getMatchingStacks().any { stack -> stack.isSameWithoutCount(t) && t.count >= count }

    }

    //    Block    //

    class Blocks(vararg blocks: Block, count: Int = 1) : ItemIngredient(count) {

        private val blocks: List<Block> = blocks.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = blocks.map { ItemStack(it, count, 0) }

        override fun test(t: ItemStack): Boolean {
            val blockT: Block = (t.item as? ItemBlock)?.block ?: return false
            return blocks.any { block: Block -> blockT == block && t.count >= count }
        }

    }

    //    Item    //

    class Items(vararg items: Item, count: Int = 1) : ItemIngredient(count) {

        private val items: List<Item> = items.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = items.map { ItemStack(it, count, 0) }

        override fun test(t: ItemStack): Boolean = items.any { item: Item -> t.item == item && t.count >= count }

    }

    //    Ore Dictionary    //

    class OreDicts(vararg oreDicts: String, count: Int = 1) : ItemIngredient(count) {

        private val oreDicts: List<String> = oreDicts.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = oreDicts.flatMap(OreDictionary::getOres)
            .map(ItemStack::copy)
            .map { stack: ItemStack ->
                stack.count = count
                return@map stack
            }

        override fun test(t: ItemStack): Boolean = oreDicts.any { oreDict: String -> oreDict in t.getOreDicts() }

    }

    //    HiiragiPart    //

    class Parts(private val part: HiiragiPart, count: Int = 1) : ItemIngredient(count) {

        constructor(shape: HiiragiShape, material: HiiragiMaterial, count: Int = 1) : this(
            HiiragiPart(shape, material),
            count
        )

        override fun getMatchingStacks(): Collection<ItemStack> = part.getItemStacks(count)

        override fun test(t: ItemStack): Boolean = part in t.getParts()

    }

    //    HiiragiMaterial    //

    class Materials(private val material: HiiragiMaterial, count: Int = 1) : ItemIngredient(count) {

        override fun getMatchingStacks(): Collection<ItemStack> = material.getItemStacks()

        override fun test(t: ItemStack): Boolean = material in t.getParts().map(HiiragiPart::material)

    }

    //    HiiragiShape    //

    class Shapes(private val shape: HiiragiShape, count: Int = 1) : ItemIngredient(count) {

        override fun getMatchingStacks(): Collection<ItemStack> = shape.getItemStacks()

        override fun test(t: ItemStack): Boolean = shape in t.getParts().map(HiiragiPart::shape)

    }

    //    Custom    //

    class Custom(
        count: Int = 1,
        val stacks: () -> Collection<ItemStack>,
        val predicate: (ItemStack) -> Boolean,
        val process: (IItemHandlerModifiable, Int) -> Unit = { inventory: IItemHandlerModifiable, index: Int ->
            inventory.extractItem(index, count, false)
        }
    ) : ItemIngredient(count) {

        override fun getMatchingStacks(): Collection<ItemStack> = stacks()

        override fun test(t: ItemStack): Boolean = predicate(t)

        override fun onProcess(inventory: IItemHandlerModifiable, index: Int) = process(inventory, index)

    }

    companion object {

        val CATALYST_PROCESS: (IItemHandlerModifiable, Int) -> Unit = { _: IItemHandlerModifiable, _: Int -> }

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