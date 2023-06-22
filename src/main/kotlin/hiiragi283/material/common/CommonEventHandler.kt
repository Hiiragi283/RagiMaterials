package hiiragi283.material.common

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.ItemEntity
import net.minecraft.util.TypeFilter
import net.minecraft.world.explosion.Explosion


object CommonEventHandler {

    fun load() {
        //水中のItemEntityに対して爆発を起こすEvent
        ServerTickEvents.START_WORLD_TICK.register(ServerTickEvents.StartWorldTick { world ->
            world.getEntitiesByType(TypeFilter.instanceOf(ItemEntity::class.java)) { it.isSubmergedInWater }
                .forEach { world.createExplosion(null, it.x, it.y, it.z, 3.0f, Explosion.DestructionType.BREAK) }
        })
    }

}