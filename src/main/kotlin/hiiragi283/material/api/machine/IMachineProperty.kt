package hiiragi283.material.api.machine

import hiiragi283.material.api.recipe.IMachineRecipe
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTTagString
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.INBTSerializable

interface IMachineProperty : INBTSerializable<NBTTagCompound> {

    val recipeType: IMachineRecipe.Type
    var processTime: Int
    var energyRate: Int
    var itemSlots: Int
    var fluidSlots: Int
    val moduleTraits: MutableSet<ModuleTrait>

    fun getEnergyRequired(): Int = processTime * energyRate

    fun getEnergyCapacity(): Int = getEnergyRequired() * 5

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().also { tag: NBTTagCompound ->
            tag.setInteger(KEY_PROCESS, processTime)
            tag.setInteger(KEY_RATE, energyRate)
            tag.setInteger(KEY_ITEM, itemSlots)
            tag.setInteger(KEY_FLUID, fluidSlots)
            val tagList = NBTTagList()
            moduleTraits
                .map(ModuleTrait::name)
                .map(::NBTTagString)
                .forEach { tagList.appendTag(it) }
            tag.setTag(KEY_TRAIT, tagList)
        }
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        if (nbt.hasKey(KEY_PROCESS)) processTime = nbt.getInteger(KEY_PROCESS)
        if (nbt.hasKey(KEY_RATE)) energyRate = nbt.getInteger(KEY_RATE)
        if (nbt.hasKey(KEY_ITEM)) itemSlots = nbt.getInteger(KEY_ITEM)
        if (nbt.hasKey(KEY_FLUID)) fluidSlots = nbt.getInteger(KEY_FLUID)
        if (nbt.hasKey(KEY_TRAIT)) {
            val tagList: NBTTagList = nbt.getTagList(KEY_TRAIT, Constants.NBT.TAG_STRING)
            moduleTraits.addAll(
                (0 until tagList.tagCount())
                    .map(tagList::getStringTagAt)
                    .map(ModuleTrait::valueOf)
            )
        }
    }

    companion object {

        const val KEY_PROCESS = "ProcessTime"
        const val KEY_RATE = "EnergyRate"
        const val KEY_ITEM = "ItemSlots"
        const val KEY_FLUID = "FluidSlots"
        const val KEY_TRAIT = "ModuleTraits"

        fun of(
            recipeType: IMachineRecipe.Type = IMachineRecipe.Type.NONE,
            processTime: Int = 100,
            energyRate: Int = 32,
            itemSlots: Int = 1,
            fluidSlots: Int = 0,
            moduleTraits: MutableSet<ModuleTrait> = mutableSetOf()
        ): IMachineProperty = object : IMachineProperty {
            override val recipeType: IMachineRecipe.Type = recipeType
            override var processTime: Int = processTime
            override var energyRate: Int = energyRate
            override var itemSlots: Int = itemSlots
            override var fluidSlots: Int = fluidSlots
            override var moduleTraits: MutableSet<ModuleTrait> = moduleTraits
        }

    }

}