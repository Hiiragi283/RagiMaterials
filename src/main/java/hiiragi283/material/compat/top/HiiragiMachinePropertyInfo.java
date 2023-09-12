package hiiragi283.material.compat.top;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.util.OptionalUtil;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HiiragiMachinePropertyInfo implements IProbeInfoProvider {

    public static void register() {
        TheOneProbe.theOneProbeImp.registerProvider(new HiiragiMachinePropertyInfo());
    }

    private HiiragiMachinePropertyInfo() {
    }

    @Override
    public String getID() {
        return RMReference.MOD_ID + ".machine_property";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (!entityPlayer.isSneaking()) return;
        OptionalUtil.getTile(world, iProbeHitData.getPos(), IMachineProperty.class).ifPresent(property -> {
            iProbeInfo.text(TextStyleClass.LABEL + "Process Time: " + TextStyleClass.INFO + property.getProcessTime() + " ticks");
            iProbeInfo.text(TextStyleClass.LABEL + "Energy Rate: " + TextStyleClass.INFO + property.getEnergyRate() + " FE/ticks");
            iProbeInfo.text(TextStyleClass.LABEL + "Energy Capacity: " + TextStyleClass.INFO + property.getEnergyCapacity() + " FE");
            iProbeInfo.text(TextStyleClass.LABEL + "Item Slot: " + TextStyleClass.INFO + property.getItemSlotCounts() + " slots");
            iProbeInfo.text(TextStyleClass.LABEL + "Fluid Slot: " + TextStyleClass.INFO + property.getFluidSlotCounts() + " slots");
        });
    }

}