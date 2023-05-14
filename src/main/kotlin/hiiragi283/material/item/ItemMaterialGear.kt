package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.addCraftingShaped

object ItemMaterialGear : ItemMaterial("gear", 4.0f) {

    override fun registerRecipe() {
        super.registerRecipe()
        //Stone Gear
        addCraftingShaped(
            "${RagiMaterials.MOD_ID}:stone_to_gear",
            getStack(MaterialRegistry.STONE),
            " A ",
            "A A",
            " A ",
            'A', "stone"
        )
        //Wood Gear
        addCraftingShaped(
            "${RagiMaterials.MOD_ID}:planks_to_gear",
            getStack(MaterialRegistry.WOOD),
            " A ",
            "A A",
            " A ",
            'A', "plankWood"
        )
    }

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //ingot -> gearのレシピを登録
        addCraftingShaped(
            "${RagiMaterials.MOD_ID}:ingot_to_gear_${material.index}",
            getStack(material),
            " A ",
            "A A",
            " A ",
            'A', RagiRegistry.ITEM_PART_INGOT.getOreDict(material)
        )
    }
}