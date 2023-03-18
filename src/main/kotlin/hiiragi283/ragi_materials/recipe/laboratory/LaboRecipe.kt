package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.items.IItemHandler

class LaboRecipe private constructor(location: ResourceLocation, val inputs: MutableList<ItemStack>, val outputs: MutableList<ItemStack>) {

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(Reference.MOD_ID, path))

        var inputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        var outputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)

        fun setCatalyst(slot: Int, stack: ItemStack): Builder = also {
            it.inputs[slot] = stack
            it.outputs[slot] = stack
        }

        fun build(): LaboRecipe {
            val recipe = LaboRecipe(location, inputs, outputs)
            LaboRecipeRegistry.map[location.toString()] = recipe
            return recipe
        }
    }

    fun match(inventory: IItemHandler, useCount: Boolean): Boolean {
        var result = false
        if (inventory.slots >= 5) {
            val matchSlot0 = RagiUtil.isSameStack(this.inputs[0], inventory.getStackInSlot(0), useCount)
            val matchSlot1 = RagiUtil.isSameStack(this.inputs[1], inventory.getStackInSlot(1), useCount)
            val matchSlot2 = RagiUtil.isSameStack(this.inputs[2], inventory.getStackInSlot(2), useCount)
            val matchSlot3 = RagiUtil.isSameStack(this.inputs[3], inventory.getStackInSlot(3), useCount)
            val matchSlot4 = RagiUtil.isSameStack(this.inputs[4], inventory.getStackInSlot(4), useCount)
            result = matchSlot0 && matchSlot1 && matchSlot2 && matchSlot3 && matchSlot4
            if (!useCount) {
                val amountSlot0 = matchSlot0 && (inventory.getStackInSlot(0).count >= this.inputs[0].count)
                val amountSlot1 = matchSlot1 && (inventory.getStackInSlot(1).count >= this.inputs[1].count)
                val amountSlot2 = matchSlot2 && (inventory.getStackInSlot(2).count >= this.inputs[2].count)
                val amountSlot3 = matchSlot3 && (inventory.getStackInSlot(3).count >= this.inputs[3].count)
                val amountSlot4 = matchSlot4 && (inventory.getStackInSlot(4).count >= this.inputs[4].count)
                result = amountSlot0 && amountSlot1 && amountSlot2 && amountSlot3 && amountSlot4
            }
        }
        return result
    }
}