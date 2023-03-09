package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler

class LaboRecipeBuilder(
        val input1: ItemStack = ItemStack.EMPTY,
        val input2: ItemStack = ItemStack.EMPTY,
        val input3: ItemStack = ItemStack.EMPTY,
        val input4: ItemStack = ItemStack.EMPTY,
        val input5: ItemStack = ItemStack.EMPTY,
        val outputs: List<ItemStack>
    ) {

    init {
        register()
    }

    constructor(
            input1: ItemStack = ItemStack.EMPTY,
            input2: ItemStack = ItemStack.EMPTY,
            input3: ItemStack = ItemStack.EMPTY,
            input4: ItemStack = ItemStack.EMPTY,
            input5: ItemStack = ItemStack.EMPTY,
            output: ItemStack
    ) : this(input1, input2, input3, input4, input5, listOf(output))

    fun match(inventory: IItemHandler): Boolean {
        var result = false
        if (inventory.slots == 5) {
            val matchSlot1 = RagiUtil.isSameStack(this.input1, inventory.getStackInSlot(0), true)
            val matchSlot2 = RagiUtil.isSameStack(this.input2, inventory.getStackInSlot(1), true)
            val matchSlot3 = RagiUtil.isSameStack(this.input3, inventory.getStackInSlot(2), true)
            val matchSlot4 = RagiUtil.isSameStack(this.input4, inventory.getStackInSlot(3), true)
            val matchSlot5 = RagiUtil.isSameStack(this.input5, inventory.getStackInSlot(4), true)
            result = matchSlot1 && matchSlot2 && matchSlot3 && matchSlot4 && matchSlot5
        }
        return result
    }

    fun register(): LaboRecipeBuilder = also { LaboRecipeRegistry.list.add(it) }
}