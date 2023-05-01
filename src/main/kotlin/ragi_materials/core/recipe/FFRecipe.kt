package ragi_materials.core.recipe

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.registries.IForgeRegistryEntry
import ragi_materials.core.item.ItemMaterial
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.util.wrapper.ItemStackWrapper
import kotlin.math.pow

@Optional.Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
data class FFRecipe private constructor(private val input: ItemStack, private val output: ItemStack, private val fuel: Int)
    : IForgeRegistryEntry.Impl<FFRecipe>(), IRecipeWrapper {

    constructor(recipe: FFRecipe) : this(recipe.getInput(), recipe.getOutput(), recipe.getFuel())

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun getFuel() = fuel

    fun match(input: ItemStack, fuel: Int) = ItemStackWrapper(this.input, 1) == ItemStackWrapper(input, 1) && fuel >= this.fuel

    //    IRecipeWrapper    //

    override fun getIngredients(ing: IIngredients) {
        ing.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ing.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutput()))
    }

    override fun drawInfo(mc: Minecraft, wid: Int, hei: Int, mouseX: Int, mouseY: Int) {
        mc.fontRenderer.drawString("§7Fuel: ${getFuel()}", 60.0f, 4.5f, 0x000000, false)
    }

    class Builder {

        private fun getFuelConsumption(stack: ItemStack): Int {
            val item = stack.item
            return if (item is IMaterialItem && item is ItemMaterial) {
                val material = item.getMaterial(stack)
                val tempMelt = material.getTempMelt()
                val scale = item.part.scale
                return tempMelt?.run { (2.0.pow(this / 1000) * scale).toInt() * 200 } ?: 0
            } else 0
        }

        var input: ItemStack = ItemStack.EMPTY
        var output: ItemStack = ItemStack.EMPTY
        var fuel = getFuelConsumption(input)

        fun setInput(stack: ItemStack) = also { this.input = stack }

        fun setOutput(stack: ItemStack) = also { this.output = stack }

        fun build() = FFRecipe(input, output, fuel)

    }
}