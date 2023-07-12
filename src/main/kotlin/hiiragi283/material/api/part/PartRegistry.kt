package hiiragi283.material.api.part

import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RagiMaterials
import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.tag.TagKey
import net.minecraft.util.registry.Registry
import java.util.stream.Stream

object PartRegistry {

    private val REGISTRY: HashMap<TagKey<*>, HiiragiPart> = hashMapOf()

    //    getMaterialPart    //

    @JvmStatic
    fun getPart(tag: TagKey<*>): HiiragiPart = REGISTRY.getOrDefault(tag, HiiragiPart.EMPTY)

    @JvmStatic
    fun getParts(tag: TagKey<*>): List<HiiragiPart> = listOf(getPart(tag)).filterNot(HiiragiPart::isEmpty)

    @JvmStatic
    fun getParts(tags: Collection<TagKey<*>>): List<HiiragiPart> =
        tags.map(::getPart).filterNot(HiiragiPart::isEmpty)

    //TagKeyから取得する
    @JvmStatic
    fun getParts(state: BlockState): List<HiiragiPart> = getPartsFromStream(state.streamTags())

    //TagKeyから取得する
    @JvmStatic
    fun getParts(stack: ItemStack): List<HiiragiPart> =
        stack.takeUnless(ItemStack::isEmpty)
            ?.let(ItemStack::streamTags)
            ?.let(::getPartsFromStream) ?: listOf()

    private fun <T : Any> getPartsFromStream(stream: Stream<TagKey<T>>): List<HiiragiPart> = stream.toList()
        .map(PartRegistry::getPart)
        .filterNot(HiiragiPart::isEmpty)

    //    registerTag    //

    @JvmStatic
    fun registerTag(tag: TagKey<*>, hiiragiPart: HiiragiPart) {
        REGISTRY[tag]?.let {
            RagiMaterials.LOGGER.warn("The part: $it will be overrided by $tag!")
        }
        REGISTRY[tag] = hiiragiPart
    }

    //    init    //

    fun init() {
        REGISTRY.clear()
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .map { HiiragiPart(it, material) }
                .forEach {
                    registerTag(it.getTagKey(Registry.BLOCK_KEY), it)
                    registerTag(it.getTagKey(Registry.ITEM_KEY), it)
                }
        }
    }
}