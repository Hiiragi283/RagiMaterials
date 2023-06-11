package hiiragi283.material.util

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack

data class MetaResourceLocation(val location: ResourceLocation, val meta: Int) {

    constructor(state: IBlockState) : this(state.block.registryName!!, state.block.getMetaFromState(state))

    constructor(stack: FluidStack) : this(ResourceLocation(stack.fluid.name), 0)

    constructor(stack: ItemStack) : this(stack.item.registryName!!, stack.metadata)

    override fun toString(): String = StringBuilder().also {
        it.append(location.namespace)
        it.append(":")
        it.append(location.path)
        it.append(":")
        it.append(meta)
    }.toString()

}