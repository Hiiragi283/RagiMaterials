package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack

object LTRegistry {

    val list: MutableList<LTRecipe> = mutableListOf()

    val WATER = LTRecipe(
            RagiUtils.getFilledBottle(2, "hydrogen", 1000),
            RagiUtils.getFilledBottle(1,"oxygen", 1000),
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            ItemStack.EMPTY,
            RagiUtils.getFilledBottle(2,"water", 1000)
    )

}