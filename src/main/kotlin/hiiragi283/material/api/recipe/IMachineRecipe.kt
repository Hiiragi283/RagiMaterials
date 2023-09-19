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

    //    Inputs    //

    val inputItems: List<List<ItemStack>>

    val inputFluids: List<FluidStack>

    //    Outputs    //

    val outputItems: List<ItemStack>

    val outputFluids: List<FluidStack>

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
        for (index: Int in inputItems.indices) {
            //1つでも一致したら次のItemStackで検証
            if (inputItems[index].any { stack: ItemStack ->
                    tile.inventoryInput.extractItem(index, stack.count, true).isSameWithNBT(stack)
                }) {
                continue
            }
            return false
        }
        //完成品を搬入して一つでも余りが出ないか
        for (index: Int in outputItems.indices) {
            if (!tile.inventoryOutput.insertItem(index, outputItems[index], true).isEmpty) {
                return false
            }
        }
        //inputFluidsに含まれる液体が搬出できるかどうか
        for (index: Int in inputFluids.indices) {
            val fluidStack: FluidStack = inputFluids[index].copy()
            if (tile.getTank(index).drain(fluidStack.amount, false)?.amount != fluidStack.amount) {
                return false
            }
        }
        //outputFluidsに含まれる液体が搬入できるかどうか
        for (index: Int in outputFluids.indices) {
            val fluidStack: FluidStack = outputFluids[index].copy()
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

    enum class Type {
        BENDING,
        CANNING,
        CENTRIFUGE,
        COMPRESSOR,
        CRUSHER,
        CUTTING,
        DISTILLER,
        ELECTROLYZER,
        EXTRACTOR,
        FORMER,
        FREEZER,
        HEATER,
        INFUSER,
        MELTER,
        PULVERIZER,
        ROCK_GENERATOR,
        SMELTER,
        WIREMILL,
        TEST,
        NONE;

        val translationKey: String = "hiiragi_machine.${this.name.lowercase()}"

        fun getTranslatedName(material: HiiragiMaterial): String =
            I18n.format(translationKey, material.getTranslatedName())

    }

}