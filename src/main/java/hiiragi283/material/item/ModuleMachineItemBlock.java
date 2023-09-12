package hiiragi283.material.item;

import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.block.BlockModuleMachine;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ModuleMachineItemBlock extends HiiragiItemBlock implements IModuleItem {

    public ModuleMachineItemBlock(BlockModuleMachine block) {
        super(block, 0);
    }

    //    IModuleItem    //

    protected NBTTagCompound getMachinePropertyTag(ItemStack stack) {
        return stack.getOrCreateSubCompound(HiiragiUtil.BlockEntityTag).getCompoundTag(HiiragiUtil.MACHINE_PROPERTY);
    }

    @Override
    public int getProcessTime(ItemStack stack) {
        NBTTagCompound tag = getMachinePropertyTag(stack);
        return tag.hasKey(IMachineProperty.KEY_PROCESS) ? tag.getInteger(IMachineProperty.KEY_PROCESS) : 100;
    }

    @Override
    public int getEnergyRate(ItemStack stack) {
        NBTTagCompound tag = getMachinePropertyTag(stack);
        return tag.hasKey(IMachineProperty.KEY_RATE) ? tag.getInteger(IMachineProperty.KEY_RATE) : 32;
    }

    @Override
    public int getItemSlotCounts(ItemStack stack) {
        NBTTagCompound tag = getMachinePropertyTag(stack);
        return tag.hasKey(IMachineProperty.KEY_ITEM) ? tag.getInteger(IMachineProperty.KEY_ITEM) : 1;
    }

    @Override
    public int getFluidSlotCounts(ItemStack stack) {
        NBTTagCompound tag = getMachinePropertyTag(stack);
        return tag.hasKey(IMachineProperty.KEY_FLUID) ? tag.getInteger(IMachineProperty.KEY_FLUID) : 0;
    }

}