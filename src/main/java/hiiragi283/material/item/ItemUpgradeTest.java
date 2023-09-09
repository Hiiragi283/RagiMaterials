package hiiragi283.material.item;

import hiiragi283.material.api.item.HiiragiItem;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemUpgradeTest extends HiiragiItem implements IModuleItem {

    public ItemUpgradeTest() {
        super("test_module", 0);
    }

    //    Client    //

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (!isInCreativeTab(tab)) return;
        var stack = new ItemStack(this, 1, 0);
        var tagInner = new NBTTagCompound();
        tagInner.setInteger(IMachineProperty.KEY_PROCESS, 200);
        tagInner.setInteger(IMachineProperty.KEY_RATE, 18);
        tagInner.setInteger(IMachineProperty.KEY_ITEM, 2);
        tagInner.setInteger(IMachineProperty.KEY_FLUID, 1);
        var tag = new NBTTagCompound();
        tag.setTag(HiiragiUtil.MACHINE_PROPERTY, tagInner);
        stack.setTagCompound(tag);
        items.add(stack);
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