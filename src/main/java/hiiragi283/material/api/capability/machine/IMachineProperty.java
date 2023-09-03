package hiiragi283.material.api.capability.machine;

import java.util.List;

public interface IMachineProperty {

    default void addTooltip(List<String> tooltip) {
        tooltip.add("§e=== Machine Property ===");
        tooltip.add("Process Time: " + getValue(Type.PROCESS_TIME));
        tooltip.add("Energy Rate: " + getValue(Type.ENERGY_RATE));
        tooltip.add("Energy Capacity: " + getValue(Type.ENERGY_CAPACITY));
        tooltip.add("Item Slots: " + getValue(Type.ITEM_SLOTS));
        tooltip.add("Fluid Slots: " + getValue(Type.FLUID_SLOTS));
    }

    int getValue(IMachineProperty.Type type);

    default int getDefaultValue(IMachineProperty.Type type) {
        return switch (type) {
            case PROCESS_TIME -> 20 * 10;
            case ENERGY_RATE -> 32;
            case ENERGY_CAPACITY -> 1024;
            case ITEM_SLOTS -> 1;
            case FLUID_SLOTS -> 0;
        };
    }

    void setValue(IMachineProperty.Type type, int amount);

    default void addValue(IMachineProperty.Type type, int amount) {
        setValue(type, getValue(type) + amount);
    }

    default void removeValue(IMachineProperty.Type type, int amount) {
        setValue(type, getValue(type) - amount);
    }

    default void merge(IMachineProperty... properties) {
        for (IMachineProperty property : properties) {
            for (IMachineProperty.Type type : IMachineProperty.Type.values()) {
                if (property.getValue(type) != property.getDefaultValue(type)) {
                    this.addValue(type, property.getValue(type));
                }
            }
        }
    }

    enum Type {

        PROCESS_TIME("processTime"),
        ENERGY_RATE("energyRate"),
        ENERGY_CAPACITY("energyCapacity"),
        ITEM_SLOTS("itemSlots"),
        FLUID_SLOTS("fluidSlots");

        private final String name;

        private Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static IMachineProperty.Type getType(String name) {
            for (IMachineProperty.Type type : IMachineProperty.Type.values()) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Cannot find any type from given name: " + name + "!");
        }

    }

}