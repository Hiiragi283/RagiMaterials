package hiiragi283.material.api.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.toItemStack
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object PartRegistry {

    private val REGISTRY: HashMap<String, HiiragiPart> = hashMapOf()

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
    fun getParts(state: IBlockState): List<HiiragiPart> {
        return state
            .let(IBlockState::toItemStack)
            .let(this::getParts)
    }

    //鉱石辞書から取得する
    @JvmStatic
    fun getParts(stack: ItemStack): List<HiiragiPart> {
        return OreDictionary.getOreIDs(stack)
            .map(OreDictionary::getOreName)
            .map(PartRegistry::getPart)
            .filterNot(HiiragiPart::isEmpty)
    }

    //    registerTag    //

    @JvmStatic
    fun registerTag(oredict: String, hiiragiPart: HiiragiPart) {
        REGISTRY[oredict]?.let {
            RagiMaterials.LOGGER.warn("The part: $it will be overrided by $oredict!")
        }
        REGISTRY[oredict] = hiiragiPart
    }

}