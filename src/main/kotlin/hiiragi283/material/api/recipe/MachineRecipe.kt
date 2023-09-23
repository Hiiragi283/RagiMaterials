package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.util.FluidIngredient
import hiiragi283.material.util.HiiragiIngredient
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack

class MachineRecipe private constructor() {

    val traits: MutableSet<MachineTrait> = mutableSetOf()
    val inputItems: MutableList<HiiragiIngredient> = mutableListOf()
    val inputFluids: MutableList<FluidIngredient> = mutableListOf()
    val outputItems: MutableList<ItemStack> = mutableListOf()
    val outputFluids: MutableList<FluidStack> = mutableListOf()

    companion object {
        fun buildAndRegister(
            type: IMachineRecipe.Type,
            registryName: ResourceLocation,
            init: MachineRecipe.() -> Unit
        ) {
            val builder = MachineRecipe()
            builder.init()
            HiiragiRegistries.MACHINE_RECIPE.getValue(type)?.register(registryName, object : IMachineRecipe {

                override fun getInputItems(): List<HiiragiIngredient> = builder.inputItems

                override fun getInputFluids(): List<FluidIngredient> = builder.inputFluids

                override fun getRequiredTraits(): Set<MachineTrait> = builder.traits

                override fun getRequiredType(): IMachineRecipe.Type = type

                override fun getOutputItems(): List<ItemStack> = builder.outputItems

                override fun getOutputFluids(): List<FluidStack> = builder.outputFluids

            })
        }

    }

}