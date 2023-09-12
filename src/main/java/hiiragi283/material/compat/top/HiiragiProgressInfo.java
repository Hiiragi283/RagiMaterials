package hiiragi283.material.compat.top;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.util.OptionalUtil;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import mcjty.theoneprobe.apiimpl.styles.ProgressStyle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HiiragiProgressInfo implements IProbeInfoProvider {

    public static void register() {
        TheOneProbe.theOneProbeImp.registerProvider(new HiiragiProgressInfo());
    }

    private HiiragiProgressInfo() {
    }

    @Override
    public String getID() {
        return RMReference.MOD_ID + ".progress";
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        OptionalUtil.getTile(world, iProbeHitData.getPos(), HiiragiTileEntity.Tickable.class).ifPresent(tile -> iProbeInfo.progress(tile.currentCount, tile.maxCount, new ProgressStyle().suffix("%")));
    }

}