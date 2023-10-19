package hiiragi283.material.api.material

import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.registry.HiiragiEntry
import net.minecraft.block.Block
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

class MaterialFluidSupplier(
    private val supplier: (HiiragiMaterial) -> Fluid? = { MaterialFluid(it) },
    private val block: (Fluid) -> Block? = { null },
    private val hasBucket: Boolean = true
) {

    fun hasFluid(material: HiiragiMaterial): Boolean = supplier(material) !== null

    fun hasBlock(material: HiiragiMaterial): Boolean = supplier(material)?.let(::hasBlock) ?: false

    fun hasBlock(fluid: Fluid): Boolean = block(fluid) !== null

    fun register(material: HiiragiMaterial) {
        val fluid: Fluid = supplier(material) ?: return
        if (FluidRegistry.isFluidRegistered(fluid.name)) return
        FluidRegistry.registerFluid(fluid)
        if (hasBucket) {
            FluidRegistry.addBucketForFluid(fluid)
        }
        block(fluid)?.let { block: Block ->
            fluid.block = block
            (block as? HiiragiEntry.BLOCK)?.register()
            FluidRegistry.registerFluid(fluid)
        }
    }

}