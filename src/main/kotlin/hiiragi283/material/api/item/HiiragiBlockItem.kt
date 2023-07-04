package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem
import net.minecraft.util.registry.Registry

abstract class HiiragiBlockItem(block: HiiragiBlock, settings: FabricItemSettings = FabricItemSettings()) :
    BlockItem(block, settings), HiiragiEntry {

    override fun register() {
        Registry.register(Registry.ITEM, identifier, this)
        RagiMaterials.LOGGER.debug("The block item ${identifier.path} registered!")
    }

}