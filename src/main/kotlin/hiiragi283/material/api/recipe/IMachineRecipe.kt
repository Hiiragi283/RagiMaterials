package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.FluidIngredient
import hiiragi283.material.util.HiiragiIngredient
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandlerModifiable

interface IMachineRecipe {

    //    Input    //

    fun getInputItems(): List<HiiragiIngredient>

    fun getInputFluids(): List<FluidIngredient>

    //    Check    //

    fun canFit(itemSlots: Int, fluidSlots: Int): Boolean =
        itemSlots >= getInputItems().size && fluidSlots >= getInputFluids().size

    /*fun matches(tile: TileEntityModuleMachine): Boolean {
        //スロット数の確認
        if (!canFit(tile.machineProperty.itemSlots, tile.machineProperty.fluidSlots)) return false
        //Input - Item
        getInputItems().forEachIndexed { index: Int, ingredient: HiiragiIngredient ->
            if (!ingredient.test(tile.inventoryInput.getStackInSlot(index))) {
                return false
            }
        }
        //Input - Fluid
        getInputFluids().forEachIndexed { index: Int, ingredient: FluidIngredient ->
            if (!ingredient.test(tile.getTank(index).fluid)) {
                return false
            }
        }
        //Output - Item
        getOutputItems(tile.inventoryInput).forEachIndexed { index: Int, stack: ItemStack ->
            if (!tile.inventoryOutput.insertItem(index, stack, true).isEmpty) {
                return false
            }
        }
        //Output - Fluid
        getOutputFluids(tile).forEachIndexed { index: Int, fluidStack: FluidStack ->
            if (tile.getTank(index + 3).fill(fluidStack, false) != fluidStack.amount) {
                return false
            }
        }
        //PRIMITIVEの特性がある場合は電力チェックを飛ばせる
        if (MachineTrait.PRIMITIVE !in tile.machineProperty.machineTraits) {
            //energyStorageからエネルギーを消費できるかどうか
            val energyRequired: Int = tile.machineProperty.getEnergyRequired()
            if (tile.energyStorage.extractEnergy(energyRequired, true) != energyRequired) return false
        }
        return tile.machineProperty.machineTraits.containsAll(getRequiredTraits())
    }

    fun process(tile: TileEntityModuleMachine) {
        //Stacks
        getOutputItems(tile.inventoryInput).forEachIndexed { index: Int, stack: ItemStack ->
            tile.inventoryOutput.insertItem(index, stack.copy(), false)
        }
        getInputItems().forEachIndexed { index: Int, ingredient: HiiragiIngredient ->
            ingredient.onProcess(tile.inventoryInput, index)
            //tile.inventoryInput.extractItem(index, ingredient.count, false)
        }
        //FluidStack
        getOutputFluids(tile).forEachIndexed { index: Int, fluidStack: FluidStack ->
            tile.getTank(index + 3).fill(fluidStack.copy(), true)
        }
        getInputFluids().forEachIndexed { index: Int, ingredient: FluidIngredient ->
            ingredient.onProcess(tile.getTank(index))
            //tile.getTank(index).drain(ingredient.amount, true)
        }
        //Energy
        if (MachineTrait.PRIMITIVE !in tile.machineProperty.machineTraits) {
            tile.energyStorage.extractEnergy(tile.machineProperty.getEnergyRequired(), false)
        }
    }*/

    fun getRequiredTraits(): Set<MachineTrait>

    fun getRequiredType(): Type

    //    Output    //

    fun getOutputItems(): List<ItemStack>

    fun getOutputFluids(): List<FluidStack>

    //    Input-Sensitive    //

    fun getOutputItems(input: IItemHandlerModifiable): List<ItemStack> = getOutputItems()

    fun getOutputFluids(
        input0: IFluidHandler,
        input1: IFluidHandler,
        input2: IFluidHandler
    ): List<FluidStack> = getOutputFluids()

    private fun getOutputFluids(tile: TileEntityModuleMachine) = getOutputFluids(
        tile.getTank(0),
        tile.getTank(1),
        tile.getTank(2)
    )

    companion object {

        fun matches(recipe: IMachineRecipe, tile: TileEntityModuleMachine): Boolean {
            //スロット数の確認
            if (!recipe.canFit(tile.machineProperty.itemSlots, tile.machineProperty.fluidSlots)) return false
            //Input - Item
            recipe.getInputItems().forEachIndexed { index: Int, ingredient: HiiragiIngredient ->
                if (!ingredient.test(tile.inventoryInput.getStackInSlot(index))) {
                    return false
                }
            }
            //Input - Fluid
            recipe.getInputFluids().forEachIndexed { index: Int, ingredient: FluidIngredient ->
                if (!ingredient.test(tile.getTank(index).fluid)) {
                    return false
                }
            }
            //Output - Item
            recipe.getOutputItems(tile.inventoryInput).forEachIndexed { index: Int, stack: ItemStack ->
                if (!tile.inventoryOutput.insertItem(index, stack, true).isEmpty) {
                    return false
                }
            }
            //Output - Fluid
            recipe.getOutputFluids(tile).forEachIndexed { index: Int, fluidStack: FluidStack ->
                if (tile.getTank(index + 3).fill(fluidStack, false) != fluidStack.amount) {
                    return false
                }
            }
            //PRIMITIVEの特性がある場合は電力チェックを飛ばせる
            if (MachineTrait.PRIMITIVE !in tile.machineProperty.machineTraits) {
                //energyStorageからエネルギーを消費できるかどうか
                val energyRequired: Int = tile.machineProperty.getEnergyRequired()
                if (tile.energyStorage.extractEnergy(energyRequired, true) != energyRequired) return false
            }
            return tile.machineProperty.machineTraits.containsAll(recipe.getRequiredTraits())
        }

        fun process(recipe: IMachineRecipe, tile: TileEntityModuleMachine) {
            //Stacks
            recipe.getOutputItems(tile.inventoryInput).forEachIndexed { index: Int, stack: ItemStack ->
                tile.inventoryOutput.insertItem(index, stack.copy(), false)
            }
            recipe.getInputItems().forEachIndexed { index: Int, ingredient: HiiragiIngredient ->
                ingredient.onProcess(tile.inventoryInput, index)
                //tile.inventoryInput.extractItem(index, ingredient.count, false)
            }
            //FluidStack
            recipe.getOutputFluids(tile).forEachIndexed { index: Int, fluidStack: FluidStack ->
                tile.getTank(index + 3).fill(fluidStack.copy(), true)
            }
            recipe.getInputFluids().forEachIndexed { index: Int, ingredient: FluidIngredient ->
                ingredient.onProcess(tile.getTank(index))
                //tile.getTank(index).drain(ingredient.amount, true)
            }
            //Energy
            if (MachineTrait.PRIMITIVE !in tile.machineProperty.machineTraits) {
                tile.energyStorage.extractEnergy(tile.machineProperty.getEnergyRequired(), false)
            }
        }

        fun register(registryName: ResourceLocation, recipe: IMachineRecipe) {
            HiiragiRegistries.MACHINE_RECIPE.getValue(recipe.getRequiredType())?.register(registryName, recipe)
        }

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

        val translationKey: String = "hiiragi_machine.${this.lowercase()}"

        fun lowercase() = name.lowercase()

        fun getTranslatedName(material: HiiragiMaterial): String =
            I18n.format(translationKey, material.getTranslatedName())

    }

}