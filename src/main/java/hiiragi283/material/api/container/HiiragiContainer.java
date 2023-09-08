package hiiragi283.material.api.container;

import hiiragi283.material.api.tile.HiiragiTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.jetbrains.annotations.NotNull;

public abstract class HiiragiContainer<T extends HiiragiTileEntity> extends Container {

    public final T tile;
    public final EntityPlayer player;
    public final InventoryPlayer inventoryPlayer;

    public HiiragiContainer(T tile, EntityPlayer player) {
        this.tile = tile;
        this.player = player;
        this.inventoryPlayer = player.inventory;
    }

    @Override
    public boolean canInteractWith(@NotNull EntityPlayer playerIn) {
        return true;
    }

    protected void initSlotsPlayer(int posY) {
        //プレイヤーのインベントリのスロットを設定
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, x + y * 9 + 9, 8 + x * 18, y * 18 + posY));
            }
        }
        //プレイヤーのホットバーのスロットを設定
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + x * 18, 3 * 18 + (posY + 4)));
        }
    }

}