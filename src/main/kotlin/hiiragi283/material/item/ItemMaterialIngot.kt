package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShapeless
import net.minecraft.util.NonNullList

object ItemMaterialIngot : ItemMaterial("ingot") {

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //block -> ingotのレシピを登録
        addCraftingShapeless(
            getStack(material, 9),
            NonNullList.withSize(1, RagiIngredient(RagiRegistry.ITEM_PART_BLOCK.getOreDict(material))),
            "${RagiMaterials.MOD_ID}:block_to_ingot_${material.index}"
        )
        //nugget -> ingotのレシピを登録
        addCraftingShapeless(
            getStack(material),
            NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_NUGGET.getOreDict(material))),
            "${RagiMaterials.MOD_ID}:nugget_to_ingot_${material.index}"
        )
    }
}