package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.tile.TileEntityModuleMachine
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

interface IMachineRecipe {

    val type: Type

    val requiredTraits: Set<ModuleTrait>

    //    Process    //

    fun matches(tile: TileEntityModuleMachine): Boolean

    fun process(tile: TileEntityModuleMachine)

    //    Inputs    //

    val inputItems: List<List<ItemStack>>

    val inputFluids: List<FluidStack>

    //    Outputs    //

    val outputItems: List<ItemStack>

    val outputFluids: List<FluidStack>

    enum class Type(val nameIn: String) {
        BEND("bend"),
        CRUSH("crush"),
        CUT("cut"),
        PULVERISE("pulverise"),
        SMELT("smelt"),
        TEST("test"),
        WIRE("wire"),
        NONE("")
    }

}