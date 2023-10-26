package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.append
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.toLocation
import net.minecraft.item.ItemStack

object MaterialItemIngot : MaterialItem(HiiragiShapes.INGOT) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 9x Nugget -> 1x Ingot
        if (!HiiragiShapes.NUGGET.canCreateMaterialItem(material)) {
            CraftingBuilder(itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.NUGGET.getOreDict(material))
                .build()
        }
        // 1x Block -> 9x Ingot
        if (!HiiragiShapes.BLOCK.canCreateMaterialItem(material)) {
            val ingot9: ItemStack = itemStack(material, 9)
            CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
                .addIngredient(HiiragiShapes.BLOCK.getOreDict(material))
                .build()
        }
        // Grinder Recipe
        addGrinderRecipe(material)
    }

}