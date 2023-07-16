package hiiragi283.material.common

import hiiragi283.material.api.part.PartRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.ItemEntity
import net.minecraft.util.TypeFilter
import net.minecraft.world.explosion.Explosion

object RagiEventHandler {

    fun loadCommon() {
        //水中のアルカリ金属またはアルカリ土類金属に対して爆発を起こすEvent
        ServerTickEvents.START_WORLD_TICK.register(ServerTickEvents.StartWorldTick { world ->
            world.getEntitiesByType(TypeFilter.instanceOf(ItemEntity::class.java)) { it.isSubmergedInWater }
                .forEach {
                    val list = PartRegistry.getParts(it.stack)
                        .map { part -> part.material.property }
                        .filter { property -> property.isActiveToWater }
                    if (list.isNotEmpty())
                        world.createExplosion(null, it.x, it.y, it.z, 3.0f, Explosion.DestructionType.BREAK)
                }
        })
    }

    @Environment(EnvType.CLIENT)
    fun loadClient() {
        //ItemStack -> TagKey -> HiiragiPartから素材の情報をツールチップに追加するEvent
        ItemTooltipCallback.EVENT.register(ItemTooltipCallback { stack, _, lines ->
            PartRegistry.getParts(stack).forEach { it.material.appendTooltip(lines, it.shape) }
        })
    }

}