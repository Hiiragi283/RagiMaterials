package hiiragi283.api.widgets;

import com.cleanroommc.modularui.widgets.slot.ModularSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModularSlotOut extends ModularSlot {

    public ModularSlotOut(IItemHandler itemHandler, int index) {
        super(itemHandler, index);
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        return false;
    }
}