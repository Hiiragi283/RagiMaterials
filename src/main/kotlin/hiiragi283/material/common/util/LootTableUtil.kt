package hiiragi283.material.common.util

import net.devtech.arrp.json.loot.JEntry
import net.devtech.arrp.json.loot.JLootTable
import net.devtech.arrp.json.loot.JPool
import net.minecraft.util.Identifier

object LootTableUtil {

    fun createSimple(item: String): JLootTable =
        JLootTable.loot("minecraft:block")
            .pool(
                JPool()
                    .rolls(1)
                    .entry(
                        JEntry()
                            .type("minecraft:item")
                            .name(item)
                            .condition("minecraft:survives_explosion")
                    )
            )

    fun createSimple(item: Identifier): JLootTable = createSimple(item.toString())

}