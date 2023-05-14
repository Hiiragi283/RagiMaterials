package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShapeless
import net.minecraft.util.NonNullList

object ItemMaterialDustTiny : ItemMaterial("dust_tiny", 0.1f) {

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //dust -> tiny dustのレシピを登録
        addCraftingShapeless(
            getStack(material, 9),
            NonNullList.withSize(1, RagiIngredient(RagiRegistry.ITEM_PART_DUST.getOreDict(material))),
            "${RagiMaterials.MOD_ID}:dust_to_tiny_${material.index}"
        )
    }
}