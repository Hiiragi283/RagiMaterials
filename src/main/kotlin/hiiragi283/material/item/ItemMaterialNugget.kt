package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import net.minecraft.item.ItemStack

object ItemMaterialNugget : ItemMaterialBase(PartRegistry.NUGGET) {

    override fun isMatch(material: HiiragiMaterial): Boolean = super.isMatch(material) && material.isMetal()

    override fun materialRecipe(material: HiiragiMaterial) {
        CraftingBuilder(ItemStack(this, 9, material.index))
            .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
            .buildShapeless()

    }

}