package hiiragi283.material.compat

import hiiragi283.material.RMReference
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.getTile
import mcjty.theoneprobe.TheOneProbe
import mcjty.theoneprobe.api.*
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiTOPPlugin : HiiragiPluginBase("theoneprobe", "TheOneProbe", { HiiragiConfigs.INTEGRATION.top }) {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        TheOneProbe.theOneProbeImp.registerProvider(object : IProbeInfoProvider {

            override fun getID(): String = RMReference.MOD_ID

            override fun addProbeInfo(
                probeMode: ProbeMode,
                iProbeInfo: IProbeInfo,
                player: EntityPlayer,
                world: World,
                state: IBlockState,
                iProbeHitData: IProbeHitData
            ) {
                getTile<TileEntityModuleMachine>(world, iProbeHitData.pos)?.let { tile ->
                    val property: MachineProperty = tile.machineProperty
                    iProbeInfo.progress(tile.currentCount, property.processTime, ProgressStyle().suffix("%"))
                    iProbeInfo.text(TextStyleClass.LABEL.toString() + "Process Time: " + TextStyleClass.INFO + property.processTime + " ticks");
                    iProbeInfo.text(TextStyleClass.LABEL.toString() + "Energy Rate: " + TextStyleClass.INFO + property.energyRate + " FE/ticks");
                    iProbeInfo.text(TextStyleClass.LABEL.toString() + "Energy Capacity: " + TextStyleClass.INFO + property.getEnergyCapacity() + " FE");
                    iProbeInfo.text(TextStyleClass.LABEL.toString() + "Item Slot: " + TextStyleClass.INFO + property.itemSlots + " slots");
                    iProbeInfo.text(TextStyleClass.LABEL.toString() + "Fluid Slot: " + TextStyleClass.INFO + property.fluidSlots + " slots");
                }
            }

        })
    }

}