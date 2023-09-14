package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.MachineContents
import hiiragi283.material.api.machine.ModuleTrait
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

interface IMachineRecipe {

    val type: Type

    val requiredTraits: Set<ModuleTrait>

    fun matches(machineContents: MachineContents): Boolean

    //    Inputs    //

    val inputItems: List<List<ItemStack>>

    val inputFluids: List<FluidStack>

    //    Outputs    //

    val outputItems: List<List<ItemStack>>

    val outputFluids: List<FluidStack>

    enum class Type(val nameIn: String) {
        BEND("bend"),
        CRUSH("crush"),
        CUT("cut"),
        PULVERISE("pulverise"),
        SMELT("smelt"),
        WIRE("wire")
    }

}