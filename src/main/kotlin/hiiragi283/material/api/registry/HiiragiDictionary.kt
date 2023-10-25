package hiiragi283.material.api.registry

import hiiragi283.material.RMReference
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

typealias MetaItem = Pair<Item, Int>

@Suppress("DEPRECATION")
abstract class HiiragiDictionary<T>(name: String) {

    protected val LOGGER: Logger = LogManager.getLogger("$name Dictionary")

    protected val ITEM_TO_OBJECT: MutableMap<MetaItem, T> = mutableMapOf()

    protected val OBJECT_TO_ITEMS: MutableMap<T, Set<MetaItem>> = mutableMapOf()

    protected fun ItemStack.toPart(): MetaItem = this.item to this.metadata

    protected fun MetaItem.toStack(count: Int = 1): ItemStack = ItemStack(this.first, count, this.second)

    //    Addition    //

    fun register(block: Block, metadata: Int, obj: T) {
        register(Item.getItemFromBlock(block), metadata, obj)
    }

    fun register(stack: ItemStack, obj: T) {
        if (stack.metadata == Short.MAX_VALUE.toInt()) {
            NonNullList.create<ItemStack>()
                .apply { stack.item.getSubItems(CreativeTabs.SEARCH, this) }
                .forEach { register(it.toPart(), obj) }
        } else register(stack.toPart(), obj)
    }

    fun register(item: Item, metadata: Int, obj: T) {
        register(item to metadata, obj)
    }

    @JvmSynthetic
    fun register(pair: MetaItem, obj: T) {
        if (ITEM_TO_OBJECT.putIfAbsent(pair, obj) !== null) {
            LOGGER.warn("The pair: ${pair.let { (item, meta) -> item.registryName!! to meta }} is already registered with ${ITEM_TO_OBJECT[pair]}!")
        }
        val set: MutableSet<MetaItem> = OBJECT_TO_ITEMS[obj]?.toMutableSet() ?: mutableSetOf()
        set.add(pair)
        OBJECT_TO_ITEMS[obj] = set
        onRegistered(pair, obj)
    }

    open fun onRegistered(pair: MetaItem, obj: T) {}

    //    getObject    //

    fun getObject(block: Block, metadata: Int): T? = getObject(Item.getItemFromBlock(block), metadata)

    fun getObject(stack: ItemStack): T? = if (stack.metadata == Short.MAX_VALUE.toInt())
        getObject(stack.item to 0)
    else getObject(stack.toPart())

    fun getObject(item: Item, metadata: Int): T? = getObject(item to metadata)

    private fun getObject(pair: MetaItem): T? = ITEM_TO_OBJECT[pair]

    //    getItems    //

    fun hasItemStack(obj: T): Boolean = OBJECT_TO_ITEMS.containsKey(obj)

    fun getPrimalStack(obj: T, count: Int = 1): ItemStack? = getPrimalPair(obj)?.toStack(count)

    private fun getPrimalPair(obj: T): MetaItem? = getItemPairs(obj)
        .firstOrNull { pair: MetaItem -> pair.first.registryName!!.namespace == "minecraft" } ?: getItemPairs(obj)
        .firstOrNull { pair: MetaItem -> pair.first.registryName!!.namespace == RMReference.MOD_ID }

    fun getItemStacks(obj: T, count: Int = 1): List<ItemStack> = getItemPairs(obj).map { it.toStack(count) }

    @Deprecated("", ReplaceWith("OBJECT_TO_ITEMS[obj] ?: setOf()"))
    fun getItemPairs(obj: T): Set<MetaItem> = OBJECT_TO_ITEMS[obj] ?: setOf()

}