package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

object LTRegistry {

    val list: MutableList<LTRecipe> = mutableListOf()

    fun init() {
        LTRecipe(RagiUtil.getFilledBottle(2, "hydrogen", 1000), RagiUtil.getFilledBottle(1,"oxygen", 1000), ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, RagiUtil.getFilledBottle(2,"water", 1000))

        LTRecipe(ItemStack(RagiInit.ItemDust, 1, MaterialRegistry.CARBON.index), RagiUtil.getFilledBottle(1,"oxygen", 1000), ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, RagiUtil.getFilledBottle(1,"carbon_dioxide", 1000))

    }
}