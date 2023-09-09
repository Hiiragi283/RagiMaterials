package hiiragi283.material.container;

import hiiragi283.material.api.container.HiiragiContainer;
import hiiragi283.material.api.container.SlotOutputItemHandler;
import hiiragi283.material.tile.TileEntityModuleInstaller;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ContainerModuleInstaller extends HiiragiContainer<TileEntityModuleInstaller> {

    public ContainerModuleInstaller(TileEntityModuleInstaller tile, EntityPlayer player) {
        super(tile, player);
        this.addSlotToContainer(new SlotItemHandler(tile.inputInv0, 0, 8 + 18, 36));
        this.addSlotToContainer(new SlotItemHandler(tile.inputInv1, 0, 8 + 18 * 3, 36));
        this.addSlotToContainer(new SlotItemHandler(tile.inputInv2, 0, 8 + 18 * 4, 36));
        this.addSlotToContainer(new SlotItemHandler(tile.inputInv3, 0, 8 + 18 * 5, 36));
        this.addSlotToContainer(new SlotOutputItemHandler(tile.outputInv, 0, 8 + 18 * 7, 36));
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
                case 0, 1, 2, 3, 4 -> {
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