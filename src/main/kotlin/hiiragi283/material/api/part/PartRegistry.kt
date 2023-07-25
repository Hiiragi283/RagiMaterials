package hiiragi283.material.api.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.util.toItemStack
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object PartRegistry {

    private val REGISTRY: HashMap<String, HiiragiPart> = hashMapOf()

    private var hasInit: Boolean = false

    //    getMaterialPart    //

    @JvmStatic
    fun getPart(oredict: String): HiiragiPart = REGISTRY.getOrDefault(oredict, HiiragiPart.EMPTY)

    @JvmStatic
    fun getParts(oredict: String): List<HiiragiPart> = listOf(getPart(oredict)).filterNot(HiiragiPart::isEmpty)

    @JvmStatic
    fun getParts(oredicts: Collection<String>): List<HiiragiPart> =
        oredicts.map(::getPart).filterNot(HiiragiPart::isEmpty)

    //ItemStackに変換した後，鉱石辞書から取得する
    @JvmStatic
    fun getParts(state: IBlockState): Set<HiiragiPart> {
        return state
            .let(IBlockState::toItemStack)
            .let(this::getParts)
    }

    //鉱石辞書から取得する
    @JvmStatic
    fun getParts(stack: ItemStack): Set<HiiragiPart> {
        return stack.takeUnless(ItemStack::isEmpty)
            ?.let(OreDictionary::getOreIDs)
            ?.map(OreDictionary::getOreName)
            ?.map(PartRegistry::getPart)
            ?.filterNot(HiiragiPart::isEmpty)
            ?.toSet() ?: setOf()
    }

    //    registerTag    //

    @JvmStatic
    fun registerTag(oredict: String, part: HiiragiPart) {

        if (part.isEmpty()) {
            RagiMaterials.LOGGER.warn("The part: $part is EMPTY!")
            return
        }

        REGISTRY[oredict]?.let {
            RagiMaterials.LOGGER.warn("The part: $it will be overrided by $oredict!")
        }

        REGISTRY[oredict] = part
    }

    @JvmStatic
    fun registerTag(oredicts: Collection<String>, part: HiiragiPart) {
        oredicts.forEach { registerTag(it, part) }
    }

    fun init() {

        if (hasInit) {
            RagiMaterials.LOGGER.warn("PartRegistry is already initialized!")
            return
        }

        REGISTRY.clear()
        ShapeRegistry.getShapes().forEach { shape ->
            MaterialRegistry.getMaterials()
                .map { HiiragiPart(shape, it) }
                .filterNot { it.isEmpty() }
                .forEach { registerTag(it.getOreDicts(), it) }
        }
    }

}