package hiiragi283.material.api.capability.fluid

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fluids.capability.IFluidTankProperties

class HiiragiFluidTankWrapper(private val tanks: MutableList<HiiragiFluidTank>) : IFluidHandler,
    INBTSerializable<NBTTagCompound> {

    constructor(vararg tanks: HiiragiFluidTank) : this(tanks.toMutableList())

    //    FluidHandler    //

    override fun getTankProperties(): Array<IFluidTankProperties> {
        return tanks.flatMap { tank: HiiragiFluidTank -> tank.tankProperties.toList() }.toTypedArray()
    }

    override fun fill(resource: FluidStack, doFill: Boolean): Int {
        var result = 0
        for (tank: HiiragiFluidTank in tanks) {
            if (tank.ioType.canInsert) {
                val resultTest: Int = tank.fill(resource, false)
                if (resultTest > 0) {
                    result = tank.fill(resource, doFill)
                    break
                }
            }
        }
        return result
    }

    override fun drain(resource: FluidStack, doDrain: Boolean): FluidStack? = drain(resource.amount, doDrain)

    override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack? {
        var result: FluidStack? = null
        for (tank: HiiragiFluidTank in tanks) {
            if (tank.ioType.canExtract) {
                val resultTest: FluidStack? = tank.drain(maxDrain, false)
                if (resultTest != null) {
                    result = tank.drain(maxDrain, doDrain)
                    break
                }
            }
        }
        return result
    }

    //    INBTSerializable<NBTTagCompound>    //

    override fun serializeNBT(): NBTTagCompound {
        val tagList = NBTTagList()
        tanks.forEach { tank: HiiragiFluidTank -> tagList.appendTag(tank.serializeNBT()) }
        return NBTTagCompound().also { tag: NBTTagCompound -> tag.setTag("Fluids", tagList) }
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        val tagList: NBTTagList = nbt.getTagList("Fluids", Constants.NBT.TAG_COMPOUND)
        for (i in 0 until tagList.tagCount()) {
            tanks[i] = HiiragiFluidTank(0)
            tanks[i].deserializeNBT(tagList.getCompoundTagAt(i))
        }
    }

}