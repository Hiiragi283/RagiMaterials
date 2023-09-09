package hiiragi283.material.api.capability.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class HiiragiFluidTankWrapper implements IFluidHandler, INBTSerializable<NBTTagCompound> {

    private final HiiragiFluidTank[] tanks;

    public HiiragiFluidTankWrapper(HiiragiFluidTank... tanks) {
        this.tanks = tanks;
    }

    //    FluidHandler    //

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return Arrays.stream(tanks).map(tank -> tank.getTankProperties()[0]).toArray(IFluidTankProperties[]::new);
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
        return tanks[0].drain(maxDrain, doDrain);
    }

    //    INBTSerializable<NBTTagCompound>    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tagList = new NBTTagList();
        for (HiiragiFluidTank tank : tanks) {
            Optional<FluidStack> stack = tank.getFluidOptional();
            var tag = new NBTTagCompound();
            stack.ifPresent(fluid -> fluid.writeToNBT(tag));
            tagList.appendTag(tag);
        }
        var tag = new NBTTagCompound();
        tag.setTag("Fluids", tagList);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        NBTTagList tagList = nbt.getTagList("Fluids", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            tanks[i].setFluid(FluidStack.loadFluidStackFromNBT(tag));
        }
    }

}