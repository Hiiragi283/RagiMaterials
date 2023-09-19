package hiiragi283.material.compat

import hiiragi283.material.compat.top.HiiragiProgressInfoProvider
import mcjty.theoneprobe.TheOneProbe
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiTOPPlugin : IHiiragiPlugin {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        TheOneProbe.theOneProbeImp.registerProvider(HiiragiProgressInfoProvider)
    }

}