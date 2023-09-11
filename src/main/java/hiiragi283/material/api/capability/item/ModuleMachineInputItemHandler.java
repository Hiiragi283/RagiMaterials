package hiiragi283.material.api.capability.item;

import hiiragi283.material.tile.TileEntityModuleMachine;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModuleMachineInputItemHandler extends HiiragiItemHandler {

    protected int maxSlots = 1;

    public int getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    public ModuleMachineInputItemHandler(@Nullable TileEntityModuleMachine tile) {
        super(6, Type.INPUT, tile);
    }

    //    IItemHandler    //

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return slot <= maxSlots - 1;
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }

}