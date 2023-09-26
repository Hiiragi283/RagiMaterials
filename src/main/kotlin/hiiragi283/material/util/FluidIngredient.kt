package hiiragi283.material.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import java.util.function.Predicate

sealed class FluidIngredient(val amount: Int = 0) : Predicate<FluidStack?> {

    abstract fun getMatchingStack(): Collection<FluidStack>

    open fun onProcess(handler: IFluidHandler) {
        handler.drain(amount, true)
    }

    //    Empty    //

    object EMPTY : FluidIngredient() {

        override fun getMatchingStack(): Collection<FluidStack> = listOf()

        override fun test(t: FluidStack?): Boolean = t == null

    }

    //    FluidStack    //

    class Fluids(vararg fluidStacks: FluidStack, amount: Int = 0) : FluidIngredient(amount) {

        constructor(vararg fluids: Fluid, amount: Int = 0) : this(
            *fluids.map { FluidStack(it, amount) }.toTypedArray(),
            amount = amount
        )

        private val fluidStacks: List<FluidStack> = fluidStacks.toList()

        override fun getMatchingStack(): Collection<FluidStack> = fluidStacks.map(FluidStack::copy)

        override fun test(t: FluidStack?): Boolean =
            getMatchingStack().any { fluidStack: FluidStack -> t !== null && t.isFluidEqual(fluidStack) && t.amount >= amount }

    }

    //    HiiragiMaterial    //

    class Materials(vararg materials: HiiragiMaterial, amount: Int = 0) : FluidIngredient(amount) {

        constructor(vararg materialStacks: MaterialStack, amount: Int = 0) : this(
            *materialStacks.map(MaterialStack::material).toTypedArray(),
            amount = amount
        )

        private val materials: List<HiiragiMaterial> = materials.toList()

        override fun getMatchingStack(): Collection<FluidStack> =
            materials.flatMap(HiiragiMaterial::getFluids).map { FluidStack(it, amount) }

        override fun test(t: FluidStack?): Boolean =
            if (t == null) false else t.fluid.name in materials.map(HiiragiMaterial::name)

    }

    //    Custom    //

    class Custom(
        val stacks: () -> Collection<FluidStack>,
        val predicate: (FluidStack?) -> Boolean,
        val process: (IFluidHandler) -> Unit,
        amount: Int = 0
    ) : FluidIngredient(amount) {

        override fun getMatchingStack(): Collection<FluidStack> = stacks()

        override fun test(t: FluidStack?): Boolean = predicate(t)

        override fun onProcess(handler: IFluidHandler) = process(handler)

    }

}