package hiiragi283.ragi_materials.util

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTankInfo
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.fluids.capability.FluidTankProperties
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fluids.capability.IFluidTankProperties

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/DCTank.java
*/

class RagiTank(var cap: Int): IFluidTank, IFluidHandler {

    private var fluidStack: FluidStack? = null

    fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        return if(fluidStack !== null) RagiUtil.setFluidToNBT(tag, fluidStack!!) else NBTTagCompound() //NBTタグに液体を書き込む
    }

    fun readFromNBT(tag: NBTTagCompound) {
        if (tag.hasKey("Fluid")) {
            fluidStack = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("Fluid")) //NBTタグから液体を読み取る
        }
    }

    //    IFluidHandler    //

    override fun getFluid(): FluidStack? {
        return fluidStack
    }

    override fun getFluidAmount(): Int {
        return if (fluidStack !== null) fluidStack!!.amount else 0
    }

    override fun getCapacity(): Int {
        return this.cap
    }

    override fun getInfo(): FluidTankInfo {
        return FluidTankInfo(this)
    }

    override fun getTankProperties(): Array<IFluidTankProperties> {
        return arrayOf(FluidTankProperties(fluid, capacity))
    }

    //    IFluidTank    //

    override fun fill(resource: FluidStack?, doFill: Boolean): Int {
        return if (resource !== null) {
            if (fluidStack !== null) {
                //現在のタンクの空き容量を取得
                val amountRemain = this.cap - fluidStack!!.amount
                //搬入したい液体の量と空き容量を比較して，実際に投入する量を取得
                val amountFill = resource.amount.coerceAtMost(amountRemain)
                if (doFill) {
                    fluidStack!!.amount += amountFill //液体の量を増やす
                }
                amountFill
            } else {
                //タンクが空の場合，搬入した液体をそのまま入れる
                fluidStack = resource.copy()
                0
            }
        } else 0
    }

    override fun drain(resource: FluidStack?, doDrain: Boolean): FluidStack? {
        return if (resource !== null) drain(resource.amount, doDrain) else null
    }

    override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack? {
        return if (fluidStack !== null && maxDrain > 0) {
            //搬出したい液体の量と現在の蓄積量を比較して，実際に搬出する量を取得
            val amountDrain = maxDrain.coerceAtMost(fluidStack!!.amount)
            if (doDrain) {
                fluidStack!!.amount -= amountDrain //液体の量を減らす
                //液体の量が0以下の場合
                if (fluidStack!!.amount <= 0) {
                    fluidStack = null //液体を空にする
                }
            }
            FluidStack(fluidStack!!.fluid, amountDrain) //搬出した液体を返す ぬるぽ
        } else null //搬出したい量が負の場合，nullを返す
    }

}