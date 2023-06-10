package hiiragi283.material.material_part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.MetaResourceLocation
import hiiragi283.material.util.wrapper.BlockStateWrapper
import hiiragi283.material.util.wrapper.FluidStackWrapper
import hiiragi283.material.util.wrapper.ItemStackWrapper
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

object MaterialPartRegistry {

    private val TAG_TO_MATERIAL_PART: HashMap<String, MaterialPart> = hashMapOf()

    @JvmStatic
    fun getMaterialParts(): Collection<MaterialPart> = RagiMaterials.MATERIAL_PART.valuesCollection

    //    getMaterialPart    //

    @JvmStatic
    fun getMaterialPart(location: String): MaterialPart =
        TAG_TO_MATERIAL_PART.getOrDefault(location, MaterialPart.EMPTY)

    @JvmStatic
    fun getMaterialPart(location: MetaResourceLocation): MaterialPart = getMaterialPart(location.toString())

    @JvmStatic
    //For BlockStateWrapper
    fun getMaterialPart(state: BlockStateWrapper): MaterialPart = getMaterialPart(MetaResourceLocation(state))

    @JvmStatic
    //For IBlockState
    fun getMaterialPart(state: IBlockState): MaterialPart = getMaterialPart(state.toMetaLocation())

    @JvmStatic
    //For FluidStackWrapper
    fun getMaterialPart(stack: FluidStackWrapper): MaterialPart = getMaterialPart(MetaResourceLocation(stack))

    @JvmStatic
    //For FluidStack
    fun getMaterialPart(stack: FluidStack): MaterialPart = getMaterialPart(stack.toMetaLocation())

    @JvmStatic
    //For ItemStackWrapper
    fun getMaterialPart(stack: ItemStackWrapper): MaterialPart = getMaterialPart(MetaResourceLocation(stack))

    @JvmStatic
    //For ItemStack
    fun getMaterialPart(stack: ItemStack): MaterialPart = getMaterialPart(stack.toMetaLocation())

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
    //For BlockStateWrapper
    fun registerTag(state: BlockStateWrapper, materialPart: MaterialPart): Unit =
        registerTag(MetaResourceLocation(state), materialPart)

    @JvmStatic
    //For IBlockState
    fun registerTag(state: IBlockState, materialPart: MaterialPart): Unit =
        registerTag(state.toMetaLocation(), materialPart)

    @JvmStatic
    //For FluidStackWrapper
    fun registerTag(stack: FluidStackWrapper, materialPart: MaterialPart): Unit =
        registerTag(MetaResourceLocation(stack), materialPart)

    @JvmStatic
    //For FluidStack
    fun registerTag(stack: FluidStack, materialPart: MaterialPart): Unit =
        registerTag(stack.toMetaLocation(), materialPart)

    @JvmStatic
    //For ItemStackWrapper
    fun registerTag(stack: ItemStackWrapper, materialPart: MaterialPart): Unit =
        registerTag(MetaResourceLocation(stack), materialPart)

    @JvmStatic
    //For ItemStack
    fun registerTag(stack: ItemStack, materialPart: MaterialPart): Unit =
        registerTag(stack.toMetaLocation(), materialPart)

    private fun IBlockState.toMetaLocation(): String =
        StringBuilder().also {
            it.append(this.block.registryName)
            it.append(":")
            it.append(this.block.getMetaFromState(this))
        }.toString()

    private fun FluidStack.toMetaLocation(): String = this.fluid.name

    private fun ItemStack.toMetaLocation(): String =
        StringBuilder().also {
            it.append(this.item.registryName)
            it.append(":")
            it.append(this.metadata)
        }.toString()

}