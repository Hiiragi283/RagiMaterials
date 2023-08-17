package hiiragi283.api.capability.material

import hiiragi283.api.capability.CapabilityIO
import hiiragi283.api.capability.IOType
import hiiragi283.api.material.MaterialStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable

class MaterialHandler(
    @JvmField var stack: MaterialStack = MaterialStack.EMPTY,
    @JvmField var capacity: Int
) : IMaterialHandler, INBTSerializable<NBTTagCompound>, CapabilityIO<MaterialHandler> {

    //    IMaterialHandler    //

    override fun getMaterialStack(): MaterialStack = stack

    fun setMaterialStack(stack: MaterialStack): MaterialHandler = also { this.stack = stack }

    override fun getMaterialAmount(): Int = stack.amount

    fun setMaterialAmount(amount: Int): MaterialHandler = also { this.stack.amount = amount }

    override fun getCapacity(): Int = capacity

    fun setCapacity(capacity: Int): MaterialHandler = also { this.capacity = capacity }

    override fun insertMaterial(resource: MaterialStack, simulate: Boolean): MaterialStack {
        //handler内がEMPTYの場合，確定で搬入する
        if (this.isEmpty()) {
            if (!simulate) this.stack = resource
            return resource
        }
        //HiiragiMaterialが異なる場合はEMPTYを返す
        if (!resource.equalsMaterial(this.stack)) return MaterialStack.EMPTY
        //現在のamount + 搬入したいamount <= capacityの場合
        else if (this.getMaterialAmount() + resource.amount <= this.capacity) {
            if (!simulate) {
                this.stack.addAmount(resource.amount)
                resource.amount = 0
            }
            return resource
        }
        //capacity - 現在のAmount = 搬入可能なAmount
        else {
            val amountDiff: Int = this.capacity - this.getMaterialAmount()
            if (!simulate) {
                resource.removeAmount(amountDiff)
                this.stack.addAmount(amountDiff)
            }
            return resource.copy(amount = amountDiff)
        }
    }

    override fun extractMaterial(resource: MaterialStack, simulate: Boolean): MaterialStack {
        //HiiragiMaterialが異なる場合はEMPTY
        return if (!resource.equalsMaterial(this.stack)) MaterialStack.EMPTY
        //現在のamount - 搬出したいamount > 0の場合
        else if (this.getMaterialAmount() - resource.amount > 0) {
            if (!simulate) {
                this.stack.removeAmount(resource.amount)
            }
            resource
        } else {
            //stackをEMPTYにする
            val copy = this.stack.copy()
            if (!simulate) {
                this.stack = MaterialStack.EMPTY
            }
            copy
        }
    }

    //    ICapabilityIO    //

    override var ioType: IOType = IOType.INPUT

    override fun getIOType(): IOType = ioType

    override fun setIOType(type: IOType): MaterialHandler = also { ioType = type }

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound = stack.serializeNBT()

    override fun deserializeNBT(nbt: NBTTagCompound) {
        stack = MaterialStack.of(nbt)
    }

}