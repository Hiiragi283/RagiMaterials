package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShaped
import net.minecraft.util.NonNullList

object ItemMaterialDust : ItemMaterial("dust") {

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //tiny -> dustのレシピを登録
        addCraftingShaped(
            getStack(material),
            NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_DUST_TINY.getOreDict(material))),
            3,
            3,
            "${RagiMaterials.MODID}:tiny_to_dust_${material.index}"
        )
    }
}