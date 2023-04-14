package hiiragi283.ragi_materials.api.capability

import hiiragi283.ragi_materials.api.material.MaterialUtil
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank

open class RagiTank(cap: Int) : FluidTank(cap), INBTSerializable<NBTTagCompound> {

    //    FluidTank    //

    //液体の名前から取得した素材が空でないならtrue
    override fun canFillFluidType(fluid: FluidStack?): Boolean = if (fluid !== null) !MaterialUtil.getMaterial(fluid.fluid.name).isEmpty() else false

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        val tag = NBTTagCompound()
        this.fluid?.writeToNBT(tag)
        return tag
    }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        val fluid = FluidStack.loadFluidStackFromNBT(tag ?: NBTTagCompound())
        this.fluid = fluid
    }

}