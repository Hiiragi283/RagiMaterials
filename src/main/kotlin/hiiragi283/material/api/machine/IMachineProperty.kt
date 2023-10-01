package hiiragi283.material.api.machine

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.getIntegerOrNull
import hiiragi283.material.util.getStringOrNull
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTTagString
import net.minecraftforge.common.util.Constants

interface IMachineProperty : HiiragiJsonSerializable {

    val recipeType: MachineType
    val processTime: Int
    val energyRate: Int
    val itemSlots: Int
    val fluidSlots: Int
    val machineTraits: Set<MachineTrait>

    fun getEnergyRequired(): Int = processTime * energyRate

    fun getEnergyCapacity(): Int = getEnergyRequired() * 5

    fun serialize() = NBTTagCompound().also { tag: NBTTagCompound ->
        tag.setString(KEY_TYPE, recipeType.name)
        tag.setInteger(KEY_PROCESS, processTime)
        tag.setInteger(KEY_RATE, energyRate)
        tag.setInteger(KEY_ITEM, itemSlots)
        tag.setInteger(KEY_FLUID, fluidSlots)
        val tagList = NBTTagList()
        machineTraits
            .map(MachineTrait::name)
            .map(::NBTTagString)
            .forEach { tagList.appendTag(it) }
        tag.setTag(KEY_TRAIT, tagList)
    }

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        root.addProperty("recipe_type", recipeType.name)
        root.addProperty("process_time", processTime)
        root.addProperty("energy_rate", energyRate)
        root.addProperty("item_slots", itemSlots)
        root.addProperty("fluid_slots", fluidSlots)

        val traitsJson = JsonArray()
        machineTraits.map(MachineTrait::name).forEach(traitsJson::add)
        root.add("machine_traits", traitsJson)

        return root

    }

    companion object {

        const val KEY_TYPE = "RecipeType"
        const val KEY_PROCESS = "ProcessTime"
        const val KEY_RATE = "EnergyRate"
        const val KEY_ITEM = "ItemSlots"
        const val KEY_FLUID = "FluidSlots"
        const val KEY_TRAIT = "MachineTraits"

        fun of(tag: NBTTagCompound) = of(
            recipeType = tag.getStringOrNull(KEY_TYPE)?.let(MachineType.Companion::from) ?: MachineType.NONE,
            processTime = tag.getIntegerOrNull(KEY_PROCESS) ?: 100,
            energyRate = tag.getIntegerOrNull(KEY_RATE) ?: 32,
            itemSlots = tag.getIntegerOrNull(KEY_ITEM) ?: 1,
            fluidSlots = tag.getIntegerOrNull(KEY_FLUID) ?: 0,
            machineTraits = getModuleTraits(tag)
        )

        private fun getModuleTraits(tag: NBTTagCompound): Set<MachineTrait> {
            val tagList: NBTTagList = tag.getTagList(KEY_TRAIT, Constants.NBT.TAG_STRING)
            return (0 until tagList.tagCount())
                .map(tagList::getStringTagAt)
                .mapNotNull(MachineTrait.Companion::from)
                .toSet()
        }

        fun of(
            recipeType: MachineType = MachineType.NONE,
            processTime: Int = 100,
            energyRate: Int = 32,
            itemSlots: Int = 1,
            fluidSlots: Int = 0,
            machineTraits: Set<MachineTrait> = mutableSetOf()
        ): IMachineProperty = object : IMachineProperty {
            override var recipeType: MachineType = recipeType
            override var processTime: Int = processTime
            override var energyRate: Int = energyRate
            override var itemSlots: Int = itemSlots
            override var fluidSlots: Int = fluidSlots
            override var machineTraits: Set<MachineTrait> = machineTraits
        }

    }

}