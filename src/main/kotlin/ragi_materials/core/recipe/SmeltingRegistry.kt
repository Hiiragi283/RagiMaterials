package ragi_materials.core.recipe

import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.addSmelting
import ragi_materials.core.util.getPart

object SmeltingRegistry {

    fun addSmelting() {
        addSmeltingCore()
        //Experimental Feature
        if (RagiConfig.module.enableExperimental) addSmeltingExp()
        //Magical Feature
        if (RagiConfig.module.enableMagic) addSmeltingMagic()
        //Main Feature
        if (RagiConfig.module.enableMain) addSmeltingMain()
        //Metallurgic Feature
        if (RagiConfig.module.enableMetallurgy) addSmeltingMetallurgy()
    }

    private fun addSmeltingCore() {
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (material.type == TypeRegistry.METAL && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                val output = getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
                val input = getPart(part, material)
                addSmelting(output, input)
            }
        }
    }

    private fun addSmeltingMetallurgy() {}

    private fun addSmeltingMain() {}

    private fun addSmeltingMagic() {}

    private fun addSmeltingExp() {}

}