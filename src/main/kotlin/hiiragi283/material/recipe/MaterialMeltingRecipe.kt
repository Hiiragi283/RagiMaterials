package hiiragi283.material.recipe

import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.getParts
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.util.FluidIngredient
import hiiragi283.material.util.HiiragiIngredient
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandlerModifiable

class MaterialMeltingRecipe(val material: HiiragiMaterial) : IMachineRecipe {

    override fun getInputItems(): List<HiiragiIngredient> = listOf(HiiragiIngredient.Materials(material))

    override fun getInputFluids(): List<FluidIngredient> = listOf()

    override fun getRequiredTraits(): Set<MachineTrait> = setOf()

    override fun getRequiredType(): IMachineRecipe.Type = IMachineRecipe.Type.MELTER

    override fun getOutputItems(): List<ItemStack> = listOf()

    override fun getOutputFluids(): List<FluidStack> = listOfNotNull(material.getFluidStack(0))

    override fun getOutputFluids(
        inventory: IItemHandlerModifiable,
        tank0: IFluidHandler,
        tank1: IFluidHandler,
        tank2: IFluidHandler
    ): List<FluidStack> {
        val amount: Int = inventory.getStackInSlot(0).getParts().getOrNull(0)?.shape?.scale ?: return listOf()
        return listOfNotNull(material.getFluidStack(amount))
    }

}