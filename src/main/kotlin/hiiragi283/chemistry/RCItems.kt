package hiiragi283.chemistry

import hiiragi283.api.HiiragiEntry
import net.minecraft.item.Item
import net.minecraftforge.registries.IForgeRegistry

object RCItems : HiiragiEntry.ITEM {

    override fun register(registry: IForgeRegistry<Item>) {
        RCBlocks.list.map { it.itemBlock }.forEach { registry.register(it) }
    }

}