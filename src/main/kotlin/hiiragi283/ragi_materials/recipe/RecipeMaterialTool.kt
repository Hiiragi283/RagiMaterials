package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtil

object RecipeMaterialTool {

    fun init() {
        for (material in MaterialRegistry.mapIndex.values) {
            if (material.durability !== null) {
                //Forge Hammer
                val forgeHammer = RagiUtil.getStack("${Reference.MOD_ID}:forge_hammer", 1, 0)
                val item = forgeHammer.item
                if (item is IMaterialItem) item.setMaterial(forgeHammer, material)
                RagiRecipe.addShaped("${Reference.MOD_ID}:forge_hammer_${material.name}", forgeHammer, "AAA", "AAA", " B ", 'A', "ingot${material.getOreDict()}", 'B', RagiUtil.getStack("minecraft:sign", 1, 0))
            }
        }
    }
}