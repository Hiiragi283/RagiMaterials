package hiiragi283.material.util;

import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModularOutputSlot extends ModularSlot {

    public ModularOutputSlot(IItemHandler itemHandler, int index) {
        super(itemHandler, index);
    }

    public ModularOutputSlot(IItemHandler itemHandler, int index, boolean phantom) {
        super(itemHandler, index, phantom);
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        return false;
    }

}