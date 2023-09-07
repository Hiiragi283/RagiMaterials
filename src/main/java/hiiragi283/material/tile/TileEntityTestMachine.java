package hiiragi283.material.tile;

import hiiragi283.material.api.capability.HiiragiCapability;
import hiiragi283.material.api.capability.IOControllable;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper;
import hiiragi283.material.api.capability.item.HiiragiItemHandler;
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper;
import hiiragi283.material.api.capability.material.MaterialHandler;
import hiiragi283.material.api.tile.MaterialTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityTestMachine extends MaterialTileEntity {

    protected HiiragiFluidTank outputTank0 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    protected HiiragiFluidTank outputTank1 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    protected HiiragiFluidTank outputTank2 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    protected HiiragiItemHandler inputInv = new HiiragiItemHandler(6, IOControllable.Type.INPUT);
    protected HiiragiItemHandler outputInv = new HiiragiItemHandler(6, IOControllable.Type.OUTPUT);
    protected MaterialHandler materialHandler = new MaterialHandler(64000, 3);

    public TileEntityTestMachine() {
        this.inventory = new HiiragiItemHandlerWrapper(inputInv, outputInv);
        this.tank = new HiiragiFluidTankWrapper(outputTank0, outputTank1, outputTank2);
    }

    //    Capability    //

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == HiiragiCapability.MATERIAL_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
        } else if (capability == HiiragiCapability.MATERIAL_HANDLER_CAPABILITY) {
            return HiiragiCapability.MATERIAL_HANDLER_CAPABILITY.cast(materialHandler);
        } else {
            return null;
        }
    }

}