package hiiragi283.material.api.machine

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.getJsonElement
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandlerModifiable

interface IMachineRecipe : HiiragiJsonSerializable {

    //    Input    //

    fun getInputItems(): List<ItemIngredient>

    fun getInputFluids(): List<FluidIngredient>

    //    Check    //

    fun canFit(itemSlots: Int, fluidSlots: Int): Boolean =
        itemSlots >= getInputItems().size && fluidSlots >= getInputFluids().size

    fun getRequiredTraits(): Set<MachineTrait>

    fun traitsString(): String = getRequiredTraits().toString().let { it.substring(1, it.length - 1) }

    fun getRequiredType(): MachineType

    //    Output    //

    fun getOutputItems(): List<ItemStack>

    fun getOutputFluids(): List<FluidStack>

    //    Input-Sensitive    //

    fun getOutputItems(
        inventory: IItemHandlerModifiable,
        tank0: IFluidHandler,
        tank1: IFluidHandler,
        tank2: IFluidHandler
    ): List<ItemStack> = getOutputItems()

    fun getOutputFluids(
        inventory: IItemHandlerModifiable,
        tank0: IFluidHandler,
        tank1: IFluidHandler,
        tank2: IFluidHandler
    ): List<FluidStack> = getOutputFluids()

    private fun getOutputItems(tile: TileEntityModuleMachine) = getOutputItems(
        tile.inventoryInput,
        tile.getTank(0),
        tile.getTank(1),
        tile.getTank(2)
    )

    private fun getOutputFluids(tile: TileEntityModuleMachine) = getOutputFluids(
        tile.inventoryInput,
        tile.getTank(0),
        tile.getTank(1),
        tile.getTank(2)
    )

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        root.addProperty("type", getRequiredType().name)

        val traitArray = JsonArray()
        getRequiredTraits().map(MachineTrait::name).forEach(traitArray::add)
        root.add("traits", traitArray)

        val inputItemArray = JsonArray()
        getInputItems().map(ItemIngredient::getJsonElement).forEach(inputItemArray::add)
        root.add("input_items", inputItemArray)

        val inputFluidArray = JsonArray()
        getInputFluids().map(FluidIngredient::getJsonElement).forEach(inputFluidArray::add)
        root.add("input_fluids", inputFluidArray)

        val outputItemArray = JsonArray()
        getOutputItems().map(ItemStack::getJsonElement).forEach(outputItemArray::add)
        root.add("output_items", outputItemArray)

        val outputFluidArray = JsonArray()
        getOutputFluids().map(FluidStack::getJsonElement).forEach(outputFluidArray::add)
        root.add("output_fluids", outputFluidArray)

        return root

    }

    companion object {

        fun matches(recipe: IMachineRecipe, tile: TileEntityModuleMachine): Boolean {
            //スロット数の確認
            if (!recipe.canFit(tile.machineProperty.itemSlots, tile.machineProperty.fluidSlots)) return false
            //Input - Item
            (0..5).forEach { index: Int ->
                val ingredient: ItemIngredient = recipe.getInputItems().getOrElse(index) { ItemIngredient.EMPTY }
                val stack: ItemStack = tile.inventoryInput.getStackInSlot(index)
                if (!ingredient.test(stack)) return false
            }
            //Input - Fluid
            (0..2).forEach { index: Int ->
                val ingredient: FluidIngredient = recipe.getInputFluids().getOrElse(index) { FluidIngredient.EMPTY }
                val stack: FluidStack? = tile.getTank(index).fluid
                if (!ingredient.test(stack)) return false
            }
            //Output - Item
            recipe.getOutputItems(tile).forEachIndexed { index: Int, stack: ItemStack ->
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
            //energyStorageからエネルギーを消費できるかどうか
            val energyRequired: Int = tile.machineProperty.getEnergyRequired()
            if (tile.energyStorage.extractEnergy(energyRequired, true) != energyRequired) return false
            return tile.machineProperty.machineTraits.containsAll(recipe.getRequiredTraits())
        }

        fun process(recipe: IMachineRecipe, tile: TileEntityModuleMachine) {
            //Output
            recipe.getOutputItems(tile).forEachIndexed { index: Int, stack: ItemStack ->
                tile.inventoryOutput.insertItem(index, stack.copy(), false)
            }
            recipe.getOutputFluids(tile).forEachIndexed { index: Int, fluidStack: FluidStack ->
                tile.getTank(index + 3).fill(fluidStack.copy(), true)
            }
            //Input
            recipe.getInputItems().forEachIndexed { index: Int, ingredient: ItemIngredient ->
                ingredient.onProcess(tile.inventoryInput, index)
            }
            recipe.getInputFluids().forEachIndexed { index: Int, ingredient: FluidIngredient ->
                ingredient.onProcess(tile.getTank(index))
            }
            //Energy
            tile.energyStorage.extractEnergy(tile.machineProperty.getEnergyRequired(), false)
        }

        fun register(registryName: ResourceLocation, recipe: IMachineRecipe) {
            HiiragiRegistries.MACHINE_RECIPE.getValue(recipe.getRequiredType())?.register(registryName, recipe)
        }

    }

}