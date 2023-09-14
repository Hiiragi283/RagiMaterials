package hiiragi283.material.api.material

import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

fun ItemStack.getStacks(): List<MaterialStack> {
    if (this.isEmpty) return listOf()
    //液体から素材のデータを取得しようと試みる
    return this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
        ?.tankProperties
        ?.mapNotNull { it.contents }
        ?.mapNotNull { MaterialStack.of(it) } ?: listOf()
}

data class MaterialStack(val material: HiiragiMaterial, var amount: Int) {

    companion object {

        @JvmStatic
        fun of(compound: NBTTagCompound) = of(compound.getString("material"), compound.getInteger("amount"))

        @JvmStatic
        fun of(fluid: Fluid, amount: Int) = of(fluid.name, amount)

        @JvmStatic
        fun of(fluidStack: FluidStack) = of(fluidStack.fluid, fluidStack.amount)

        @JvmStatic
        fun of(name: String, amount: Int): MaterialStack? =
            HiiragiRegistries.MATERIAL.getValue(name)?.let { MaterialStack(it, amount) }

    }

    fun addTooltip(tooltip: MutableList<String>) {
        material.addTooltip(tooltip, material.getTranslatedName(), amount)
    }

    //    Amount    //

    fun equalsMaterial(other: MaterialStack): Boolean = this.material == other.material

    //    Serialization    //

    fun serializeNBT(): NBTTagCompound = NBTTagCompound().also {
        it.setString("material", material.name)
        it.setInteger("amount", amount)
    }

    //    General    //

    override fun toString(): String = "$material:$amount"

}