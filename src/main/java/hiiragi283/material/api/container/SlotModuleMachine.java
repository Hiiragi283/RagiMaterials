package hiiragi283.material.api.container;

import hiiragi283.material.api.tile.TileEntityModuleMachine;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class SlotModuleMachine extends SlotItemHandler {

    private final TileEntityModuleMachine tile;
    private final int slotNumber;

    public SlotModuleMachine(TileEntityModuleMachine tile, int slotNumber, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.tile = tile;
        this.slotNumber = slotNumber;
    }

    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        return tile.getItemSlotCounts() >= slotNumber;
    }

}