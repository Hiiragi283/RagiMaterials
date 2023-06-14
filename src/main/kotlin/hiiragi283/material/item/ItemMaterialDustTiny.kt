package hiiragi283.material.item

import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import net.minecraft.item.ItemStack

object ItemMaterialDustTiny : ItemMaterialBase(PartRegistry.DUST_TINY) {

    //    IRMEntry    //

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() }
            .forEach {
                CraftingBuilder(ItemStack(this, 9, it.index))
                    .addIngredient(RagiIngredient("dust${it.getOreDictName()}"))
                    .buildShapeless()
            }
    }

}