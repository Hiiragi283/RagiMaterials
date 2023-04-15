package hiiragi283.ragi_materials.api.recipe

import hiiragi283.ragi_materials.util.same
import hiiragi283.ragi_materials.util.sameExact
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Optional.Interface
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.registries.IForgeRegistryEntry

@Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
data class LaboRecipe private constructor(private var inputs: MutableList<ItemStack>, private var outputs: MutableList<ItemStack>, private var catalyst: ItemStack)
    : IForgeRegistryEntry.Impl<LaboRecipe>(), IRecipeWrapper {

    constructor(recipe: LaboRecipe) : this(recipe.getInputs(), recipe.getOutputs(), recipe.getCatalyst())

    fun getInput(slot: Int): ItemStack = inputs[slot].copy()

    fun getInputs() = inputs.toMutableList()

    fun getOutput(slot: Int): ItemStack = outputs[slot].copy()

    fun getOutputs() = outputs.toMutableList()

    fun getCatalyst(): ItemStack = catalyst.copy()

    fun match(inventory: IItemHandler): Boolean {
        var result = true
        for (i in 0..4) {
            val input = this.inputs[i]
            val stackSlot = inventory.getStackInSlot(i)
            result = result && input.same(stackSlot) && stackSlot.count >= input.count
        }
        return result
    }

    //stackの個数まで一致するか判断するメソッド
    fun matchExact(inventory: IItemHandler): Boolean {
        var result = true
        for (i in 0..4) {
            result = result && this.inputs[i].sameExact(inventory.getStackInSlot(i))
        }
        return result
    }

    fun setInput(slot: Int, stack: ItemStack) = also { inputs[slot] = stack }

    fun setInputs(inputs: MutableList<ItemStack>) = also { this.inputs = inputs }

    fun setOutput(slot: Int, stack: ItemStack) = also { outputs[slot] = stack }

    fun setOutputs(outputs: MutableList<ItemStack>) = also { this.outputs = outputs }

    fun setCatalyst(stack: ItemStack) = also { catalyst = stack }

    //    IRecipeWrapper    //

    override fun getIngredients(ings: IIngredients) {
        val inputs = getInputs().toMutableList().also { it.add(getCatalyst()) }
        ings.setInputLists(VanillaTypes.ITEM, mutableListOf(inputs))
        ings.setOutputLists(VanillaTypes.ITEM, mutableListOf(getOutputs()))
    }

    class Builder {

        var inputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        var outputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        var catalyst: ItemStack = ItemStack.EMPTY

        fun build() = LaboRecipe(inputs, outputs, catalyst)
    }
}