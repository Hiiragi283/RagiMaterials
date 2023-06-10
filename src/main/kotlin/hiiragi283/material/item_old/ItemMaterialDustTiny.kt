package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShapeless
import net.minecraft.util.NonNullList

object ItemMaterialDustTiny : ItemMaterial("dust_tiny", 0.1f) {

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //dust -> tiny dustのレシピを登録
        addCraftingShapeless(
            getStack(material, 9),
            NonNullList.withSize(1, RagiIngredient(RagiRegistry.ITEM_PART_DUST.getOreDict(material))),
            "${RagiMaterials.MODID}:dust_to_tiny_${material.index}"
        )
    }
}