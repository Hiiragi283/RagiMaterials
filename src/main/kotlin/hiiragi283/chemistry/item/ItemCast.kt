package hiiragi283.chemistry.item

import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ICastItem
import hiiragi283.api.item.ItemMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.core.util.CraftingBuilder
import hiiragi283.material.RMItems
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fluids.FluidStack

class ItemCast(val amount: Int, val item: ItemMaterial) : HiiragiItem("cast_${item.shape.name}", 0), ICastItem {

    init {
        maxDamage = 63
        maxStackSize = 1
    }

    override fun getFluidAmount(stack: ItemStack): Int = amount

    override fun getResult(stack: ItemStack, fluid: FluidStack?): ItemStack {
        return if (fluid != null) {
            val material = MaterialRegistry.getMaterial(fluid.fluid.name)
            if (material.isEmpty()) ItemStack.EMPTY
            else item.getItemStack(material)
        } else ItemStack.EMPTY
    }

    //    HiiragiEntry    //

    override fun registerRecipe() {
        CraftingBuilder(ItemStack(this))
            .addIngredient(
                Ingredient.fromStacks(RMItems.MATERIAL_PLATE.getItemStack(MaterialCommon.CLAY)),
                Ingredient.fromItem(item)
            )
            .buildShapeless()
    }

}