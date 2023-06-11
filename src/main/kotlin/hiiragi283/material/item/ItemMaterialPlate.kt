package hiiragi283.material.item

import hiiragi283.material.init.RMItems
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.StandardState
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object ItemMaterialPlate : ItemMaterialBase(PartRegistry.PLATE) {

    //    IRMEntry    //

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { it.getState() == StandardState.SOLID }
            .forEach {
                CraftingBuilder(ItemStack(this, 1, it.getIndex()))
                    .addIngredient(RagiIngredient("ingot${it.getOreDictName()}"))
                    .addIngredient(RagiIngredient(ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE)))
                    .buildShapeless()
            }
    }

}