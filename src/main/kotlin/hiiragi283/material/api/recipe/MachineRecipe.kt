package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.tile.TileEntityModuleMachine
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

    private val inputFluidsInternal: List<FluidStack> = mutableListOf()

    private val outputItemsInternal: MutableList<List<ItemStack>> = mutableListOf()

    private val outputFluidsInternal: List<FluidStack> = mutableListOf()

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
        //各スロットのindexと一致するlistから，どれか一つでも搬出に成功 -> true
        //それらがすべてtrue -> true
        val resultItem: Boolean = inputItems.indices.map { index ->
            inputItems.getOrNull(index)?.any { stack: ItemStack ->
                !tile.inventoryInput.extractItem(index, stack.count, true).isEmpty
            } ?: false
        }.all { true }
        if (!resultItem) return false
        //完成品をすべて出力スロットに搬入できる -> true

        //inputFluidsに含まれる液体が搬出できる -> true
        val resultFluid: Boolean = inputFluids.indices
            .mapNotNull { index -> tile.getTank(index).drain(inputFluids.getOrNull(index), false) }
            .isEmpty()
        if (!resultFluid) return false
        //energyStorageからエネルギーを消費できる -> true
        val energyRequired: Int = tile.machineProperty.getRequiredEnergy()
        val resultEnergy: Boolean = tile.energyStorage.extractEnergy(energyRequired, true) == energyRequired
        if (!resultEnergy) return false
        //tileが要求された特性をすべて持っている -> true
        return tile.machineProperty.getModuleTraits().containsAll(requiredTraits)
    }

    override fun onProcess(tile: TileEntityModuleMachine) {
        //ItemStack
        inputItems.indices.forEach { index ->
            inputItems.getOrNull(index)?.forEach { stack: ItemStack ->
                tile.inventoryInput.extractItem(index, stack.count, false)
            }
        }
        //FluidStack
        inputFluids.indices.forEach { index -> tile.getTank(index).drain(inputFluids.getOrNull(index), true) }
        //Energy
        tile.energyStorage.extractEnergy(tile.machineProperty.getRequiredEnergy(), false)
    }

    override val inputItems: List<List<ItemStack>> = inputItemsInternal

    override val inputFluids: List<FluidStack> = inputFluidsInternal

    override val outputItems: List<List<ItemStack>> = outputItemsInternal

    override val outputFluids: List<FluidStack> = outputFluidsInternal

}