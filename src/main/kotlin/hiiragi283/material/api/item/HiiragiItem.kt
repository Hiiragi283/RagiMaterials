package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

abstract class HiiragiItem(settings: FabricItemSettings = FabricItemSettings()) : Item(settings), HiiragiEntry {

    //    HiiragiEntry    //

    override fun register() {
        Registry.register(Registry.ITEM, identifier, this)
        RagiMaterials.LOGGER.debug("The item ${identifier.path} registered!")
    }

}