package hiiragi283.material.common

import hiiragi283.material.api.part.PartRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.entity.ItemEntity
import net.minecraft.util.TypeFilter

object RagiEventHandler {

    fun loadCommon() {
        //水中のアルカリ金属またはアルカリ土類金属に対して爆発を起こすEvent
        ServerTickEvents.START_WORLD_TICK.register(ServerTickEvents.StartWorldTick { world ->
            world.getEntitiesByType(TypeFilter.instanceOf(ItemEntity::class.java)) { it.isSubmergedInWater }
                .forEach {
                    PartRegistry.getParts(it.stack).forEach { part -> part.doExplosion(world, it) }
                }
        })
    }

    @Environment(EnvType.CLIENT)
    fun loadClient() {
        //ItemStack -> TagKey -> HiiragiPartから素材の情報をツールチップに追加するEvent
        ItemTooltipCallback.EVENT.register(ItemTooltipCallback { stack, _, lines ->
            PartRegistry.getParts(stack).forEach { it.appendTooltip(lines) }
        })
    }

}