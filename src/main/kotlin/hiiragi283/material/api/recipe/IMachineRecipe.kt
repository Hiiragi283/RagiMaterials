package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.util.isSameWithNBT
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

interface IMachineRecipe {

    val type: Type

    val requiredTraits: Set<ModuleTrait>

    //    Process    //

    fun validate() {
        if (inputItems.size > 6)
            throw IndexOutOfBoundsException("Size: ${inputItems.size} is over 6!")
        if (inputFluids.size > 3)
            throw IndexOutOfBoundsException("Size: ${inputFluids.size} is over 3!")
        if (outputItems.size > 6)
            throw IndexOutOfBoundsException("Size: ${outputItems.size} is over 6!")
        if (outputFluids.size > 3)
            throw IndexOutOfBoundsException("Size: ${outputFluids.size} is over 3!")
    }

    fun matches(tile: TileEntityModuleMachine): Boolean {
        validate()
        //素材をすべて搬出できるか
        inputItems.forEachIndexed { index: Int, list: List<ItemStack> ->
            for (stack: ItemStack in list) {
                if (tile.inventoryInput.extractItem(index, stack.count, true).isSameWithNBT(stack)) {
                    continue
                }
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

    fun process(tile: TileEntityModuleMachine) {
        //ItemStack
        inputItems.forEachIndexed { index: Int, list: List<ItemStack> ->
            list.forEach { stack: ItemStack -> tile.inventoryInput.extractItem(index, stack.count, false) }
        }
        outputItems.forEachIndexed { index: Int, stack: ItemStack ->
            tile.inventoryOutput.insertItem(index, stack.copy(), false)
        }
        //FluidStack
        inputFluids.forEachIndexed { index, fluidStack ->
            tile.getTank(index).drain(fluidStack.copy(), true)
        }
        outputFluids.forEachIndexed { index, fluidStack ->
            tile.getTank(index + 3).fill(fluidStack.copy(), true)
        }
        //Energy
        tile.energyStorage.extractEnergy(tile.machineProperty.getEnergyRequired(), false)
    }

    //    Inputs    //

    val inputItems: List<List<ItemStack>>

    val inputFluids: List<FluidStack>

    //    Outputs    //

    val outputItems: List<ItemStack>

    val outputFluids: List<FluidStack>

    enum class Type {
        BEND,
        CRUSH,
        CUT,
        PULVERISE,
        SMELT,
        TEST,
        WIRE,
        NONE;

        private val translationKey: String = "hiiragi_machine.${this.name.lowercase()}"

        fun getTranslatedName(material: HiiragiMaterial): String =
            I18n.format(translationKey, material.getTranslatedName())

    }

}