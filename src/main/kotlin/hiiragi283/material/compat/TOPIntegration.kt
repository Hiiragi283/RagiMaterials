package hiiragi283.material.compat

import hiiragi283.material.RMReference
import mcjty.theoneprobe.api.IProbeHitData
import mcjty.theoneprobe.api.IProbeInfo
import mcjty.theoneprobe.api.IProbeInfoProvider
import mcjty.theoneprobe.api.ProbeMode
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
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

    }

}