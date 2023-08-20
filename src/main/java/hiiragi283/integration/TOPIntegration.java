package hiiragi283.integration;

import hiiragi283.api.capability.HiiragiCapability;
import hiiragi283.api.capability.material.IMaterialHandler;
import hiiragi283.api.material.HiiragiMaterial;
import hiiragi283.material.RMReference;
import hiiragi283.material.util.HiiragiUtil;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TOPIntegration implements IProbeInfoProvider {

    @Override
    public String getID() {
        return RMReference.MOD_ID;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {
        if (probeMode == ProbeMode.EXTENDED) {
            TileEntity tile = HiiragiUtil.getTile(world, iProbeHitData.getPos());
            if (tile == null) return;
            IMaterialHandler handler = tile.getCapability(HiiragiCapability.MATERIAL, null);
            if (handler == null || handler.isEmpty()) return;
            HiiragiMaterial material = handler.getMaterialStack().getMaterial();
            iProbeInfo.text(TextStyleClass.NAME + "Material: " + material.getTranslatedName());
            iProbeInfo.progress(
                    handler.getMaterialAmount(), handler.getCapacity(), iProbeInfo.defaultProgressStyle()
                            .suffix("mB")
                            .filledColor(material.getColor())
                            .alternateFilledColor(material.getColor())
            );
        }
    }

}