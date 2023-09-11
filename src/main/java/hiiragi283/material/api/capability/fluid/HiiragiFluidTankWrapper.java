package hiiragi283.material.api.capability.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ParametersAreNonnullByDefault
public class HiiragiFluidTankWrapper implements IFluidHandler, INBTSerializable<NBTTagCompound> {

    @NotNull
    private List<HiiragiFluidTank> tanks;

    public void setSize(int size) {
        tanks = new ArrayList<>(size);
    }

    public HiiragiFluidTankWrapper(Collection<HiiragiFluidTank> tanks) {
        this.tanks = new ArrayList<>(tanks);
    }

    public HiiragiFluidTankWrapper(HiiragiFluidTank... tanks) {
        this.tanks = Arrays.asList(tanks);
    }

    //    FluidHandler    //

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return tanks.stream().map(tank -> tank.getTankProperties()[0]).toArray(IFluidTankProperties[]::new);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        int result = 0;
        for (HiiragiFluidTank tank : tanks) {
            if (tank.getIOType().canInsert) {
                int resultTest = tank.fill(resource, false);
                if (resultTest > 0) {
                    result = tank.fill(resource, doFill);
                    break;
                }
            }
        }
        return result;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        FluidStack result = null;
        for (HiiragiFluidTank tank : tanks) {
            if (tank.getIOType().canExtract) {
                FluidStack resultTest = tank.drain(resource, false);
                if (resultTest != null) {
                    result = tank.drain(resource, doDrain);
                    break;
                }
            }
        }
        return result;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        FluidStack result = null;
        for (HiiragiFluidTank tank : tanks) {
            if (tank.getIOType().canExtract) {
                FluidStack resultTest = tank.drain(maxDrain, false);
                if (resultTest != null) {
                    result = tank.drain(maxDrain, doDrain);
                    break;
                }
            }
        }
        return result;
    }

    //    INBTSerializable<NBTTagCompound>    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tagList = new NBTTagList();
        for (HiiragiFluidTank tank : tanks) {
            tagList.appendTag(tank.serializeNBT());
        }
        var tag = new NBTTagCompound();
        tag.setTag("Fluids", tagList);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        NBTTagList tagList = nbt.getTagList("Fluids", Constants.NBT.TAG_COMPOUND);
        setSize(tagList.tagCount());
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            tanks.get(i).deserializeNBT(tag);
        }
    }

}