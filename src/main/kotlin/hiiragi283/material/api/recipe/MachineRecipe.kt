package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.ItemStackComparable
import hiiragi283.material.util.findItemStack
import hiiragi283.material.util.toComparable
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

    //    IMachineRecipe    //

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

        fun addInput(vararg items: Item, count: Int = 1) = also {
            this.inputItems.add(items.map { item: Item -> ItemStack(item, count, 0) })
        }

        fun addInput(vararg blocks: Block, count: Int = 1) = also {
            this.inputItems.add(blocks.map { block: Block -> ItemStack(block, count, 0) })
        }

        fun addInput(vararg oreDict: String, count: Int = 1) = also {
            this.inputItems.add(oreDict.flatMap(OreDictionary::getOres)
                .map { stack: ItemStack -> stack.toComparable(count = 1) }
                .toSet()
                .map { stackComparable: ItemStackComparable -> stackComparable.toItemStack(count) })
        }

        fun addInput(part: HiiragiPart, count: Int = 1) = also {
            addInputs(part.getOreDicts(), count)
        }

        fun addInput(shape: HiiragiShape, material: HiiragiMaterial, count: Int = 1) = also {
            addInput(HiiragiPart(shape, material), count)
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> addInputs(collection: Collection<T>, count: Int = 1) = also {
            (collection as? Collection<String>)?.let { addInput(*it.toTypedArray(), count = count) }
                ?: (collection as? Collection<Block>)?.let { addInput(*it.toTypedArray(), count = count) }
                ?: (collection as? Collection<Item>)?.let { addInput(*it.toTypedArray(), count = count) }
                ?: (collection as? Collection<ItemStack>)?.let { addInput(*it.toTypedArray()) }
        }

        //    Input - Fluid    //

        fun addInputFluid(fluidStack: FluidStack) = also {
            this.inputFluids.add(fluidStack)
        }

        fun addInputFluid(fluid: Fluid, amount: Int) = also {
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

        fun addOutput(item: Item, count: Int = 1) = also {
            addOutput(ItemStack(item, count, 0))
        }

        fun addOutput(block: Block, count: Int = 1) = also {
            addOutput(ItemStack(block, count, 0))
        }

        fun addOutput(oreDict: String, count: Int = 1) = also {
            addOutput(findItemStack(oreDict).let { stack ->
                stack.count = count
                return@let stack
            })
        }

        fun addOutput(part: HiiragiPart, count: Int = 1) = also {
            addOutput(part.findItemStack().let { stack ->
                stack.count = count
                return@let stack
            })
        }

        fun addOutput(shape: HiiragiShape, material: HiiragiMaterial, count: Int = 1) = also {
            addOutput(HiiragiPart(shape, material), count)
        }

        //    Output - Fluid    //

        fun addOutputFluid(fluidStack: FluidStack) = also {
            this.outputFluids.add(fluidStack)
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