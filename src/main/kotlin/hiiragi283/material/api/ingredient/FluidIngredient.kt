package hiiragi283.material.api.ingredient

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.util.HiiragiJsonSerializable
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import java.util.function.Predicate

sealed class FluidIngredient(val amount: Int = 0) : Predicate<FluidStack?>, HiiragiJsonSerializable {

    abstract fun getMatchingStack(): Collection<FluidStack>

    open fun onProcess(handler: IFluidHandler) {
        handler.drain(amount, true)
    }

    //    Empty    //

    object EMPTY : FluidIngredient() {

        override fun getMatchingStack(): Collection<FluidStack> = listOf()

        override fun test(t: FluidStack?): Boolean = t == null

        override fun getJsonElement(): JsonElement {
            throw UnsupportedOperationException()
        }

    }

    //    FluidStack    //

    class Fluids(vararg fluids: Fluid, amount: Int = 0) : FluidIngredient(amount) {

        constructor(vararg fluidStacks: FluidStack, amount: Int = 0) : this(
            *fluidStacks.map { it.fluid }.toTypedArray(),
            amount = amount
        )

        private val fluids: List<Fluid> = fluids.toList()

        override fun getMatchingStack(): Collection<FluidStack> = fluids.map { FluidStack(it, amount) }

        override fun test(t: FluidStack?): Boolean =
            getMatchingStack().any { fluidStack: FluidStack -> t !== null && t.isFluidEqual(fluidStack) && t.amount >= amount }

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            val fluidArray = JsonArray()
            fluids.map(Fluid::getName).forEach(fluidArray::add)
            root.add("fluids", fluidArray)

            root.addProperty("amount", amount)

            return root

        }

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

        override fun getJsonElement(): JsonElement {

            val root = JsonObject()

            val materialArray = JsonArray()
            materials.map(HiiragiMaterial::name).forEach(materialArray::add)
            root.add("materials", materialArray)

            root.addProperty("amount", amount)

            return root

        }

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

        override fun getJsonElement(): JsonElement {
            throw UnsupportedOperationException()
        }

    }

}