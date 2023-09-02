package hiiragi283.material.api.capability.machine;

public interface IMachineProperty {

    int getValue(Type property);

    void setValue(Type property, int amount);

    void addValue(Type property, int amount);

    void removeValue(Type property, int amount);

    void clearValue(Type property);

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