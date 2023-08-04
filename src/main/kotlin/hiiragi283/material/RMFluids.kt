package hiiragi283.material

import hiiragi283.api.fluid.HiiragiFluid
import hiiragi283.api.material.MaterialRegistry
import net.minecraftforge.fluids.FluidRegistry

object RMFluids {

    fun register() {
        MaterialRegistry.getMaterials()
            .filter { it.hasTempBoil() && it.hasTempMelt() }
            .map { HiiragiFluid.of(it) }
            .forEach {
                FluidRegistry.registerFluid(it)
                FluidRegistry.addBucketForFluid(it)
            }
    }

}