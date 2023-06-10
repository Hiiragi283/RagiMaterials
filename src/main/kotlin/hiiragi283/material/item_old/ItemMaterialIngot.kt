package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShapeless
import net.minecraft.util.NonNullList

object ItemMaterialIngot : ItemMaterial("ingot") {

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //block -> ingotのレシピを登録
        addCraftingShapeless(
            getStack(material, 9),
            NonNullList.withSize(1, RagiIngredient(RagiRegistry.ITEM_PART_BLOCK.getOreDict(material))),
            "${RagiMaterials.MODID}:block_to_ingot_${material.index}"
        )
        //nugget -> ingotのレシピを登録
        addCraftingShapeless(
            getStack(material),
            NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_NUGGET.getOreDict(material))),
            "${RagiMaterials.MODID}:nugget_to_ingot_${material.index}"
        )
    }
}