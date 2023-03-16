package hiiragi283.ragi_materials.integration.jei.stone_mill

import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry

class StoneMillRecipe(val part: MaterialPart, val material: MaterialBuilder) {

    var input = MaterialUtil.getPart(part, material)
    var output = MaterialUtil.getPart(PartRegistry.DUST, material, part.scale.toInt())

}