package hiiragi283.material.api.ingredient

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.HiiragiJsonSerializable
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import java.util.function.Predicate

class FluidIngredient(
    val names: List<String>,
    val amount: Int = 0
) : Predicate<FluidStack?>, HiiragiJsonSerializable {

    constructor(vararg fluids: FluidStack, amount: Int = 0) : this(
        *fluids.map(FluidStack::getFluid).toTypedArray(),
        amount = amount
    )

    constructor(vararg fluids: Fluid, amount: Int = 0) : this(fluids.map(Fluid::getName), amount)

    constructor(vararg materials: HiiragiMaterial, amount: Int = 0) : this(materials.map(HiiragiMaterial::name), amount)

    constructor(vararg names: String, amount: Int = 0) : this(names.toList(), amount)

    fun getMatchingStack(): Collection<FluidStack> = names.mapNotNull { FluidRegistry.getFluidStack(it, amount) }

    fun onProcess(handler: IFluidHandler) {
        handler.drain(amount, true)
    }

    //    Predicate    //

    override fun test(fluidStack: FluidStack?): Boolean =
        if (fluidStack == null) names.isEmpty() else fluidStack.fluid.name in names && fluidStack.amount >= amount

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        val materialArray = JsonArray()
        names.forEach(materialArray::add)
        root.add("fluids", materialArray)

        root.addProperty("amount", amount)

        return root

    }

    companion object {

        @JvmField
        val EMPTY = FluidIngredient(listOf())

    }

}