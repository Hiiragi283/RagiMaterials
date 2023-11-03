package hiiragi283.material.compat.crt.recipe

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import crafttweaker.api.liquid.ILiquidStack
import crafttweaker.api.minecraft.CraftTweakerMC
import crafttweaker.api.oredict.IOreDictEntry
import hiiragi283.material.RMReference
import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.compat.crt.HiiragiAction
import hiiragi283.material.compat.crt.toFluid
import hiiragi283.material.compat.crt.toItem
import hiiragi283.material.recipe.MachineRecipe
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenConstructor
import stanhebben.zenscript.annotations.ZenMethod
import java.util.function.Supplier

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.recipe.MachineRecipe")
@ZenRegister
class MachineRecipeBuilder @ZenConstructor constructor() {

    private val traits: MutableSet<MachineTrait> = mutableSetOf()
    private val inputItems: MutableList<ItemIngredient> = mutableListOf()
    private val inputFluids: MutableList<FluidIngredient> = mutableListOf()
    private val outputItems: MutableList<Supplier<ItemStack>> = mutableListOf()
    private val outputFluids: MutableList<Supplier<FluidStack>> = mutableListOf()

    //    MachineTrait    //

    @ZenMethod
    fun addTrait(vararg trait: String) {
        traits.addAll(trait.map(MachineTrait::valueOf))
    }

    //    Item Input    //

    @ZenMethod
    fun addInputStacks(vararg stacks: IItemStack, count: Int) {
        inputItems.add(ItemIngredient.Stacks(*CraftTweakerMC.getItemStacks(*stacks), count = count))
    }

    @ZenMethod
    fun addInputItems(vararg items: IItemStack, count: Int) {
        inputItems.add(ItemIngredient.Items(*items.map(IItemStack::toItem).toTypedArray(), count = count))
    }

    @ZenMethod
    fun addInputOreDict(vararg oreDicts: IOreDictEntry, count: Int) {
        inputItems.add(ItemIngredient.OreDicts(*oreDicts.map(IOreDictEntry::getName).toTypedArray(), count = count))
    }

    //    Fluid Input    //

    @ZenMethod
    fun addInputFluids(vararg stacks: ILiquidStack, amount: Int) {
        inputFluids.add(FluidIngredient(*stacks.map(ILiquidStack::toFluid).toTypedArray(), amount = amount))
    }

    //    Item Output    //

    @ZenMethod
    fun addOutputItem(vararg stacks: IItemStack) {
        CraftTweakerMC.getItemStacks(*stacks).forEach { stack: ItemStack ->
            outputItems.add { stack }
        }
    }

    //    Fluid Output    //

    fun addOutputFluid(vararg stacks: ILiquidStack) {
        CraftTweakerMC.getLiquidStacks(stacks).forEach { stack: FluidStack ->
            outputFluids.add { stack }
        }
    }

    //    Register    //

    @ZenMethod
    fun buildAndRegister(type: String, registryName: String) {
        CraftTweakerAPI.apply(HiiragiAction("") {
            MachineRecipe.buildAndRegister(
                MachineType.valueOf(type),
                ResourceLocation(registryName)
            ) {
                this.traits.addAll(this@MachineRecipeBuilder.traits)
                this.inputItems.addAll(this@MachineRecipeBuilder.inputItems)
                this.inputFluids.addAll(this@MachineRecipeBuilder.inputFluids)
                this.outputItems.addAll(this@MachineRecipeBuilder.outputItems)
                this.outputFluids.addAll(this@MachineRecipeBuilder.outputFluids)
            }
        })
    }

}