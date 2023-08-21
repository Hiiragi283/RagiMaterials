package hiiragi283.integration

import hiiragi283.api.capability.HiiragiCapability
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.material.RMReference
import hiiragi283.material.util.getTile
import mcjty.theoneprobe.api.*
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

object TOPIntegration : IProbeInfoProvider {

    //    IProbeInfoProvider    //

    override fun getID(): String = RMReference.MOD_ID

    override fun addProbeInfo(
        mode: ProbeMode,
        probeInfo: IProbeInfo,
        player: EntityPlayer,
        world: World,
        state: IBlockState,
        data: IProbeHitData
    ) {
        if (mode == ProbeMode.EXTENDED) {
            getTile<TileEntity>(world, data.pos)?.getCapability(HiiragiCapability.MATERIAL, null)?.let {
                if (!it.isEmpty()) {
                    val material: HiiragiMaterial = it.getMaterialStack().material
                    probeInfo.text(TextStyleClass.NAME.toString() + "Material: " + material.getTranslatedName())
                    probeInfo.progress(
                        it.getMaterialAmount(), it.getCapacity(), probeInfo.defaultProgressStyle()
                            .suffix("mB")
                            .filledColor(material.color)
                            .alternateFilledColor(material.color)
                    )
                }
            }
        }
    }

}