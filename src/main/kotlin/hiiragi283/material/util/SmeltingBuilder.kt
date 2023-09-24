package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

object SmeltingBuilder {

    fun addSmelting(input: ItemStack, output: ItemStack) {
        GameRegistry.addSmelting(input, output, 0f)
    }

    fun addSmelting(input: Item, output: ItemStack) {
        GameRegistry.addSmelting(input, output, 0f)
    }

    fun addSmelting(input: Block, output: ItemStack) {
        GameRegistry.addSmelting(input, output, 0f)
    }

}