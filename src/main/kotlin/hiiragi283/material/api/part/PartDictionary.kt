package hiiragi283.material.api.part

import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.HiiragiLogger
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.oredict.OreDictionary
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

typealias MetaItem = Pair<Item, Int>

object PartDictionary {

    private val LOGGER: Logger = LogManager.getLogger("Part Dictionary")

    private val ITEM_TO_PART: MutableMap<MetaItem, HiiragiPart> = mutableMapOf()
    private val PART_TO_ITEMS: MutableMap<HiiragiPart, Set<MetaItem>> = mutableMapOf()
    private val SHAPE_TO_ITEM: MutableMap<HiiragiShape, Set<MetaItem>> = mutableMapOf()
    private val MATERIAL_TO_ITEM: MutableMap<HiiragiMaterial, Set<MetaItem>> = mutableMapOf()

    private fun ItemStack.toPair(): MetaItem = this.item to this.metadata
    private fun MetaItem.toStack(count: Int = 1): ItemStack = ItemStack(this.first, count, this.second)

    fun reloadOreDicts() {
        HiiragiLogger.info("Reloading Ore Dictionary for Part Dictionary...")
        OreDictionary.getOreNames().forEach { oreDict: String ->
            OreDictionary.getOres(oreDict)
                .map { stack: ItemStack -> OreDictionary.OreRegisterEvent(oreDict, stack) }
                .forEach(::onOreDictRegistered)
        }
        HiiragiLogger.info("Reloaded!!")
    }

    @Suppress("unused")
    @SubscribeEvent
    fun onOreDictRegistered(event: OreDictionary.OreRegisterEvent) {
        val oreDict: String = event.name
        val stack: ItemStack = event.ore
        HiiragiPart.fromOreDict(oreDict)?.let { part: HiiragiPart ->
            register(stack, part)
            val oreDict1: String = part.getOreDict()
            if (oreDict != oreDict1) {
                OreDictionary.registerOre(oreDict1, stack)
            }
        }
    }

    //    register    //

    @JvmStatic
    fun register(stack: ItemStack, part: HiiragiPart) {
        register(stack.item, stack.metadata, part)
    }

    @JvmStatic
    fun register(block: Block, metadata: Int, part: HiiragiPart) {
        register(Item.getItemFromBlock(block), metadata, part)
    }

    @JvmStatic
    fun register(item: Item, metadata: Int, part: HiiragiPart) {
        if (metadata == Short.MAX_VALUE.toInt()) {
            NonNullList.create<ItemStack>()
                .apply { item.getSubItems(CreativeTabs.SEARCH, this) }
                .forEach { register(it.toPair(), part) }
        } else register(item to metadata, part)
    }

    @JvmSynthetic
    private fun register(metaItem: MetaItem, part: HiiragiPart) {
        val (shape: HiiragiShape, material: HiiragiMaterial) = part
        //Item -> Part
        if (ITEM_TO_PART.putIfAbsent(metaItem, part) !== null) {
            LOGGER.warn("The pair: ${metaItem.let { (item: Item, meta: Int) -> item.registryName!! to meta }} is already registered with ${ITEM_TO_PART[metaItem]}!")
        }
        //Part -> Item
        val partSet: MutableSet<MetaItem> = PART_TO_ITEMS[part]?.toMutableSet() ?: mutableSetOf()
        partSet.add(metaItem)
        PART_TO_ITEMS[part] = partSet
        //Shape -> Item
        val shapeSet: MutableSet<MetaItem> = SHAPE_TO_ITEM[shape]?.toMutableSet() ?: mutableSetOf()
        shapeSet.add(metaItem)
        SHAPE_TO_ITEM[shape] = shapeSet
        //Material -> Item
        val materialSet: MutableSet<MetaItem> = MATERIAL_TO_ITEM[material]?.toMutableSet() ?: mutableSetOf()
        materialSet.add(metaItem)
        MATERIAL_TO_ITEM[material] = materialSet
    }

    //    getPart    //

    @JvmStatic
    fun getPart(stack: ItemStack): HiiragiPart? = getPart(stack.item, stack.metadata)

    @JvmStatic
    fun getPart(block: Block, metadata: Int): HiiragiPart? = getPart(Item.getItemFromBlock(block), metadata)

    @JvmStatic
    fun getPart(item: Item, metadata: Int): HiiragiPart? =
        getPart(item to (metadata.takeUnless(Short.MAX_VALUE.toInt()::equals) ?: 0))

    private fun getPart(metaItem: MetaItem): HiiragiPart? = ITEM_TO_PART[metaItem]

    //    HiiragiPart    //

    @JvmStatic
    fun hasStack(part: HiiragiPart): Boolean = PART_TO_ITEMS.containsKey(part)

    @JvmStatic
    fun getStack(part: HiiragiPart, count: Int = 1): ItemStack? = findPair(getPairs(part))?.toStack(count)

    @JvmStatic
    fun getStacks(part: HiiragiPart, count: Int = 1): List<ItemStack> = getPairs(part).map { it.toStack(count) }

    private fun getPairs(part: HiiragiPart): Set<MetaItem> = PART_TO_ITEMS[part] ?: setOf()

    //    HiiragiShape    //

    @JvmStatic
    fun hasStack(shape: HiiragiShape): Boolean = SHAPE_TO_ITEM.containsKey(shape)

    @JvmStatic
    fun getStack(shape: HiiragiShape, count: Int = 1): ItemStack? = findPair(getPairs(shape))?.toStack(count)

    @JvmStatic
    fun getStacks(shape: HiiragiShape, count: Int = 1): List<ItemStack> =
        getPairs(shape).map { it.toStack(count) }

    private fun getPairs(shape: HiiragiShape): Set<MetaItem> = SHAPE_TO_ITEM[shape] ?: setOf()

    //    HiiragiMaterial    //

    @JvmStatic
    fun hasStack(material: HiiragiMaterial): Boolean = MATERIAL_TO_ITEM.containsKey(material)

    @JvmStatic
    fun getStack(material: HiiragiMaterial, count: Int = 1): ItemStack? = findPair(getPairs(material))?.toStack(count)

    @JvmStatic
    fun getStacks(material: HiiragiMaterial, count: Int = 1): List<ItemStack> =
        getPairs(material).map { it.toStack(count) }

    private fun getPairs(material: HiiragiMaterial): Set<MetaItem> = MATERIAL_TO_ITEM[material] ?: setOf()

    //    Misc    //

    private fun findPair(pairs: Collection<MetaItem>): MetaItem? =
        pairs.firstOrNull { it.first.registryName!!.namespace == "minecraft" }
            ?: pairs.firstOrNull { it.first.registryName!!.namespace == RMReference.MOD_ID }

}