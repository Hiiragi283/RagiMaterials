package hiiragi283.material.common

import hiiragi283.material.api.material.MaterialPart
import hiiragi283.material.api.material.MaterialType
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.TypeFilter
import net.minecraft.world.explosion.Explosion

object RagiEventHandler {

    fun load() {
        //水中のアルカリ金属またはアルカリ土類金属に対して爆発を起こすEvent
        ServerTickEvents.START_WORLD_TICK.register(ServerTickEvents.StartWorldTick { world ->
            world.getEntitiesByType(TypeFilter.instanceOf(ItemEntity::class.java)) { it.isSubmergedInWater }
                .forEach {
                    val item = it.stack.item
                    if (item is MaterialPart<*>) {
                        val type = (item as MaterialPart<ItemStack>).getMaterial(it.stack).type
                        if (type == MaterialType.ALKALI_METAL || type == MaterialType.ALKALINE_METAL)
                            world.createExplosion(null, it.x, it.y, it.z, 3.0f, Explosion.DestructionType.BREAK)
                    }
                }
        })
    }

}