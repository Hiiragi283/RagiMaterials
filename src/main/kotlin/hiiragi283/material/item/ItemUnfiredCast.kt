package hiiragi283.material.item

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fml.common.registry.GameRegistry

object ItemUnfiredCast : HiiragiItem("unfired_cast", 4) {

    val RESULTS: Map<Int, ItemCast> = mapOf(
        0 to HiiragiItems.CAST_INGOT,
        1 to HiiragiItems.CAST_NUGGET,
        2 to HiiragiItems.CAST_GEAR,
        3 to HiiragiItems.CAST_PLATE,
        4 to HiiragiItems.CAST_STICK,
    )

    //    HiiragiEntry    //

    override fun registerRecipe() {
        (0..maxMeta).forEach {
            //Clay -> Unfired Cast
            CraftingBuilder(ItemStack(this, 1, it))
                .addIngredient(
                    Ingredient.fromStacks(ItemStack(Blocks.CLAY)),
                    Ingredient.fromItem(RESULTS[it]!!.item)
                )
                .build()
            //Unfired Cast -> Cast
            GameRegistry.addSmelting(
                ItemStack(this, 1, it),
                ItemStack(RESULTS[it]!!), 0.0f
            )
        }
    }

}