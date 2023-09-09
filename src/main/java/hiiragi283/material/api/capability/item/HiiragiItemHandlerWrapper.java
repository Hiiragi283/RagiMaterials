package hiiragi283.material.api.capability.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
public class HiiragiItemHandlerWrapper implements IItemHandlerModifiable, INBTSerializable<NBTTagCompound> {

    private final List<AbstractMap.SimpleEntry<HiiragiItemHandler, Integer>> pairs = new ArrayList<>();

    public HiiragiItemHandlerWrapper(HiiragiItemHandler... itemHandlers) {
        //Wrapperのスロット -> IItemHandlerとそれに対応したスロットのEntry
        for (HiiragiItemHandler handler : itemHandlers) {
            for (int slot = 0; slot < handler.getSlots(); slot++) {
                pairs.add(new AbstractMap.SimpleEntry<>(handler, slot));
            }
        }
    }

    public AbstractMap.SimpleEntry<HiiragiItemHandler, Integer> getHandler(int slot) {
        validateSlotIndex(slot);
        return pairs.get(slot);
    }

    protected void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= pairs.size())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + pairs.size() + ")");
    }

    //    IItemHandlerModifiable    //

    @Override
    public int getSlots() {
        return pairs.size();
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot) {
        validateSlotIndex(slot);
        return getHandler(slot).getKey().getStackInSlot(getHandler(slot).getValue());
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        validateSlotIndex(slot);
        if (canInsert(slot)) {
            return getHandler(slot).getKey().insertItem(getHandler(slot).getValue(), stack, simulate);
        } else return stack;
    }

    public boolean canInsert(int slot) {
        validateSlotIndex(slot);
        return getHandler(slot).getKey().getIOType().canInsert;
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        validateSlotIndex(slot);
        if (canExtract(slot)) {
            return getHandler(slot).getKey().extractItem(getHandler(slot).getValue(), amount, simulate);
        } else return ItemStack.EMPTY;
    }

    public boolean canExtract(int slot) {
        validateSlotIndex(slot);
        return getHandler(slot).getKey().getIOType().canExtract;
    }

    @Override
    public int getSlotLimit(int slot) {
        validateSlotIndex(slot);
        return getHandler(slot).getKey().getSlotLimit(getHandler(slot).getValue());
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        validateSlotIndex(slot);
        getHandler(slot).getKey().setStackInSlot(getHandler(slot).getValue(), stack);
    }

    //    INBTSerializable    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tagList = new NBTTagList();
        for (int slot = 0; slot < pairs.size(); slot++) {
            var pair = getHandler(slot);
            ItemStack stack = pair.getKey().getStackInSlot(pair.getValue());
            if (!stack.isEmpty()) {
                var tag = new NBTTagCompound();
                tag.setInteger("slot", slot);
                stack.writeToNBT(tag);
                tagList.appendTag(tag);
            }
        }
        var ret = new NBTTagCompound();
        ret.setTag("Items", tagList);
        return ret;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        var tagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int index = 0; index < tagList.tagCount(); index++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(index);
            int slot = tag.getInteger("Slot");
            if (slot >= 0 && slot < pairs.size()) {
                var pair = getHandler(slot);
                pair.getKey().setStackInSlot(pair.getValue(), new ItemStack(tag));
            }
        }
    }

}