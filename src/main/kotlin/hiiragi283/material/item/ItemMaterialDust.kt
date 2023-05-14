package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShaped
import net.minecraft.util.NonNullList

object ItemMaterialDust : ItemMaterial("dust") {

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //tiny -> dustのレシピを登録
        addCraftingShaped(
            getStack(material),
            NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_DUST_TINY.getOreDict(material))),
            3,
            3,
            "${RagiMaterials.MOD_ID}:tiny_to_dust_${material.index}"
        )
    }
}