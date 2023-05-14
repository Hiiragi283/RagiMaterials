package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShapeless
import net.minecraft.util.NonNullList

object ItemMaterialNugget : ItemMaterial("nugget", 0.1f) {

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //ingot -> nuggetのレシピを登録
        addCraftingShapeless(
            getStack(material, 9),
            NonNullList.withSize(1, RagiIngredient(RagiRegistry.ITEM_PART_INGOT.getOreDict(material))),
            "${RagiMaterials.MOD_ID}:ingot_to_nugget_${material.index}"
        )
    }
}