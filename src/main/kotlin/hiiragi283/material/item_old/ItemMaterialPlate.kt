package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.addCraftingShaped
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object ItemMaterialPlate : ItemMaterial("plate") {

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //ingot -> gearのレシピを登録
        addCraftingShaped(
            "${RagiMaterials.MODID}:ingot_to_plate_${material.index}",
            getStack(material),
            "A",
            "B",
            "B",
            'A', ItemStack(RagiRegistry.ITEM_FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE),
            'B', RagiRegistry.ITEM_PART_INGOT.getOreDict(material)
        )
    }
}