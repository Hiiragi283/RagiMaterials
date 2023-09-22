package hiiragi283.material.tile

import hiiragi283.material.api.tile.HiiragiTileEntity
import net.minecraft.entity.item.EntityMinecart
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraftforge.common.capabilities.Capability

class TileEntityCapabilityRail : HiiragiTileEntity() {

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = world.getEntitiesWithinAABB(
        EntityMinecart::class.java,
        AxisAlignedBB(
            (pos.x + 0.2),
            (pos.y + 0.2),
            (pos.z + 0.2),
            (pos.x + 0.8),
            (pos.y + 0.8),
            (pos.z + 0.8)
        )
    ).getOrNull(0)?.hasCapability(capability, facing) ?: super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? = world.getEntitiesWithinAABB(
        EntityMinecart::class.java,
        AxisAlignedBB(
            (pos.x + 0.2),
            (pos.y + 0.2),
            (pos.z + 0.2),
            (pos.x + 0.8),
            (pos.y + 0.8),
            (pos.z + 0.8)
        )
    ).getOrNull(0)?.getCapability(capability, facing) ?: super.getCapability(capability, facing)

}