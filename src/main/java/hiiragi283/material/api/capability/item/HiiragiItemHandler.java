package hiiragi283.material.api.capability.item;

import hiiragi283.material.api.capability.FaceControllable;
import hiiragi283.material.api.capability.IOControllable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HiiragiItemHandler extends ItemStackHandler implements FaceControllable, IOControllable {

    @NotNull
    private final IOControllable.Type ioType;
    @Nullable
    private final EnumFacing facing;
    @Nullable
    private final TileEntity tile;

    public HiiragiItemHandler(int size) {
        this(size, Type.GENERAL);
    }

    public HiiragiItemHandler(int size, IOControllable.Type ioType) {
        this(size, ioType, null);
    }

    public HiiragiItemHandler(int size, @NotNull IOControllable.Type ioType, @Nullable EnumFacing facing) {
        this(size, ioType, facing, null);
    }

    public HiiragiItemHandler(int size, @NotNull IOControllable.Type ioType, @Nullable EnumFacing facing, @Nullable TileEntity tile) {
        super(size);
        this.ioType = ioType;
        this.facing = facing;
        this.tile = tile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        if (tile != null) {
            tile.markDirty();
        }
    }

    //    Custom    //

    public void clear() {
        this.stacks = NonNullList.withSize(this.getSlots(), ItemStack.EMPTY);
    }

    public boolean isEmpty() {
        return this.stacks.stream().allMatch(ItemStack::isEmpty);
    }

    //    FaceControllable    //

    @Override
    public @Nullable EnumFacing getFacing() {
        return facing;
    }

    //    IOControllable    //

    @Override
    public @NotNull Type getIOType() {
        return ioType;
    }
}