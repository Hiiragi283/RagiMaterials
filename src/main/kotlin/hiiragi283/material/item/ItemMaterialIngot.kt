package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.item.ItemStack

object ItemMaterialIngot : ItemMaterialBase(PartRegistry.INGOT) {

    override fun isMatch(material: HiiragiMaterial): Boolean = super.isMatch(material) && material.isMetal()

    //    IRMEntry    //

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) }
            .forEach {
                //nugget -> ingot
                CraftingBuilder(ItemStack(this, 1, it.index))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', "nugget${it.getOreDictName()}")
                    .buildShaped()
                //block -> ingot
                val ingot9 = ItemStack(this, 9, it.index)
                CraftingBuilder(ingot9.toLocation().append("_alt"), ingot9)
                    .addIngredient(RagiIngredient("block${it.getOreDictName()}"))
                    .buildShapeless()
            }
    }

}