package hiiragi283.material.api.capability.machine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;

public class MachineProperty implements IMachineProperty, INBTSerializable<NBTTagCompound> {

    private final Map<IMachineProperty.Type, Integer> map = new HashMap<>();

    public MachineProperty() {
    }

    public MachineProperty(IMachineProperty other) {
        for (IMachineProperty.Type type : IMachineProperty.Type.values()) {
            map.put(type, other.getValue(type));
        }
    }

    public MachineProperty(Map<IMachineProperty.Type, Integer> map) {
        this.map.putAll(map);
    }

    //    IMachineProperty    //

    @Override
    public int getValue(IMachineProperty.Type type) {
        return map.getOrDefault(type, getDefaultValue(type));
    }

    @Override
    public void setValue(IMachineProperty.Type type, int amount) {
        map.put(type, amount);
    }

    //    INBTSerializable    //

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