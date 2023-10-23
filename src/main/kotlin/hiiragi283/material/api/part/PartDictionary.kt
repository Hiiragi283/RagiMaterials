package hiiragi283.material.api.part

import hiiragi283.material.RMReference
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

private typealias MetaItem = Pair<Item, Int>

object PartDictionary {

    private val LOGGER: Logger = LogManager.getLogger("Part Dictionary")

    private val ITEM_TO_PART: MutableMap<MetaItem, HiiragiPart> = mutableMapOf()

    private val PART_TO_ITEMS: MutableMap<HiiragiPart, Set<MetaItem>> = mutableMapOf()

    private fun ItemStack.toPart(): MetaItem = this.item to this.metadata

    private fun MetaItem.toStack(): ItemStack = ItemStack(this.first, 1, this.second)

    //    Addition    //

    @JvmStatic
    fun add(block: Block, metadata: Int, part: HiiragiPart) {
        add(ItemStack(block, 1, metadata), part)
    }

    @JvmStatic
    fun add(oreDict: String, part: HiiragiPart) {
        OreDictionary.getOres(oreDict).forEach { stack: ItemStack -> add(stack, part) }
    }

    @JvmStatic
    fun add(stack: ItemStack, part: HiiragiPart) {
        add(stack.toPart(), part)
    }

    @JvmStatic
    fun add(item: Item, metadata: Int, part: HiiragiPart) {
        add(item to metadata, part)
    }

    @JvmSynthetic
    private fun add(pair: MetaItem, part: HiiragiPart) {
        if (ITEM_TO_PART.putIfAbsent(pair, part) !== null) {
            LOGGER.warn("The pair: $pair is already registered with ${ITEM_TO_PART[pair]}!")
        }
        val set: MutableSet<MetaItem> = PART_TO_ITEMS[part]?.toMutableSet() ?: mutableSetOf()
        set.add(pair)
        PART_TO_ITEMS[part] = set
    }

    //    getPart    //

    @JvmStatic
    fun getPart(block: Block, metadata: Int): HiiragiPart? = getPart(ItemStack(block, 1, metadata))

    @JvmStatic
    fun getPart(stack: ItemStack): HiiragiPart? = getPart(stack.toPart())

    @JvmStatic
    fun getPart(item: Item, metadata: Int): HiiragiPart? = getPart(item to metadata)

    private fun getPart(pair: MetaItem): HiiragiPart? = ITEM_TO_PART[pair]

    //    getItems    //

    @JvmStatic
    fun getPrimalStack(part: HiiragiPart): ItemStack? = (getItemPairs(part)
        .firstOrNull { pair: MetaItem -> pair.first.registryName!!.namespace == "minecraft" } ?: getItemPairs(part)
        .firstOrNull { pair: MetaItem -> pair.first.registryName!!.namespace == RMReference.MOD_ID }
            )?.toStack()

    @JvmStatic
    fun getItemStacks(part: HiiragiPart): List<ItemStack> = getItemPairs(part).map { it.toStack() }

    @Deprecated("")
    @JvmStatic
    fun getItemPairs(part: HiiragiPart): Set<MetaItem> = PART_TO_ITEMS[part] ?: setOf()

}