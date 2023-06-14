package hiiragi283.material.item

import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.item.ItemStack

object ItemMaterialDust : ItemMaterialBase(PartRegistry.DUST) {

    //    IRMEntry    //

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() }
            .forEach {
                CraftingBuilder(ItemStack(this, 1, it.index))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', "dustTiny${it.getOreDictName()}")
                    .buildShaped()
            }
    }

}