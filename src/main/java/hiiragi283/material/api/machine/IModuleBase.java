package hiiragi283.material.api.machine;

public interface IModuleBase {

    default int getProcessTime() {
        return 20 * 5;
    }

    default int getEnergyRate() {
        return 32;
    }

    default int getEnergyCapacity() {
        return 1024;
    }

    default int getItemSlotCounts() {
        return 1;
    }

    default int getFluidSlotCounts() {
        return 0;
    }

}