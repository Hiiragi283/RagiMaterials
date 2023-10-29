package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialBlockStorage : MaterialBlock(HiiragiShapes.BLOCK) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 4x/9x Ingot/Gem -> 1x Block
        val builder = CraftingBuilder(this.itemStack(material))
        when (shape.getIngotCount(material)) {
            4 -> builder.setPattern("AA", "AA")
            9 -> builder.setPattern("AAA", "AAA", "AAA")
            else -> {}
        }
        if (HiiragiShapes.INGOT.canCreateMaterialItem(material)) {
            builder.setIngredient('A', HiiragiShapes.INGOT.getOreDict(material)).build()
        } else if (HiiragiShapes.GEM.canCreateMaterialItem(material)) {
            builder.setIngredient('A', HiiragiShapes.GEM.getOreDict(material)).build()
        }
        //Grinder Recipe
        addGrinderRecipe(material)
    }

}