package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import net.minecraft.item.ItemStack

object ItemMaterialDustTiny : ItemMaterialBase(PartRegistry.DUST_TINY) {

    override fun materialRecipe(material: HiiragiMaterial) {
        CraftingBuilder(ItemStack(this, 9, material.index))
            .addIngredient(RagiIngredient("dust${material.getOreDictName()}"))
            .buildShapeless()

    }

}