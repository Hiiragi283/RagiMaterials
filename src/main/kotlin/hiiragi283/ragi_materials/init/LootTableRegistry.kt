package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import net.minecraft.util.ResourceLocation
import net.minecraft.world.storage.loot.LootTableList

object LootTableRegistry {

    val OreRainbow = ResourceLocation(Reference.MOD_ID, "gameplay/ore_rainbow")

    init {
        LootTableList.register(OreRainbow)
    }

}