package hiiragi283.material.api.fluid

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.util.registry.Registry

abstract class HiiragiFluidBlock(
    fluid: HiiragiFluid,
    settings: FabricBlockSettings = FabricBlockSettings.copyOf(Blocks.WATER)
) : FluidBlock(fluid, settings), HiiragiEntry {

    //    HiiragiEntry    //

    override fun register() {
        Registry.register(Registry.BLOCK, identifier, this)
        RagiMaterials.LOGGER.debug("The fluid block ${identifier.path} registered!")
    }

}