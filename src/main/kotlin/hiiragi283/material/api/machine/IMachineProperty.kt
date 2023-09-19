package hiiragi283.material.api.machine

import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.util.getIntegerOrNull
import hiiragi283.material.util.getStringOrNull
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTTagString
import net.minecraftforge.common.util.Constants

interface IMachineProperty {

    val recipeType: IMachineRecipe.Type
    val processTime: Int
    val energyRate: Int
    val itemSlots: Int
    val fluidSlots: Int
    val moduleTraits: Set<ModuleTrait>

    fun getEnergyRequired(): Int = processTime * energyRate

    fun getEnergyCapacity(): Int = getEnergyRequired() * 5

    fun serialize() = NBTTagCompound().also { tag: NBTTagCompound ->
        tag.setString(KEY_TYPE, recipeType.name)
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

    //    INBTSerializable    //

    companion object {

        const val KEY_TYPE = "RecipeType"
        const val KEY_PROCESS = "ProcessTime"
        const val KEY_RATE = "EnergyRate"
        const val KEY_ITEM = "ItemSlots"
        const val KEY_FLUID = "FluidSlots"
        const val KEY_TRAIT = "ModuleTraits"

        fun of(tag: NBTTagCompound) = of(
            recipeType = tag.getStringOrNull(KEY_TYPE)?.let { IMachineRecipe.Type.valueOf(it) }
                ?: IMachineRecipe.Type.NONE,
            processTime = tag.getIntegerOrNull(KEY_PROCESS) ?: 100,
            energyRate = tag.getIntegerOrNull(KEY_RATE) ?: 32,
            itemSlots = tag.getIntegerOrNull(KEY_ITEM) ?: 1,
            fluidSlots = tag.getIntegerOrNull(KEY_FLUID) ?: 0,
            moduleTraits = getModuleTraits(tag)
        )

        private fun getModuleTraits(tag: NBTTagCompound): Set<ModuleTrait> {
            val tagList: NBTTagList = tag.getTagList(KEY_TRAIT, Constants.NBT.TAG_STRING)
            return (0 until tagList.tagCount())
                .map(tagList::getStringTagAt)
                .map(ModuleTrait::valueOf)
                .toSet()
        }

        fun of(
            recipeType: IMachineRecipe.Type = IMachineRecipe.Type.NONE,
            processTime: Int = 100,
            energyRate: Int = 32,
            itemSlots: Int = 1,
            fluidSlots: Int = 0,
            moduleTraits: Set<ModuleTrait> = mutableSetOf()
        ): IMachineProperty = object : IMachineProperty {
            override var recipeType: IMachineRecipe.Type = recipeType
            override var processTime: Int = processTime
            override var energyRate: Int = energyRate
            override var itemSlots: Int = itemSlots
            override var fluidSlots: Int = fluidSlots
            override var moduleTraits: Set<ModuleTrait> = moduleTraits
        }

    }

}