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
    fun getParts(state: BlockState): Set<HiiragiPart> = getPartsFromStream(state.streamTags())

    //TagKeyから取得する
    @JvmStatic
    fun getParts(stack: ItemStack): Set<HiiragiPart> =
        stack.takeUnless(ItemStack::isEmpty)
            ?.let(ItemStack::streamTags)
            ?.let(::getPartsFromStream) ?: setOf()

    private fun <T : Any> getPartsFromStream(stream: Stream<TagKey<T>>): Set<HiiragiPart> = stream.toList()
        .map(PartRegistry::getPart)
        .filterNot(HiiragiPart::isEmpty)
        .toSet()

    //    registerTag    //

    @JvmStatic
    fun registerTag(tag: TagKey<*>, part: HiiragiPart) {

        if (part.isEmpty()) {
            RagiMaterials.LOGGER.warn("Empty part will not be registered!")
            return
        }

        REGISTRY[tag]?.let {
            RagiMaterials.LOGGER.warn("$tag is already registered!")
        }

        REGISTRY[tag] = part

    }

    @JvmStatic
    fun registerTags(map: Map<TagKey<*>, HiiragiPart>) {
        map.forEach(::registerTag)
    }

    //    init    //

    fun init() {
        REGISTRY.clear()
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.isValid(material) }
                .map { HiiragiPart(it, material) }
                .filterNot { it.isEmpty() }
                .forEach {
                    registerTag(it.getTagKey(Registry.BLOCK_KEY), it)
                    registerTag(it.getTagKey(Registry.ITEM_KEY), it)
                }
        }
    }
}