package hiiragi283.ragi_materials.main.util

import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.storage.loot.LootContext

object RagiLoot {

    fun getLoot(world: World, location: ResourceLocation): ItemStack {
        return if (world is WorldServer) {
            //RagiLogger.infoDebug("world is WorldServer!")
            val builder = LootContext.Builder(world)
            val lootTable = world.lootTableManager.getLootTableFromLocation(location)
            val results = lootTable.generateLootForPools(world.rand, builder.build())
            return if (results.isNotEmpty() && results[0] !== null) {
                //RagiLogger.infoDebug("result is here!")
                results[0]
            } else {
                //RagiLogger.warnDebug("result is empty...")
                ItemStack.EMPTY
            }
        } else {
            //RagiLogger.warnDebug("result is empty...")
            ItemStack.EMPTY
        }
    }

    fun getLoot(world: World, registryName: String): ItemStack {
        val location = ResourceLocation(registryName)
        return getLoot(world, location)
    }
}