package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.tile.TileEntityModuleMachine
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

fun createMachineRecipe(type: IMachineRecipe.Type, init: MachineRecipe.() -> Unit): MachineRecipe {
    val recipe = MachineRecipe(type)
    recipe.init()
    return recipe
}

class MachineRecipe(override val type: IMachineRecipe.Type) : IMachineRecipe {

    private val traitsInternal: MutableSet<ModuleTrait> = mutableSetOf()

    private val inputItemsInternal: MutableList<List<ItemStack>> = mutableListOf()

    private val inputFluidsInternal: MutableList<FluidStack> = mutableListOf()

    private val outputItemsInternal: MutableList<ItemStack> = mutableListOf()

    private val outputFluidsInternal: MutableList<FluidStack> = mutableListOf()

    private fun validate() {
        if (inputItems.size > 6)
            throw IndexOutOfBoundsException("Size: ${inputItems.size} is over 6!")
        if (inputFluids.size > 3)
            throw IndexOutOfBoundsException("Size: ${inputFluids.size} is over 3!")
        if (outputItems.size > 6)
            throw IndexOutOfBoundsException("Size: ${outputItems.size} is over 6!")
        if (outputFluids.size > 3)
            throw IndexOutOfBoundsException("Size: ${outputFluids.size} is over 3!")
    }

    //    IMachineRecipe    //

    override val requiredTraits: Set<ModuleTrait> = traitsInternal

    override fun matches(tile: TileEntityModuleMachine): Boolean {
        validate()
        //素材をすべて搬入できるか
        inputItems.forEachIndexed { index: Int, list: List<ItemStack> ->
            if (!list.any { stack -> !tile.inventoryInput.extractItem(index, stack.count, true).isEmpty }) {
                return false
            }
        }
        //完成品を搬入して一つでも余りが出ないか
        outputItems.forEachIndexed { index: Int, stack: ItemStack ->
            if (!tile.inventoryOutput.insertItem(index, stack, true).isEmpty) {
                return false
            }
        }
        //inputFluidsに含まれる液体が搬出できるかどうか
        inputFluids.forEachIndexed { index: Int, fluidStack: FluidStack ->
            val stackDrained: FluidStack? = tile.getTank(index).drain(fluidStack, false)
            if (stackDrained == null || !stackDrained.isFluidEqual(fluidStack) || stackDrained.amount != fluidStack.amount) {
                return false
            }
        }
        //outputFluidsに含まれる液体が搬入できるかどうか
        outputFluids.forEachIndexed { index: Int, fluidStack: FluidStack ->
            if (tile.getTank(index + 3).fill(fluidStack, false) != fluidStack.amount) {
                return false
            }
        }
        //energyStorageからエネルギーを消費できるかどうか
        val energyRequired: Int = tile.machineProperty.getEnergyRequired()
        if (tile.energyStorage.extractEnergy(energyRequired, true) != energyRequired) return false
        //tileが要求された特性をすべて持っている -> true
        return tile.machineProperty.moduleTraits.containsAll(requiredTraits)
    }

    override fun process(tile: TileEntityModuleMachine) {
        //ItemStack
        inputItems.forEachIndexed { index: Int, list: List<ItemStack> ->
            list.forEach { stack: ItemStack -> tile.inventoryInput.extractItem(index, stack.count, false) }
        }
        outputItems.forEachIndexed { index: Int, stack: ItemStack ->
            tile.inventoryOutput.insertItem(index, stack, false)
        }
        //FluidStack
        inputFluids.forEachIndexed { index, fluidStack ->
            tile.getTank(index).drain(fluidStack, true)
        }
        outputFluids.forEachIndexed { index, fluidStack ->
            tile.getTank(index + 3).fill(fluidStack, true)
        }
        //Energy
        tile.energyStorage.extractEnergy(tile.machineProperty.getEnergyRequired(), false)
    }

    override val inputItems: List<List<ItemStack>> = inputItemsInternal

    override val inputFluids: List<FluidStack> = inputFluidsInternal

    override val outputItems: List<ItemStack> = outputItemsInternal

    override val outputFluids: List<FluidStack> = outputFluidsInternal

}