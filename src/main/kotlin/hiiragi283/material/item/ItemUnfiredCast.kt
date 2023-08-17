package hiiragi283.material.item

import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.material.MaterialCommon
import hiiragi283.material.RMItems
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemUnfiredCast : HiiragiItem("unfired_cast", 4) {

    private val map: Map<Int, ItemCast> = mapOf(
        0 to RMItems.CAST_INGOT,
        1 to RMItems.CAST_NUGGET,
        2 to RMItems.CAST_GEAR,
        3 to RMItems.CAST_PLATE,
        4 to RMItems.CAST_STICK,
    )

    //    HiiragiEntry    //

    override fun registerRecipe() {
        (0..maxMeta).forEach {
            //Clay -> Unfired Cast
            CraftingBuilder(ItemStack(this, 1, it))
                .addIngredient(
                    Ingredient.fromStacks(ItemStack(Blocks.CLAY)),
                    Ingredient.fromItem(map[it]!!.item)
                )
                .build()
            //Unfired Cast -> Cast
            GameRegistry.addSmelting(
                ItemStack(this, 1, it),
                ItemStack(map[it]!!), 0.0f
            )
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler(
            { stack, tintIndex -> if (tintIndex == 1) MaterialCommon.CLAY.color else -1 },
            this
        )
    }

}