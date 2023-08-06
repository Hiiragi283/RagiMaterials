package hiiragi283.chemistry.item

import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.material.MaterialCommon
import hiiragi283.chemistry.RCItems
import hiiragi283.core.RMCreativeTabs
import hiiragi283.core.util.CraftingBuilder
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemUnfiredCast : HiiragiItem("unfired_cast", 4) {

    private val map: Map<Int, ItemCast> = mapOf(
        0 to RCItems.CAST_INGOT,
        1 to RCItems.CAST_NUGGET,
        2 to RCItems.CAST_GEAR,
        3 to RCItems.CAST_PLATE,
        4 to RCItems.CAST_STICK,
    )

    init {
        creativeTab = RMCreativeTabs.COMMON
    }

    //    HiiragiEntry    //

    override fun registerRecipe() {
        (0..maxMeta).forEach {
            //Clay -> Unfired Cast
            CraftingBuilder(ItemStack(this, 1, it))
                .addIngredient(
                    Ingredient.fromStacks(ItemStack(Blocks.CLAY)),
                    Ingredient.fromItem(map[it]!!.item)
                )
                .buildShapeless()
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