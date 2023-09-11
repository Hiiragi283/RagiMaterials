package hiiragi283.material.api.capability.fluid;

import hiiragi283.material.api.capability.IOControllable;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class HiiragiFluidTank extends FluidTank implements IOControllable, INBTSerializable<NBTTagCompound> {

    @NotNull
    protected final IOControllable.Type ioType;
    @Nullable
    protected final TileEntity tile;

    public HiiragiFluidTank(int capacity, IOControllable.Type ioType) {
        this(capacity, ioType, null);
    }

    public HiiragiFluidTank(int capacity, IOControllable.Type ioType, @Nullable TileEntity tile) {
        super(capacity);
        this.ioType = ioType;
        this.tile = tile;
    }

    public void clear() {
        this.fluid = null;
    }

    public Optional<FluidStack> getFluidOptional() {
        return this.fluid != null ? Optional.of(this.fluid) : Optional.empty();
    }

    public boolean isEmpty() {
        return this.fluid == null;
    }

    @Override
    protected void onContentsChanged() {
        if (tile != null) {
            tile.markDirty();
        }
    }

    //    IOControllable    //

    @NotNull
    @Override
    public Type getIOType() {
        return ioType;
    }

    //    INBTSerializable    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tag = new NBTTagCompound();
        tag.setInteger(HiiragiUtil.CAPACITY, getCapacity());
        getFluidOptional().ifPresent(stack -> tag.setTag(HiiragiUtil.FLUID, stack.writeToNBT(new NBTTagCompound())));
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.setCapacity(nbt.getInteger(HiiragiUtil.CAPACITY));
        this.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag(HiiragiUtil.FLUID)));
    }

}