package hiiragi283.material.api.item

import hiiragi283.material.api.IHiiragiEntry
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item

abstract class HiiragiItem(settings: FabricItemSettings) : Item(settings), IHiiragiEntry {

}