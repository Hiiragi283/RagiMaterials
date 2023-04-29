package ragi_materials.core.recipe

import ragi_materials.core.config.RagiConfig

object SmeltingRegistry {

    fun addSmelting() {
        //addSmeltingCore()
        //Experimental Feature
        if (RagiConfig.module.enableExperimental) addSmeltingExp()
        //Magical Feature
        if (RagiConfig.module.enableMagic) addSmeltingMagic()
        //Main Feature
        if (RagiConfig.module.enableMain) addSmeltingMain()
        //Metallurgic Feature
        if (RagiConfig.module.enableMetallurgy) addSmeltingMetallurgy()
    }

    /*private fun addSmeltingCore() {
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
    }*/

    private fun addSmeltingMetallurgy() {}

    private fun addSmeltingMain() {}

    private fun addSmeltingMagic() {}

    private fun addSmeltingExp() {}

}