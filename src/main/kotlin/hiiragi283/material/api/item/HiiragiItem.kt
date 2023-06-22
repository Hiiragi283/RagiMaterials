package hiiragi283.material.api.item

import hiiragi283.material.api.IHiiragiEntry
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

abstract class HiiragiItem(settings: FabricItemSettings) : Item(settings), IHiiragiEntry {

    override fun register(path: String) {
        Registry.register(Registry.ITEM, hiiragiId(path), asItem())
        RagiMaterials.LOGGER.debug("The item $path registered!")
    }

}