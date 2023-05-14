package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.addCraftingShaped
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object ItemMaterialPlate : ItemMaterial("plate") {

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //ingot -> gearのレシピを登録
        addCraftingShaped(
            "${RagiMaterials.MOD_ID}:ingot_to_plate_${material.index}",
            getStack(material),
            "A",
            "B",
            "B",
            'A', ItemStack(RagiRegistry.ITEM_FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE),
            'B', RagiRegistry.ITEM_PART_INGOT.getOreDict(material)
        )
    }
}