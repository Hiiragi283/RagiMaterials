package hiiragi283.material.api.material_part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.toItemStack
import hiiragi283.material.util.toLocation
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

object MaterialPartRegistry {

    private val TAG_TO_MATERIAL_PART: HashMap<String, MaterialPart> = hashMapOf()

    //    getMaterialPart    //

    @JvmStatic
    fun getMaterialPart(obj: Any): MaterialPart {
        val location: String? = when (obj) {
            is FluidStack -> obj.toLocation(false).toString()
            is IBlockState -> obj.toLocation().toString()
            is ItemStack -> obj.toLocation().toString()
            is ResourceLocation -> obj.toString()
            is String -> obj
            else -> null
        }
        return TAG_TO_MATERIAL_PART.getOrDefault(location, MaterialPart.EMPTY)
    }

    @JvmStatic
    fun getMaterialParts(obj: Any): Collection<MaterialPart> {
        return when (obj) {
            is FluidStack -> listOf(getMaterialPart(obj))
            is IBlockState -> listOf(getMaterialPart(obj))
            is ItemStack -> getMaterialPartFromItemStack(obj)
            is ResourceLocation -> listOf(getMaterialPart(obj))
            is String -> listOf(getMaterialPart(obj))
            else -> listOf(MaterialPart.EMPTY)
        }
    }

    private fun getMaterialPartFromBlockState(state: IBlockState): List<MaterialPart> {
        //鉱石辞書 -> IBlockStateの順に取得する
        //鉱石辞書を取得するのに一度ItemStackに変換する
        return state
            .let(IBlockState::toItemStack)
            .let(::getMaterialPartFromItemStack)
            .toMutableList()
            .also { it.add(getMaterialPart(state)) }
            .filterNot(MaterialPart::isEmpty)
    }

    private fun getMaterialPartFromItemStack(stack: ItemStack): List<MaterialPart> {
        //鉱石辞書 -> ItemStackの順に取得する
        return OreDictionary.getOreIDs(stack)
            .map(OreDictionary::getOreName)
            .map(MaterialPartRegistry::getMaterialPart)
            .toMutableList()
            .also { it.add(getMaterialPart(stack)) }
            .filterNot(MaterialPart::isEmpty)
    }

    //    registerTag    //

    @JvmStatic
    fun registerTag(obj: Any, materialPart: MaterialPart) {
        val location: String? = when (obj) {
            is FluidStack -> obj.toLocation(false).toString()
            is IBlockState -> obj.toLocation().toString()
            is ItemStack -> obj.toLocation().toString()
            is ResourceLocation -> obj.toString()
            is String -> obj
            else -> null
        }
        if (location == null) {
            RagiMaterials.LOGGER.error("Cannot generate location from $obj!")
            return
        }
        TAG_TO_MATERIAL_PART[location]?.let {
            RagiMaterials.LOGGER.warn("The material part: $it will be overrided by $location!")
        }
        TAG_TO_MATERIAL_PART[location] = materialPart
    }

}