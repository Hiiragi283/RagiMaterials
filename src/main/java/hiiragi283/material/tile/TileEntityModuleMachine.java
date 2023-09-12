package hiiragi283.material.tile;

import hiiragi283.material.api.capability.IOControllable;
import hiiragi283.material.api.capability.energy.HiiragiEnergyStorage;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper;
import hiiragi283.material.api.capability.fluid.ModuleMachineFluidTank;
import hiiragi283.material.api.capability.item.HiiragiItemHandler;
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper;
import hiiragi283.material.api.capability.item.ModuleMachineInputItemHandler;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.machine.MachineContents;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.api.tile.IMaterialTile;
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
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class TileEntityModuleMachine extends HiiragiTileEntity.Tickable implements IMachineProperty, IMaterialTile {

    public TileEntityModuleMachine() {
        super(100);
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString("Module Machine");
    }

    //    NBT    //

    @NotNull
    public IMachineProperty machineProperty = IMachineProperty.of();

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventoryInput.deserializeNBT(compound.getCompoundTag("InventoryInput"));
        inventoryOutput.deserializeNBT(compound.getCompoundTag("InventoryOutput"));
        tankInput0.deserializeNBT(compound.getCompoundTag("TankInput0"));
        tankInput1.deserializeNBT(compound.getCompoundTag("TankInput1"));
        tankInput2.deserializeNBT(compound.getCompoundTag("TankInput2"));
        tankOutput0.deserializeNBT(compound.getCompoundTag("TankOutput0"));
        tankOutput1.deserializeNBT(compound.getCompoundTag("TankOutput1"));
        tankOutput2.deserializeNBT(compound.getCompoundTag("TankOutput2"));
        energyStorage.deserializeNBT(compound.getCompoundTag(HiiragiUtil.BATTERY));
        machineProperty.deserializeNBT(compound.getCompoundTag(HiiragiUtil.MACHINE_PROPERTY));
        HiiragiMaterial.REGISTRY.getValue(compound.getString(HiiragiUtil.MATERIAL))
                .ifPresent(material -> MATERIAL = material);
        initMachineProperty(machineProperty);
        super.readFromNBT(compound);
    }

    @NotNull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("InventoryInput", inventoryInput.serializeNBT());
        compound.setTag("InventoryOutput", inventoryOutput.serializeNBT());
        compound.setTag("TankInput0", tankInput0.serializeNBT());
        compound.setTag("TankInput1", tankInput1.serializeNBT());
        compound.setTag("TankInput2", tankInput2.serializeNBT());
        compound.setTag("TankOutput0", tankOutput0.serializeNBT());
        compound.setTag("TankOutput1", tankOutput1.serializeNBT());
        compound.setTag("TankOutput2", tankOutput2.serializeNBT());
        compound.setTag(HiiragiUtil.BATTERY, energyStorage.serializeNBT());
        compound.setTag(HiiragiUtil.MACHINE_PROPERTY, machineProperty.serializeNBT());
        Optional.ofNullable(MATERIAL)
                .map(material -> material.name)
                .ifPresent(name -> compound.setString(HiiragiUtil.MATERIAL, name));
        return super.writeToNBT(compound);
    }

    //    Capability    //

    public final ModuleMachineInputItemHandler inventoryInput = new ModuleMachineInputItemHandler(this);
    public final HiiragiItemHandler inventoryOutput = new HiiragiItemHandler(6, IOControllable.Type.OUTPUT, this);

    public final ModuleMachineFluidTank tankInput0 = new ModuleMachineFluidTank(0, IOControllable.Type.INPUT, this);
    public final ModuleMachineFluidTank tankInput1 = new ModuleMachineFluidTank(1, IOControllable.Type.INPUT, this);
    public final ModuleMachineFluidTank tankInput2 = new ModuleMachineFluidTank(2, IOControllable.Type.INPUT, this);
    public final ModuleMachineFluidTank tankOutput0 = new ModuleMachineFluidTank(3, IOControllable.Type.OUTPUT, this);
    public final ModuleMachineFluidTank tankOutput1 = new ModuleMachineFluidTank(4, IOControllable.Type.OUTPUT, this);
    public final ModuleMachineFluidTank tankOutput2 = new ModuleMachineFluidTank(5, IOControllable.Type.OUTPUT, this);
    @NotNull
    public HiiragiFluidTankWrapper tank = new HiiragiFluidTankWrapper(tankOutput0, tankOutput1, tankOutput2);

    public HiiragiEnergyStorage energyStorage = new HiiragiEnergyStorage(0);

    public void initMachineProperty(IMachineProperty property) {
        this.machineProperty = property;
        this.maxCount = property.getProcessTime();
        this.inventoryInput.setMaxSlots(property.getItemSlotCounts());
        List<HiiragiFluidTank> list = new ArrayList<>();
        if (property.getFluidSlotCounts() >= 1) {
            list.add(tankInput0);
        }
        if (property.getFluidSlotCounts() >= 2) {
            list.add(tankInput1);
        }
        if (property.getFluidSlotCounts() >= 3) {
            list.add(tankInput2);
        }
        list.add(tankOutput0);
        list.add(tankOutput1);
        list.add(tankOutput2);
        this.tank = new HiiragiFluidTankWrapper(list);
        this.energyStorage.setCapacity(property.getEnergyCapacity());
    }

    public MachineContents getMachineContents() {
        return new MachineContents(inventoryInput, tankInput0, tankInput1, tankInput2, machineProperty);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new HiiragiItemHandlerWrapper(inventoryInput, inventoryOutput));
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
        HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .ifPresent(material -> MATERIAL = material);
        OptionalUtil.getItemImplemented(stack, IModuleItem.class)
                .map(module -> module.toMachineProperty(stack))
                .ifPresent(this::initMachineProperty);
    }

    @Override
    public void onTileRemoved(World world, BlockPos pos, IBlockState state) {
        HiiragiUtil.dropInventoriesItems(world, pos, inventoryInput, inventoryOutput);
    }

    //    IMachineProperty    //

    @Override
    public int getProcessTime() {
        return machineProperty.getProcessTime();
    }

    @Override
    public int getEnergyRate() {
        return machineProperty.getEnergyRate();
    }

    @Override
    public int getItemSlotCounts() {
        return machineProperty.getItemSlotCounts();
    }

    @Override
    public int getFluidSlotCounts() {
        return machineProperty.getFluidSlotCounts();
    }

    //    IMaterialTile    //

    @Nullable
    private HiiragiMaterial MATERIAL = null;

    @Override
    public Optional<HiiragiMaterial> getMaterial() {
        return Optional.ofNullable(MATERIAL);
    }

}