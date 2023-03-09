package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.tile.TileForgeFurnace

class FFRecipe(val part: MaterialPart, val material: MaterialBuilder) {

    var input = MaterialUtil.getPart(part, material)
    var output = MaterialUtil.getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
    var fuel = TileForgeFurnace.getFuelConsumption(input)

}