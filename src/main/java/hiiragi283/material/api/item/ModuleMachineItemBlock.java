package hiiragi283.material.api.item;

import hiiragi283.material.block.BlockModuleMachine;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ModuleMachineItemBlock extends HiiragiItemBlock implements IModuleItem {

    public ModuleMachineItemBlock(BlockModuleMachine block) {
        super(block, 0);
    }

    //    IModuleItem    //

    @Override
    public int getProcessTime(ItemStack stack) {
        return Math.max(0, stack.getOrCreateSubCompound(HiiragiUtil.MACHINE_PROPERTY).getInteger(IMachineProperty.KEY_PROCESS));
    }

    @Override
    public int getEnergyRate(ItemStack stack) {
        return Math.max(0, stack.getOrCreateSubCompound(HiiragiUtil.MACHINE_PROPERTY).getInteger(IMachineProperty.KEY_RATE));
    }

    @Override
    public int getItemSlotCounts(ItemStack stack) {
        return Math.max(1, stack.getOrCreateSubCompound(HiiragiUtil.MACHINE_PROPERTY).getInteger(IMachineProperty.KEY_ITEM));
    }

    @Override
    public int getFluidSlotCounts(ItemStack stack) {
        return Math.max(0, stack.getOrCreateSubCompound(HiiragiUtil.MACHINE_PROPERTY).getInteger(IMachineProperty.KEY_FLUID));
    }

}