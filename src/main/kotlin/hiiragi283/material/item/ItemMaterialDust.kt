package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.item.ItemStack

object ItemMaterialDust : ItemMaterialBase(PartRegistry.DUST) {

    override fun materialRecipe(material: HiiragiMaterial) {
        CraftingBuilder(ItemStack(this, 1, material.index))
            .setPattern("AAA", "AAA", "AAA")
            .setIngredient('A', "dustTiny${material.getOreDictName()}")
            .buildShaped()

    }

}