package ragi_materials.core.capability.fluid

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fluids.capability.IFluidTankProperties

class RagiTankWrapper(private vararg val tanks: RagiTank) : IFluidHandler, INBTSerializable<NBTTagCompound> {

    fun getFluid(index: Int) = tanks[index].fluid

    fun getCapacity(index: Int) = tanks[index].fluid

    //    IFluidHandler    //

    override fun getTankProperties(): Array<IFluidTankProperties> {
        val properties: MutableList<IFluidTankProperties> = mutableListOf()
        tanks.forEach {
            properties.add(it.tankProperties[0])
        }
        return properties.toTypedArray()
    }

    override fun fill(resource: FluidStack?, doFill: Boolean): Int {
        var result = 0
        for (tank in tanks) {
            //搬入可能なtankに対してのみ実行する
            if (tank.getIOType().canInput) {
                val result1 = tank.fill(resource, false)
                if (result1 > 0) {
                    result = tank.fill(resource, doFill)
                    break
                }
            }
        }
        return result
    }

    override fun drain(resource: FluidStack?, doDrain: Boolean): FluidStack? {
        var result: FluidStack? = null
        for (tank in tanks) {
            //搬出可能なtankに対してのみ実行する
            if (tank.getIOType().canOutput) {
                val result1 = tank.drain(resource, false)
                if (result1 !== null) {
                    result = tank.drain(resource, doDrain)
                    break
                }
            }
        }
        return result
    }

    //最初に登録したtankを返す
    override fun drain(maxDrain: Int, doDrain: Boolean) = tanks[0].drain(maxDrain, doDrain)

    //    INBTSerializable<NBTTagCompound>    //

    override fun serializeNBT(): NBTTagCompound {
        val tagList = NBTTagList()
        for (tank in tanks) {
            val stack = tank.fluid
            NBTTagCompound().also { tag ->
                stack?.writeToNBT(tag)
                tagList.appendTag(tag)
            }
        }
        return NBTTagCompound().also { it.setTag("Fluids", tagList) }
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        val tagList = nbt.getTagList("Fluids", Constants.NBT.TAG_COMPOUND)
        for (i in 0 until tagList.tagCount()) {
            val tag = tagList.getCompoundTagAt(i)
            tanks[i].fluid = FluidStack.loadFluidStackFromNBT(tag)
        }
    }
}