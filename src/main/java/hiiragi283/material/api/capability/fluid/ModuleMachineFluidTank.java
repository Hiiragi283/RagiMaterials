package hiiragi283.material.api.capability.fluid;

import hiiragi283.material.network.HiiragiFluidMessage;
import hiiragi283.material.network.HiiragiNetworkManager;
import hiiragi283.material.tile.TileEntityModuleMachine;
import org.jetbrains.annotations.Nullable;

public class ModuleMachineFluidTank extends HiiragiFluidTank {

    private final int index;

    public ModuleMachineFluidTank(int index, Type ioType, @Nullable TileEntityModuleMachine tile) {
        super(64000, ioType, tile);
        this.index = index;
    }

    @Override
    protected void onContentsChanged() {
        super.onContentsChanged();
        if (tile != null) {
            HiiragiNetworkManager.INSTANCE.sendToAll(new HiiragiFluidMessage(tile.getPos(), index, getFluid()));
        }
    }

}