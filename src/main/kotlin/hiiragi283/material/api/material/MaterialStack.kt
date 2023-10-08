package hiiragi283.material.api.material

import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

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

    fun addTooltip(event: ItemTooltipEvent) {
        addTooltip(event.toolTip)
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