package hiiragi283.material.material_part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.MetaResourceLocation
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

object MaterialPartRegistry {

    private val TAG_TO_MATERIAL_PART: HashMap<String, MaterialPart> = hashMapOf()

    //    getMaterialPart    //

    @JvmStatic
    fun getMaterialPart(location: String): MaterialPart =
        TAG_TO_MATERIAL_PART.getOrDefault(location, MaterialPart.EMPTY)

    @JvmStatic
    fun getMaterialPart(location: MetaResourceLocation): MaterialPart = getMaterialPart(location.toString())

    @JvmStatic
    //For IBlockState
    fun getMaterialPart(state: IBlockState): MaterialPart = getMaterialPart(MetaResourceLocation(state))

    @JvmStatic
    //For FluidStack
    fun getMaterialPart(stack: FluidStack): MaterialPart = getMaterialPart(MetaResourceLocation(stack))

    @JvmStatic
    //For ItemStack
    fun getMaterialPart(stack: ItemStack): MaterialPart = getMaterialPart(MetaResourceLocation(stack))

    //    registerTag    //

    @JvmStatic
    fun registerTag(location: String, materialPart: MaterialPart) {
        TAG_TO_MATERIAL_PART.putIfAbsent(location, materialPart)
            ?.let { RagiMaterials.LOGGER.warn("The location: $location has already registered!") }
    }

    @JvmStatic
    fun registerTag(location: MetaResourceLocation, materialPart: MaterialPart): Unit =
        registerTag(location.toString(), materialPart)

    @JvmStatic
    //For IBlockState
    fun registerTag(state: IBlockState, materialPart: MaterialPart): Unit =
        registerTag(MetaResourceLocation(state), materialPart)

    @JvmStatic
    //For FluidStack
    fun registerTag(stack: FluidStack, materialPart: MaterialPart): Unit =
        registerTag(MetaResourceLocation(stack), materialPart)

    @JvmStatic
    //For ItemStack
    fun registerTag(stack: ItemStack, materialPart: MaterialPart): Unit =
        registerTag(MetaResourceLocation(stack), materialPart)

}