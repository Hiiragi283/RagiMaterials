package hiiragi283.api.part

import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.shape.ShapeRegistry
import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.toItemStack
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object PartRegistry {

    private val REGISTRY: HashMap<String, HiiragiPart> = hashMapOf()

    fun init() {
        ShapeRegistry.getShapes().forEach { shape ->
            MaterialRegistry.getMaterials()
                .map { HiiragiPart(shape, it) }
                .forEach { registerTag(it.getOreDicts(), it) }
        }
    }

    /**
     * Returns [HiiragiPart] with given Ore Dictionary name from [REGISTRY]
     * @return [HiiragiPart.EMPTY] if there is no material with given name
     */
    @JvmStatic
    fun getPart(oredict: String): HiiragiPart = REGISTRY.getOrDefault(oredict, HiiragiPart.EMPTY)

    @JvmStatic
    fun getAllParts(): MutableCollection<HiiragiPart> = REGISTRY.values

    /**
     * Returns list of [HiiragiPart] with given Ore Dictionary name from [REGISTRY]
     * @return a new list excluded [HiiragiPart.EMPTY]
     */
    @JvmStatic
    fun getParts(oredict: String): List<HiiragiPart> = listOf(getPart(oredict)).filterNot(HiiragiPart::isEmpty)

    /**
     * Returns list of [HiiragiPart] with given list of Ore Dictionary names from [REGISTRY]
     * @return a new list excluded [HiiragiPart.EMPTY]
     */
    @JvmStatic
    fun getParts(oredicts: Collection<String>): List<HiiragiPart> =
        oredicts.map(PartRegistry::getPart).filterNot(HiiragiPart::isEmpty)

    /**
     * Returns list of [HiiragiPart] with given [IBlockState]
     *
     * The state will be converted into [ItemStack], then get entries by [getParts]
     * @return a new list excluded [HiiragiPart.EMPTY]
     */
    @JvmStatic
    fun getParts(state: IBlockState): List<HiiragiPart> {
        return state
            .let(IBlockState::toItemStack)
            .let(this::getParts)
    }

    /**
     * Returns list of [HiiragiPart] with given [ItemStack]
     *
     * The stack will be converted into Ore Dictionary names, then get entries from [REGISTRY]
     *
     * @return a new list excluded [HiiragiPart.EMPTY]
     */
    @JvmStatic
    fun getParts(stack: ItemStack): List<HiiragiPart> {
        return stack.takeUnless(ItemStack::isEmpty)
            ?.let(OreDictionary::getOreIDs)
            ?.map(OreDictionary::getOreName)
            ?.map(PartRegistry::getPart)
            ?.filterNot(HiiragiPart::isEmpty) ?: listOf()
    }

    //    registerTag    //

    /**
     * Registers [HiiragiPart] with given Ore Dictionary name
     *
     * Will be skipped if [HiiragiPart.EMPTY] given or the same oredict is already registered
     */
    @JvmStatic
    fun registerTag(oredict: String, part: HiiragiPart) {

        if (part.isEmpty()) {
            RagiMaterials.LOGGER.warn("The part: $part is EMPTY!")
            return
        }

        REGISTRY[oredict]?.let {
            RagiMaterials.LOGGER.warn("The part: $it will be overrided by $oredict!")
            return
        }

        REGISTRY[oredict] = part
    }

    /**
     * Registers the same [HiiragiPart] for each Ore Dictionary names
     */
    @JvmStatic
    fun registerTag(oredicts: Collection<String>, part: HiiragiPart) {
        oredicts.forEach { registerTag(it, part) }
    }

}