package hiiragi283.material.recipe

import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartDictionary
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandlerModifiable

class MaterialMeltingRecipe(val material: HiiragiMaterial) : IMachineRecipe {

    override fun getInputItems(): List<ItemIngredient> = listOf(ItemIngredient.Materials(material))

    override fun getInputFluids(): List<FluidIngredient> = listOf()

    override fun getRequiredTraits(): Set<MachineTrait> = setOf(MachineTrait.MELT)

    override fun getRequiredType(): MachineType = MachineType.SMELTER

    override fun getOutputItems(): List<ItemStack> = listOf()

    override fun getOutputFluids(): List<FluidStack> = listOfNotNull(material.getFluidStack(0))

    override fun getOutputFluids(
        inventory: IItemHandlerModifiable,
        tank0: IFluidHandler,
        tank1: IFluidHandler,
        tank2: IFluidHandler
    ): List<FluidStack> {
        val amount: Int = PartDictionary.getPart(inventory.getStackInSlot(0))?.getScale() ?: return listOf()
        return listOfNotNull(material.getFluidStack(amount))
    }

}