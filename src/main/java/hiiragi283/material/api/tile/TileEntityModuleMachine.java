package hiiragi283.material.api.tile;

import hiiragi283.material.api.capability.IOControllable;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper;
import hiiragi283.material.api.capability.item.HiiragiItemHandler;
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@ParametersAreNonnullByDefault
public class TileEntityModuleMachine extends HiiragiTileEntity.Tickable implements IMachineProperty {

    public TileEntityModuleMachine() {
        super(100);
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Module Machine");
    }

    //    NBT    //

    public IMachineProperty property;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        property.deserializeNBT(compound.getCompoundTag(HiiragiUtil.MACHINE_PROPERTY));
        super.readFromNBT(compound);
    }

    @NotNull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag(HiiragiUtil.MACHINE_PROPERTY, property.serializeNBT());
        return super.writeToNBT(compound);
    }

    //    Capability    //

    public final HiiragiItemHandler inputInv0 = new HiiragiItemHandler(1, IOControllable.Type.INPUT, this);
    public final HiiragiItemHandler inputInv1 = new HiiragiItemHandler(1, IOControllable.Type.INPUT, this);
    public final HiiragiItemHandler inputInv2 = new HiiragiItemHandler(1, IOControllable.Type.INPUT, this);
    public final HiiragiItemHandler inputInv3 = new HiiragiItemHandler(1, IOControllable.Type.INPUT, this);
    public final HiiragiItemHandler inputInv4 = new HiiragiItemHandler(1, IOControllable.Type.INPUT, this);
    public final HiiragiItemHandler inputInv5 = new HiiragiItemHandler(1, IOControllable.Type.INPUT, this);
    public final HiiragiItemHandler outputInv = new HiiragiItemHandler(6, IOControllable.Type.OUTPUT, this);

    private HiiragiItemHandler[] getInventories() {
        return new HiiragiItemHandler[]{outputInv, inputInv0, inputInv1, inputInv2, inputInv3, inputInv4, inputInv5};
    }

    public final HiiragiFluidTank inputTank0 = new HiiragiFluidTank(64000, IOControllable.Type.INPUT);
    public final HiiragiFluidTank inputTank1 = new HiiragiFluidTank(64000, IOControllable.Type.INPUT);
    public final HiiragiFluidTank inputTank2 = new HiiragiFluidTank(64000, IOControllable.Type.INPUT);
    public final HiiragiFluidTank outputTank0 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    public final HiiragiFluidTank outputTank1 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    public final HiiragiFluidTank outputTank2 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);

    private HiiragiFluidTank[] getTanks() {
        return new HiiragiFluidTank[]{outputTank0, outputTank1, outputTank2, inputTank0, inputTank1, inputTank2};
    }

    public EnergyStorage energyStorage;

    public void initMachineProperty(IMachineProperty property) {
        this.property = property;
        this.maxCount = property.getProcessTime();
        this.inventory = new HiiragiItemHandlerWrapper(Arrays.copyOfRange(getInventories(), 0, property.getItemSlotCounts() + 2));
        this.tank = new HiiragiFluidTankWrapper(Arrays.copyOfRange(getTanks(), 0, property.getFluidSlotCounts() + 3));
        this.energyStorage = new EnergyStorage(property.getEnergyCapacity());
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.inventory);
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank);
        } else if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(this.energyStorage);
        } else return null;
    }

    //    Event    //


    @Override
    public boolean onTileActivated(World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing) {
        if (!world.isRemote) openGui(player, world, pos);
        return true;
    }

    @Override
    public void onTilePlaced(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        OptionalUtil.getItemImplemented(stack, IModuleItem.class)
                .map(module -> module.toMachineProperty(stack))
                .ifPresent(this::initMachineProperty);
    }

    @Override
    public void onTileRemoved(World world, BlockPos pos, IBlockState state) {
        HiiragiUtil.dropInventoriesItems(world, pos, inputInv0, inputInv1, inputInv2, inputInv3, inputInv4, inputInv5, outputInv);
    }

    //    IMachineProperty    //

    @Override
    public int getProcessTime() {
        return property != null ? property.getProcessTime() : IMachineProperty.super.getProcessTime();
    }

    @Override
    public int getEnergyRate() {
        return property != null ? property.getEnergyRate() : IMachineProperty.super.getEnergyRate();
    }

    @Override
    public int getItemSlotCounts() {
        return property != null ? property.getItemSlotCounts() : IMachineProperty.super.getItemSlotCounts();
    }

    @Override
    public int getFluidSlotCounts() {
        return property != null ? property.getFluidSlotCounts() : IMachineProperty.super.getFluidSlotCounts();
    }

}