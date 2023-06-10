package hiiragi283.material.util

import hiiragi283.material.util.wrapper.BlockStateWrapper
import hiiragi283.material.util.wrapper.FluidStackWrapper
import hiiragi283.material.util.wrapper.ItemStackWrapper
import net.minecraft.util.ResourceLocation

data class MetaResourceLocation(val location: ResourceLocation, val meta: Int) {

    constructor(state: BlockStateWrapper) : this(state.block.registryName!!, state.meta)

    constructor(stack: FluidStackWrapper) : this(ResourceLocation(stack.fluid.name), 0)

    constructor(stack: ItemStackWrapper) : this(stack.item?.registryName!!, stack.meta)

    override fun toString(): String = StringBuilder().also {
        it.append(location.namespace)
        it.append(":")
        it.append(location.path)
        it.append(":")
        it.append(meta)
    }.toString()

}