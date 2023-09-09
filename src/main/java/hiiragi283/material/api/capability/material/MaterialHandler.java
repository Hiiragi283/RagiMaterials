package hiiragi283.material.api.capability.material;

import hiiragi283.material.api.material.MaterialStack;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MaterialHandler implements IMaterialHandler, INBTSerializable<NBTTagCompound> {

    protected final MaterialStack @NotNull [] materialStacks;
    protected final int capacity;
    protected boolean canFill = true;
    protected boolean canDrain = true;

    public MaterialHandler(int capacity, int slots) {
        this.capacity = capacity;
        this.materialStacks = new MaterialStack[slots];
    }

    public MaterialHandler(int capacity, MaterialStack ... materialStacks) {
        this.capacity = capacity;
        this.materialStacks = materialStacks;
    }

    @Override
    public MaterialStack @NotNull [] getContents() {
        return materialStacks;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean canFill() {
        return canFill;
    }

    @Override
    public boolean canDrain() {
        return canDrain;
    }

    @Override
    public boolean canFillMaterialType(MaterialStack materialStack) {
        return canFill();
    }

    @Override
    public boolean canDrainMaterialType(MaterialStack materialStack) {
        return canDrain();
    }

    @Override
    public int fill(MaterialStack resource, boolean doFill) {
        return canFillMaterialType(resource) ? fillInternal(resource, doFill) : 0;
    }

    public int fillInternal(MaterialStack resource, boolean doFill) {
        //resourceがEMPTYの場合 -> 0
        if (resource.isEmpty()) return 0;
        //doFill = falseの場合 -> 数値だけ返す
        if (!doFill) {
            //各MaterialStackに対して搬入可能かチェック
            for (MaterialStack materialStack : getContents()) {
                //MaterialStackがEMPTY -> 最大値を超えない範囲で搬入
                if (materialStack.isEmpty()) {
                    return Math.min(getCapacity(), resource.amount);
                }
                //materialが同じ場合 -> 最大値を超えない範囲で搬入
                else if (materialStack.equalsMaterial(resource)) {
                    return Math.min(getCapacity() - materialStack.amount, resource.amount);
                }
            }
            //搬入不可能 -> 0
        }
        //doFill = trueの場合 -> 実際に搬入を行う
        else {
            //各MaterialStackに対して搬入可能かチェック
            for (int i = 0; i < getContents().length; i++) {
                MaterialStack materialStack = getContents()[i];
                //MaterialStackがEMPTY -> 最大値を超えない範囲で搬入
                if (materialStack.isEmpty()) {
                    getContents()[i].amount = Math.min(getCapacity(), resource.amount);
                    return getContents()[i].amount;
                }
                //materialが同じ場合 ->
                else if (materialStack.equalsMaterial(resource)) {
                    //toFillは搬入可能な量を表す
                    int toFill = getCapacity() - materialStack.amount;
                    //搬入量 < toFill -> resource.amountの分だけ増やす
                    if (resource.amount < toFill) {
                        getContents()[i].amount += resource.amount;
                        toFill = resource.amount;
                    }
                    //搬入量 >= toFill -> 最大値にする
                    else {
                        getContents()[i].amount = getCapacity();
                    }
                    return toFill;
                }
            }
            //materialがすべて一致しなかった場合 -> 0
        }
        return 0;
    }

    @Override
    public int drain(MaterialStack resource, boolean doDrain) {
        return canDrainMaterialType(resource) ? drainInternal(resource, doDrain) : 0;
    }

    public int drainInternal(MaterialStack resource, boolean doDrain) {
        //resourceがEMPTYの場合 -> 0
        if (resource.isEmpty()) return 0;
        //doDrain = falseの場合 -> 数値だけ返す
        if (!doDrain) {
            //各MaterialStackに対して搬出可能かチェック
            for (MaterialStack materialStack : getContents()) {
                //MaterialStackがEMPTYでない && materialが同じ -> 0を下回らない範囲で搬出
                if (!materialStack.isEmpty() && materialStack.equalsMaterial(resource)) {
                    //搬出したい量
                    int toDrain = Math.min(materialStack.amount, resource.amount);
                    return Math.max(0, toDrain);
                }
            }
            //MaterialStackがEMPTY || materialが同じでない -> 0
        }
        //doDrain = trueの場合 -> 実際に搬出を行う
        else {
            //各MaterialStackに対して搬出可能かチェック
            for (int i = 0; i < getContents().length; i++) {
                MaterialStack materialStack = getContents()[i];
                //MaterialStackがEMPTYでない && materialが同じ -> 0を下回らない範囲で搬出
                if (!materialStack.isEmpty() && materialStack.equalsMaterial(resource)) {
                    //搬出したい量
                    int toDrain = Math.min(materialStack.amount, resource.amount);
                    //残る量
                    int remaining = materialStack.amount - toDrain;
                    //remaining <= 0 -> EMPTYを代入する
                    if (remaining <= 0) {
                        getContents()[i] = MaterialStack.EMPTY;
                    }
                    //remaining >= 0 -> 減らした量を代入する
                    else {
                        getContents()[i].amount = remaining;
                    }
                    return Math.max(0, toDrain);
                }
            }
            //materialがすべて一致しなかった場合 -> 0
        }
        return 0;
    }

    //    INBTSerializable    //

    @Override
    public NBTTagCompound serializeNBT() {
        var tagList = new NBTTagList();
        for (MaterialStack materialStack : getContents()) {
            tagList.appendTag(materialStack.serializeNBT());
        }
        var tag = new NBTTagCompound();
        tag.setTag(HiiragiUtil.MATERIALS, tagList);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        NBTTagList tagList = nbt.getTagList(HiiragiUtil.MATERIALS, Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            var stack = new MaterialStack(tag);
            getContents()[i] = stack;
        }
    }

}