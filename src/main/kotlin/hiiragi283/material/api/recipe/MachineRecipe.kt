package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.util.findItemStack
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

class MachineRecipe(
    override val type: IMachineRecipe.Type,
    override val requiredTraits: Set<ModuleTrait>,
    override val inputItems: List<List<ItemStack>>,
    override val inputFluids: List<FluidStack>,
    override val outputItems: List<ItemStack>,
    override val outputFluids: List<FluidStack>,
) : IMachineRecipe {

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

    override fun matches(tile: TileEntityModuleMachine): Boolean {
        validate()
        //素材をすべて搬出できるか
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

    class Builder(val type: IMachineRecipe.Type, val registryName: ResourceLocation) {

        private val traits: MutableSet<ModuleTrait> = mutableSetOf()
        private val inputItems: MutableList<List<ItemStack>> = mutableListOf()
        private val inputFluids: MutableList<FluidStack> = mutableListOf()
        private val outputItems: MutableList<ItemStack> = mutableListOf()
        private val outputFluids: MutableList<FluidStack> = mutableListOf()

        fun buildAndRegister() {
            val recipe = MachineRecipe(type, traits, inputItems, inputFluids, outputItems, outputFluids)
            recipe.validate()
            HiiragiRegistries.RECIPE_TYPE.getValue(type)?.register(registryName, recipe)
        }

        //    Traits    //

        fun addTrait(vararg traits: ModuleTrait) = also {
            this.traits.addAll(traits)
        }

        fun addTraits(traits: Collection<ModuleTrait>) = also {
            this.traits.addAll(traits)
        }

        //    Input - Item    //

        fun addInput(vararg stacks: ItemStack) = also {
            this.inputItems.add(listOf(*stacks))
        }

        fun addInput(stacks: Collection<ItemStack>) = also {
            this.inputItems.add(stacks.toList())
        }

        fun addInputItem(vararg items: Item) = also {
            addInput(items.map(::ItemStack))
        }

        fun addInputItem(items: Collection<Item>) = also {
            addInput(items.map(::ItemStack))
        }

        fun addInputBlock(vararg blocks: Block) = also {
            addInput(blocks.map(::ItemStack))
        }

        fun addInputBlock(blocks: Collection<Block>) = also {
            addInput(blocks.map(::ItemStack))
        }

        fun addInputOreDict(vararg oreDict: String) = also {
            addInput(oreDict.flatMap(OreDictionary::getOres))
        }

        fun addInputOreDict(oreDicts: Collection<String>) = also {
            addInput(oreDicts.flatMap(OreDictionary::getOres))
        }

        fun addInputPart(part: HiiragiPart) = also {
            addInputOreDict(part.getOreDicts())
        }

        fun addInputPart(shape: HiiragiShape, material: HiiragiMaterial) = also {
            addInputPart(HiiragiPart(shape, material))
        }

        //    Input - Fluid    //

        fun addInputFluid(fluidStack: FluidStack) = also {
            this.inputFluids.add(fluidStack)
        }

        fun addInput(fluid: Fluid, amount: Int) = also {
            addInputFluid(FluidStack(fluid, amount))
        }

        fun addInputFluid(name: String, amount: Int) = also {
            FluidRegistry.getFluidStack(name, amount)?.let { fluidStack: FluidStack -> addInputFluid(fluidStack) }
        }

        fun addInputFluid(material: HiiragiMaterial, amount: Int) = also {
            addInputFluid(material.name, amount)
        }

        fun addInputFluid(materialStack: MaterialStack) = also {
            addInputFluid(materialStack.material, materialStack.amount)
        }

        //    Output - Item    //

        fun addOutput(stack: ItemStack) = also {
            this.outputItems.add(stack)
        }

        fun addOutputItem(item: Item) = also {
            addOutput(ItemStack(item))
        }

        fun addOutputBlock(block: Block) = also {
            addOutput(ItemStack(block))
        }

        fun addOutputOreDict(oreDict: String) = also {
            addOutput(findItemStack(oreDict))
        }

        fun addOutputPart(part: HiiragiPart) = also {
            addOutput(part.findItemStack())
        }

        fun addOutput(shape: HiiragiShape, material: HiiragiMaterial) = also {
            addOutputPart(HiiragiPart(shape, material))
        }

        //    Output - Fluid    //

        fun addOutputFluid(fluidStack: FluidStack) = also {
            this.inputFluids.add(fluidStack)
        }

        fun addOutputFluid(fluid: Fluid, amount: Int) = also {
            addOutputFluid(FluidStack(fluid, amount))
        }

        fun addOutputFluid(name: String, amount: Int) = also {
            FluidRegistry.getFluidStack(name, amount)?.let { fluidStack: FluidStack -> addOutputFluid(fluidStack) }
        }

        fun addOutputFluid(material: HiiragiMaterial, amount: Int) = also {
            addOutputFluid(material.name, amount)
        }

    }

}