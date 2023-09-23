package hiiragi283.material.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import java.util.function.Predicate

sealed class FluidIngredient(val amount: Int = 0) : Predicate<FluidStack?> {

    abstract fun getMatchingStack(): Collection<FluidStack>

    //    Predicate    //

    override fun test(t: FluidStack?): Boolean {
        getMatchingStack().forEach { fluidStack ->
            if (t !== null && t.isFluidEqual(fluidStack) && t.amount >= amount) {
                return true
            }
        }
        return false
    }

    class Fluids(vararg fluidStacks: FluidStack, amount: Int = 0) : FluidIngredient(amount) {

        constructor(vararg fluids: Fluid, amount: Int = 0) : this(
            *fluids.map { FluidStack(it, amount) }.toTypedArray(),
            amount = amount
        )

        private val fluidStacks: List<FluidStack> = fluidStacks.toList()

        override fun getMatchingStack(): Collection<FluidStack> = fluidStacks.map(FluidStack::copy)

    }

    class Materials(vararg materials: HiiragiMaterial, amount: Int = 0) : FluidIngredient(amount) {

        constructor(vararg materialStacks: MaterialStack, amount: Int = 0) : this(
            *materialStacks.map(MaterialStack::material).toTypedArray(),
            amount = amount
        )

        private val materials: List<HiiragiMaterial> = materials.toList()

        override fun getMatchingStack(): Collection<FluidStack> =
            materials.flatMap(HiiragiMaterial::getFluids).map { FluidStack(it, amount) }

    }

}