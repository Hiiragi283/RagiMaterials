package hiiragi283.material.api.ingredient

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.*
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.oredict.OreDictionary
import java.util.function.Predicate

sealed class ItemIngredient(val count: Int = 1) : Predicate<ItemStack>, HiiragiJsonSerializable {

    abstract fun getMatchingStacks(): Collection<ItemStack>

    open fun onProcess(inventory: IItemHandlerModifiable, index: Int) {
        inventory.extractItem(index, count, false)
    }

    //    Empty    //

    object EMPTY : ItemIngredient() {

        override fun getMatchingStacks(): Collection<ItemStack> = listOf()

        override fun test(t: ItemStack): Boolean = t.isEmpty

        override fun getJsonElement(): JsonElement {
            throw UnsupportedOperationException()
        }

    }

    //    ItemStack    //

    class Stacks(vararg stacks: ItemStack, count: Int = 1) : ItemIngredient(count) {

        private val stacks: List<ItemStack> = stacks.map {
            it.count = count
            return@map it
        }

        override fun getMatchingStacks(): Collection<ItemStack> = stacks

        override fun test(stack: ItemStack): Boolean =
            getMatchingStacks().any { stackIn -> stackIn.isSameWithoutCount(stack) && stack.count >= count }

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            val stackArray = JsonArray()
            stacks.map(ItemStack::getJsonElement).forEach(stackArray::add)
            root.add("stacks", stackArray)

            root.addProperty("count", count)

            return root

        }

    }

    //    Block    //

    class Blocks(vararg blocks: Block, count: Int = 1) : ItemIngredient(count) {

        private val blocks: List<Block> = blocks.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = blocks.map { it.itemStack(count) }

        override fun test(stack: ItemStack): Boolean {
            val blockT: Block = (stack.item as? ItemBlock)?.block ?: return false
            return blocks.any { block: Block -> blockT == block && stack.count >= count }
        }

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            val blockArray = JsonArray()
            blocks.map(Block::getRegistryName).map(ResourceLocation?::toString).forEach(blockArray::add)
            root.add("blocks", blockArray)

            root.addProperty("count", count)

            return root

        }

    }

    //    Item    //

    class Items(vararg items: Item, count: Int = 1) : ItemIngredient(count) {

        private val items: List<Item> = items.toList()

        override fun getMatchingStacks(): Collection<ItemStack> = items.map { it.itemStack(count) }

        override fun test(stack: ItemStack): Boolean =
            items.any { item: Item -> stack.item == item && stack.count >= count }

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            val itemArray = JsonArray()
            items.map(Item::getRegistryName).map(ResourceLocation?::toString).forEach(itemArray::add)
            root.add("items", itemArray)

            root.addProperty("count", count)

            return root

        }

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

        override fun test(stack: ItemStack): Boolean = oreDicts.any { oreDict: String -> oreDict in stack.oreDicts() }

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            val oreDictArray = JsonArray()
            oreDicts.forEach(oreDictArray::add)
            root.add("ore_dicts", oreDictArray)

            root.addProperty("count", count)

            return root

        }

    }

    //    HiiragiPart    //

    class Parts(private val part: HiiragiPart, count: Int = 1) : ItemIngredient(count) {

        constructor(shape: HiiragiShape, material: HiiragiMaterial, count: Int = 1) : this(
            HiiragiPart(shape, material),
            count
        )

        override fun getMatchingStacks(): Collection<ItemStack> = part.getItemStacks(count)

        override fun test(stack: ItemStack): Boolean = part == PartDictionary.getPart(stack)

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            root.add("parts", part.getJsonElement())
            root.addProperty("count", count)

            return root

        }

    }

    //    HiiragiMaterial    //

    class Materials(private val material: HiiragiMaterial, count: Int = 1) : ItemIngredient(count) {

        override fun getMatchingStacks(): Collection<ItemStack> = material.getItemStacks(count)

        override fun test(stack: ItemStack): Boolean = material == PartDictionary.getPart(stack)?.material

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            root.addProperty("materials", material.name)
            root.addProperty("count", count)

            return root

        }

    }

    //    HiiragiShape    //

    class Shapes(private val shape: HiiragiShape, count: Int = 1) : ItemIngredient(count) {

        override fun getMatchingStacks(): Collection<ItemStack> = shape.getItemStacks(count)

        override fun test(stack: ItemStack): Boolean = shape == PartDictionary.getPart(stack)?.shape

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            root.addProperty("shapes", shape.name)
            root.addProperty("count", count)

            return root

        }

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

        override fun test(stack: ItemStack): Boolean = predicate(stack)

        override fun onProcess(inventory: IItemHandlerModifiable, index: Int) = process(inventory, index)

        override fun getJsonElement(): JsonElement {
            throw UnsupportedOperationException()
        }

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