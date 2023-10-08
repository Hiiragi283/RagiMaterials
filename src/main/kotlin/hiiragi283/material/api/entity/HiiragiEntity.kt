package hiiragi283.material.api.entity

import hiiragi283.material.RagiMaterials
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface HiiragiEntity {

    fun openGui(player: EntityPlayer, world: World, pos: BlockPos, entity: Entity) {
        if (!world.isRemote) player.openGui(RagiMaterials.Instance, entity.entityId, world, pos.x, pos.y, pos.z)
    }

}