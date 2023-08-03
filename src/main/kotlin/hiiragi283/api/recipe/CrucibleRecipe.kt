package hiiragi283.api.recipe

import hiiragi283.api.fluid.DelegatedFluidStack
import hiiragi283.core.util.HiiragiIngredient
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.registries.IForgeRegistryEntry

interface CrucibleRecipe : IForgeRegistryEntry<CrucibleRecipe> {

    fun matches(inventory: IItemHandler, tank: FluidTank): Boolean

    fun getOutput(inventory: IItemHandler, tank: FluidTank): FluidStack

    class Impl private constructor(private val input: HiiragiIngredient, private val output: DelegatedFluidStack) :
        IForgeRegistryEntry.Impl<CrucibleRecipe>(), CrucibleRecipe {

        constructor(vararg stacks: ItemStack, output: DelegatedFluidStack) : this(HiiragiIngredient(*stacks), output)

        constructor(oredict: String, output: DelegatedFluidStack) : this(HiiragiIngredient(oredict), output)

        override fun matches(inventory: IItemHandler, tank: FluidTank): Boolean =
            inventory.getStackInSlot(0) in input.matchingStacks && tank.fluid == null

        override fun getOutput(inventory: IItemHandler, tank: FluidTank): FluidStack = output.get()

    }

}