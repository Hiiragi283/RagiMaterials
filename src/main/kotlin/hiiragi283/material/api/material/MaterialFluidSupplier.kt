package hiiragi283.material.api.material

import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.fluid.MaterialFluidBlock
import hiiragi283.material.api.registry.HiiragiEntry
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

class MaterialFluidSupplier(
    val hasFluid: Boolean,
    val hasBlock: Boolean,
    val hasBucket: Boolean
) {

    fun register(material: HiiragiMaterial) {
        val fluid: Fluid = MaterialFluid(material).takeIf { hasFluid } ?: return
        if (FluidRegistry.isFluidRegistered(fluid.name)) return
        FluidRegistry.registerFluid(fluid)
        if (hasBucket) FluidRegistry.addBucketForFluid(fluid)
        if (hasBlock) {
            MaterialFluidBlock(fluid).run {
                fluid.block = this
                (this as? HiiragiEntry.BLOCK)?.register()
                FluidRegistry.registerFluid(fluid)
            }
        }
    }

}