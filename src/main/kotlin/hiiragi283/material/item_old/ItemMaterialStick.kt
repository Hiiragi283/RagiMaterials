package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.addCraftingShaped

object ItemMaterialStick : ItemMaterial("stick", 0.5f) {

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //ingot -> gearのレシピを登録
        addCraftingShaped(
            "${RagiMaterials.MODID}:ingot_to_stick_${material.index}",
            getStack(material),
            "A",
            "A",
            'A', RagiRegistry.ITEM_PART_INGOT.getOreDict(material)
        )
    }
}