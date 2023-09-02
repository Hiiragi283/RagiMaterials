package hiiragi283.material.api.capability.machine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;

public class MachineProperty implements IMachineProperty, INBTSerializable<NBTTagCompound> {

    private final Map<IMachineProperty.Type, Integer> map = new HashMap<>();

    public MachineProperty() {
    }

    public MachineProperty(Map<IMachineProperty.Type, Integer> map) {
        this.map.putAll(map);
    }

    //    IMachineProperty    //

    @Override
    public int getValue(IMachineProperty.Type property) {
        return map.get(property);
    }

    @Override
    public void setValue(IMachineProperty.Type property, int amount) {
        map.put(property, amount);
    }

    @Override
    public void addValue(IMachineProperty.Type property, int amount) {
        int amountPre = getValue(property);
        map.put(property, amountPre + amount);
    }

    @Override
    public void removeValue(IMachineProperty.Type property, int amount) {
        int amountPre = getValue(property);
        map.put(property, amountPre - amount);
    }

    @Override
    public void clearValue(IMachineProperty.Type property) {
        map.put(property, 0);
    }

    //    IMachineProperty    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tag = new NBTTagCompound();
        for (IMachineProperty.Type type : IMachineProperty.Type.values()) {
            tag.setInteger(type.getName(), getValue(type));
        }
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        for (IMachineProperty.Type type : IMachineProperty.Type.values()) {
            setValue(type, nbt.getInteger(type.getName()));
        }
    }

}