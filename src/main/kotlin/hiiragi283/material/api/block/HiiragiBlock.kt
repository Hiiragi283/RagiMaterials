package hiiragi283.material.api.block

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry

abstract class HiiragiBlock(settings: FabricBlockSettings) : Block(settings), HiiragiEntry {

    //    HiiragiEntry    //

    override fun register() {
        Registry.register(Registry.BLOCK, identifier, this)
        RagiMaterials.LOGGER.debug("The block ${identifier.path} registered!")
    }

}