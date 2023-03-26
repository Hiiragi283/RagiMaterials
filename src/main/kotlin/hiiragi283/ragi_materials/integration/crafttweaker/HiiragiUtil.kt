package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.api.item.IItemStack
import crafttweaker.api.minecraft.CraftTweakerMC
import net.minecraft.item.ItemStack

object HiiragiUtil {

    //IItemStack[] -> List<ItemStack>
    fun getStacks(iStacks: Array<IItemStack>): List<ItemStack> {
        val list = mutableListOf<ItemStack>()
        iStacks.forEach { list.add(CraftTweakerMC.getItemStack(it)) }
        return list
    }
}