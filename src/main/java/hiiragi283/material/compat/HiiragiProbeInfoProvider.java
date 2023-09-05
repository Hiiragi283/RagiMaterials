package hiiragi283.material.compat;

import hiiragi283.material.RMReference;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HiiragiProbeInfoProvider implements IProbeInfoProvider {

    public static void register() {
        TheOneProbe.theOneProbeImp.registerProvider(new HiiragiProbeInfoProvider());
    }

    @Override
    public String getID() {
        return RMReference.MOD_ID;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {

    }

}