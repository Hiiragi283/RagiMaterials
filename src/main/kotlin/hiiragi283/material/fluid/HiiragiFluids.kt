package hiiragi283.material.fluid

import hiiragi283.material.api.material.MaterialRegistry
import net.minecraftforge.fluids.FluidRegistry

object HiiragiFluids {

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