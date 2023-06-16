package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.item.ItemStack

object ItemMaterialIngot : ItemMaterialBase(PartRegistry.INGOT) {

    override fun isMatch(material: HiiragiMaterial): Boolean = super.isMatch(material) && material.isMetal()

    override fun materialRecipe(material: HiiragiMaterial) {
        //nugget -> ingot
        CraftingBuilder(ItemStack(this, 1, material.index))
            .setPattern("AAA", "AAA", "AAA")
            .setIngredient('A', "nugget${material.getOreDictName()}")
            .buildShaped()
        //block -> ingot
        val ingot9 = ItemStack(this, 9, material.index)
        CraftingBuilder(ingot9.toLocation().append("_alt"), ingot9)
            .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
            .buildShapeless()
    }

}