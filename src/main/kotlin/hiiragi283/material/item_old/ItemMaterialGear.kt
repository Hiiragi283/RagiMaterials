package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.MaterialRegistryOld
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.addCraftingShaped

object ItemMaterialGear : ItemMaterial("gear", 4.0f) {

    override fun registerRecipe() {
        super.registerRecipe()
        //Stone Gear
        addCraftingShaped(
            "${RagiMaterials.MODID}:stone_to_gear",
            getStack(MaterialRegistryOld.STONE),
            " A ",
            "A A",
            " A ",
            'A', "stone"
        )
        //Wood Gear
        addCraftingShaped(
            "${RagiMaterials.MODID}:planks_to_gear",
            getStack(MaterialRegistryOld.WOOD),
            " A ",
            "A A",
            " A ",
            'A', "plankWood"
        )
    }

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //ingot -> gearのレシピを登録
        addCraftingShaped(
            "${RagiMaterials.MODID}:ingot_to_gear_${material.index}",
            getStack(material),
            " A ",
            "A A",
            " A ",
            'A', RagiRegistry.ITEM_PART_INGOT.getOreDict(material)
        )
    }
}