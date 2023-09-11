package hiiragi283.material.api.capability.energy;

import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class HiiragiEnergyStorage extends EnergyStorage implements INBTSerializable<NBTTagCompound> {

    public HiiragiEnergyStorage(int capacity) {
        super(capacity);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //    INBTSerializable    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tag = new NBTTagCompound();
        tag.setInteger(HiiragiUtil.CAPACITY, this.capacity);
        tag.setInteger(HiiragiUtil.AMOUNT, this.energy);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.capacity = nbt.getInteger(HiiragiUtil.CAPACITY);
        this.energy = nbt.getInteger(HiiragiUtil.AMOUNT);
    }

}