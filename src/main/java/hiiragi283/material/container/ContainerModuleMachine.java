package hiiragi283.material.container;

import hiiragi283.material.api.container.HiiragiContainer;
import hiiragi283.material.api.container.SlotModuleMachine;
import hiiragi283.material.api.container.SlotOutputItemHandler;
import hiiragi283.material.api.tile.TileEntityModuleMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ContainerModuleMachine extends HiiragiContainer<TileEntityModuleMachine> {

    public ContainerModuleMachine(TileEntityModuleMachine tile, EntityPlayer player) {
        super(tile, player);
        this.addSlotToContainer(new SlotModuleMachine(tile, 1, tile.inputInv0, 0, 8 + 18, 18));
        this.addSlotToContainer(new SlotModuleMachine(tile, 2, tile.inputInv1, 0, 8 + 18 * 2, 18));
        this.addSlotToContainer(new SlotModuleMachine(tile, 3, tile.inputInv2, 0, 8 + 18 * 3, 18));
        this.addSlotToContainer(new SlotModuleMachine(tile, 4, tile.inputInv3, 0, 8 + 18, 18 * 2));
        this.addSlotToContainer(new SlotModuleMachine(tile, 5, tile.inputInv4, 0, 8 + 18 * 2, 18 * 2));
        this.addSlotToContainer(new SlotModuleMachine(tile, 6, tile.inputInv5, 0, 8 + 18 * 3, 18 * 2));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 5, 18));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 6, 18));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 7, 18));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 5, 18 * 2));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 6, 18 * 2));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 7, 18 * 2));
        this.initSlotsPlayer(84);
    }

    @NotNull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot.getHasStack()) {
            ItemStack stackSlot = slot.getStack();
            stack = stackSlot.copy();
            switch (index) {
                //Input, Output -> Inventory, Hotbar
                case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 -> {
                    if (!mergeItemStack(stackSlot, 4, inventorySlots.size(), false)) return ItemStack.EMPTY;
                }
                //Inventory, Hotbar -> Input
                default -> {
                    if (!mergeItemStack(stackSlot, 0, 4, false)) return ItemStack.EMPTY;
                }
            }
            if (stackSlot.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else slot.onSlotChanged();
        }
        return stack;
    }

}