package hiiragi283.material.material_part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.toLocation
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack

object MaterialPartRegistry {

    private val TAG_TO_MATERIAL_PART: HashMap<String, MaterialPart> = hashMapOf()

    //    getMaterialPart    //

    @JvmStatic
    fun getMaterialPart(location: String?): MaterialPart =
        TAG_TO_MATERIAL_PART.getOrDefault(location, MaterialPart.EMPTY)

    @JvmStatic
    fun getMaterialPart(location: ResourceLocation): MaterialPart = getMaterialPart(location.toString())

    @JvmStatic
    //For IBlockState
    fun getMaterialPart(state: IBlockState): MaterialPart = getMaterialPart(state.toLocation())

    @JvmStatic
    //For FluidStack
    fun getMaterialPart(stack: FluidStack): MaterialPart = getMaterialPart(stack.toLocation(false))

    @JvmStatic
    //For ItemStack
    fun getMaterialPart(stack: ItemStack): MaterialPart = getMaterialPart(stack.toLocation())

    //    registerTag    //

    @JvmStatic
    fun registerTag(location: String, materialPart: MaterialPart) {
        TAG_TO_MATERIAL_PART.putIfAbsent(location, materialPart)
            ?.let { RagiMaterials.LOGGER.warn("The location: $location has already registered!") }
    }

    @JvmStatic
    fun registerTag(location: ResourceLocation, materialPart: MaterialPart): Unit =
        registerTag(location.toString(), materialPart)

    @JvmStatic
    //For IBlockState
    fun registerTag(state: IBlockState, materialPart: MaterialPart): Unit =
        registerTag(state.toLocation(), materialPart)

    @JvmStatic
    //For FluidStack
    fun registerTag(stack: FluidStack, materialPart: MaterialPart): Unit =
        registerTag(stack.toLocation(false), materialPart)

    @JvmStatic
    //For ItemStack
    fun registerTag(stack: ItemStack, materialPart: MaterialPart): Unit =
        registerTag(stack.toLocation(), materialPart)

}