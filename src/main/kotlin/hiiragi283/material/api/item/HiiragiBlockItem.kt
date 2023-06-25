package hiiragi283.material.api.item

import hiiragi283.material.api.IHiiragiEntry
import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BlockItem
import net.minecraft.util.registry.Registry

abstract class HiiragiBlockItem(block: HiiragiBlock, settings: FabricItemSettings = FabricItemSettings()) :
    BlockItem(block, settings), IHiiragiEntry {

    override fun register(path: String) {
        Registry.register(Registry.ITEM, hiiragiId(path), this)
        RagiMaterials.LOGGER.debug("The block item $path registered!")
    }

}