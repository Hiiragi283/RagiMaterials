package hiiragi283.material.api.machine

import hiiragi283.material.api.recipe.IMachineRecipe
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable

interface IMachineProperty : INBTSerializable<NBTTagCompound> {

    fun getRecipeType(): IMachineRecipe.Type

    fun getProcessTime(): Int = 100

    fun getEnergyRate(): Int = 32

    fun getRequiredEnergy(): Int = getProcessTime() * getEnergyRate()

    fun getEnergyCapacity(): Int = getRequiredEnergy() * 5

    fun getItemSlots(): Int = 1

    fun getFluidSlots(): Int = 0

    fun getModuleTraits(): Set<ModuleTrait> = setOf()

    //    INBTSerializable    //
    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().also { tag: NBTTagCompound ->
            tag.setInteger(KEY_PROCESS, getProcessTime())
            tag.setInteger(KEY_RATE, getEnergyRate())
            tag.setInteger(KEY_ITEM, getItemSlots())
            tag.setInteger(KEY_FLUID, getFluidSlots())
        }
    }

    class Impl(@JvmField val recipeType: IMachineRecipe.Type) : IMachineProperty {

        @JvmField
        var processTime: Int = 100
        @JvmField
        var energyRate: Int = 32
        @JvmField
        var itemSlots: Int = 1
        @JvmField
        var fluidSlots: Int = 0

        @JvmField
        val moduleTraits: MutableSet<ModuleTrait> = mutableSetOf()

        //    IMachineProperty    //

        override fun getRecipeType(): IMachineRecipe.Type = recipeType

        override fun getProcessTime(): Int = processTime

        override fun getEnergyRate(): Int = energyRate

        override fun getItemSlots(): Int = itemSlots

        override fun getFluidSlots(): Int = fluidSlots

        override fun getModuleTraits(): Set<ModuleTrait> = moduleTraits

        //    INBTSerializable    //

        override fun deserializeNBT(nbt: NBTTagCompound) {
            if (nbt.hasKey(KEY_PROCESS)) processTime = nbt.getInteger(KEY_PROCESS)
            if (nbt.hasKey(KEY_RATE)) energyRate = nbt.getInteger(KEY_RATE)
            if (nbt.hasKey(KEY_ITEM)) itemSlots = nbt.getInteger(KEY_ITEM)
            if (nbt.hasKey(KEY_FLUID)) fluidSlots = nbt.getInteger(KEY_FLUID)
        }

    }

    companion object {

        fun of(recipeType: IMachineRecipe.Type = IMachineRecipe.Type.NONE, init: Impl.() -> Unit = {}): IMachineProperty.Impl {
            val property = Impl(recipeType)
            property.init()
            return property
        }

        const val KEY_PROCESS = "ProcessTime"
        const val KEY_RATE = "EnergyRate"
        const val KEY_ITEM = "ItemSlots"
        const val KEY_FLUID = "FluidSlots"
        const val KEY_TRAIT = "ModuleTraits"

    }

}