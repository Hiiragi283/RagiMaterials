package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.materials.EnumMaterials
import hiiragi283.ragi_materials.util.RagiRecipe
import hiiragi283.ragi_materials.util.RagiUtils

object RagiInitRecipe {

    fun registerRecipes() {
        addCrafting()
    }

    private fun addCrafting() {
        for (material in EnumMaterials.values()) {
            //nugget -> ingotのレシピを登録
            RagiRecipe.addShaped(RagiUtils.getStack(Reference.MOD_ID, "ingot", 1, material.index), "AAA", "AAA", "AAA", 'A', "nugget${material.getOreDict()}")
            //ingot -> nuggetのレシピを登録
            RagiRecipe.addShaped(RagiUtils.getStack(Reference.MOD_ID, "nugget", 9, material.index), "A", 'A', "ingot${material.getOreDict()}")
        }
    }
}