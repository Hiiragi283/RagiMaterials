package hiiragi283.material.recipe

import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import java.util.function.Supplier

class MachineRecipe private constructor() {

    val traits: MutableSet<MachineTrait> = mutableSetOf()
    val inputItems: MutableList<ItemIngredient> = mutableListOf()
    val inputFluids: MutableList<FluidIngredient> = mutableListOf()
    val outputItems: MutableList<Supplier<ItemStack>> = mutableListOf()
    val outputFluids: MutableList<Supplier<FluidStack>> = mutableListOf()

    companion object {

        fun build(
            type: MachineType,
            init: MachineRecipe.() -> Unit
        ): IMachineRecipe {
            val builder = MachineRecipe()
            builder.init()
            return object : IMachineRecipe {

                override fun getInputItems(): List<ItemIngredient> = builder.inputItems

                override fun getInputFluids(): List<FluidIngredient> = builder.inputFluids

                override fun getRequiredTraits(): Set<MachineTrait> = builder.traits

                override fun getRequiredType(): MachineType = type

                override fun getOutputItems(): List<ItemStack> = builder.outputItems.map(Supplier<ItemStack>::get)

                override fun getOutputFluids(): List<FluidStack> = builder.outputFluids.map(Supplier<FluidStack>::get)

            }
        }

        fun buildAndRegister(
            type: MachineType,
            registryName: ResourceLocation,
            init: MachineRecipe.() -> Unit
        ) {
            IMachineRecipe.register(registryName, build(type, init))
        }

    }

}