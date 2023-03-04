package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

data class LTRecipe(
        val input1: ItemStack,
        val input2: ItemStack,
        val input3: ItemStack,
        val input4: ItemStack,
        val input5: ItemStack,
        val output: ItemStack
        ) {

    init {
        register()
    }

    fun match(inventory: IItemHandler): Boolean {
        var result = false
        if (inventory.slots == 5) {
            val matchSlot1 = RagiUtils.isSameStack(this.input1, inventory.getStackInSlot(0), true)
            val matchSlot2 = RagiUtils.isSameStack(this.input2, inventory.getStackInSlot(1), true)
            val matchSlot3 = RagiUtils.isSameStack(this.input3, inventory.getStackInSlot(2), true)
            val matchSlot4 = RagiUtils.isSameStack(this.input4, inventory.getStackInSlot(3), true)
            val matchSlot5 = RagiUtils.isSameStack(this.input5, inventory.getStackInSlot(4), true)
            result = matchSlot1 && matchSlot2 && matchSlot3 && matchSlot4 && matchSlot5
        }
        return result
    }

    fun register(): LTRecipe = also { LTRegistry.list.add(it) }
}