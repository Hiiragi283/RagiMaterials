package hiiragi283.material.api.block

import hiiragi283.material.api.IHiiragiEntry
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry

abstract class HiiragiBlock(settings: FabricBlockSettings) : Block(settings), IHiiragiEntry {

    override fun register(path: String) {
        Registry.register(Registry.BLOCK, hiiragiId(path), this)
        RagiMaterials.LOGGER.debug("The block $path registered!")
    }

}